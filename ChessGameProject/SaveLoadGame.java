//eng zi ying & venice goh
import java.io.*;

//this is the model class (MVC)
// handle the save load logic
public class SaveLoadGame
{
    private ChessBoard chessBoard;
    private TurnManager turnManager;
    private Piece[][] board;
    private boolean redTurn;
    private int turnCounter;
    
    public SaveLoadGame(ChessBoard chessBoard, TurnManager turnManager){
        this.chessBoard = chessBoard;
        this.turnManager = turnManager;
        board = chessBoard.getBoard();
    }
    
    // eng zi ying & venice goh
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            turnCounter = turnManager.getTurnCounter(); //get the turncounter 
            redTurn = turnManager.isRedTurn(); //get the isRedTurn 
            // Save turn information
            writer.write("redTurn = " + redTurn + "\n"); // Save whose turn into the first line of the txt file 
            writer.write("turn =" + turnCounter + "\n"); // Save current turn number into the second line of the txt file 
    
            // Save the board state row by row 
            for (int i = 0; i < board.length; i++) {
                // Write the top border of each row
                writer.write("+-----".repeat(board[i].length) + "+\n");
                // Write the pieces in the row
                for (int j = 0; j < board[i].length; j++) {
                    Piece piece = board[i][j];
                    if (piece == null) {
                        writer.write("|     "); // Empty cell
                    } else {
                        // Save color and type of the piece
                        String isRed = piece.getColor() ? "R" : "B";
                        String pieceName = String.format("%s", piece.getClass().getSimpleName().charAt(0));
                        writer.write(String.format("| %s%-3s", isRed, pieceName)); // For example: "| RT  " for Red Tor
                    }
                }
                writer.write("|\n");
            }
            // Write the final row's border
            writer.write("+-----".repeat(board[0].length) + "+\n");
        }
    }

    // eng zi ying
    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read turn information
            String redTurnLine = reader.readLine(); //read the first line of the txt file (the redTurn info)
            redTurn = Boolean.parseBoolean(redTurnLine.split("=")[1].trim()); //read only the necessary info from the line
            
            String turnCounterLine = reader.readLine(); //read the second line of the txt file (the redTurn info)
            turnCounter = Integer.parseInt(turnCounterLine.split("=")[1].trim()); //read only the necessary info from the line
            
            //set the loaded info accordingly 
            turnManager.setTurnCounter(turnCounter); 
            turnManager.setRedTurn(redTurn);
            
            // Read the board
            for (int i = 0; i < board.length; i++) {
                reader.readLine(); // Skip the border line
                String row = reader.readLine(); // Read the row with pieces
                String[] cells = row.split("\\|"); // Split by '|' character
    
                for (int j = 1; j < cells.length ; j++) { // Skip the borders
                    String pieceData = cells[j].trim(); // Get the piece info (e.g., "RT", "BX", etc.)
                    
                    if (pieceData.isEmpty()) {
                        board[i][j - 1] = null; // Empty cell
                    } else {
                        char colorChar = pieceData.charAt(0); // First character: 'R' or 'B'
                        boolean isRed = colorChar == 'R'; // Determine the color
                        char pieceChar = pieceData.charAt(1); // Second character: piece type
                        
                        //load the piece position and team into the model based on the piece type 
                        switch (pieceChar) {
                            case 'T': board[i][j - 1] = new Tor(isRed, new Position(i, j - 1)); break;
                            case 'X': board[i][j - 1] = new Xor(isRed, new Position(i, j - 1)); break;
                            case 'R': board[i][j - 1] = new Ram(isRed, new Position(i, j - 1)); break;
                            case 'S': board[i][j - 1] = new Sau(isRed, new Position(i, j - 1)); break;
                            case 'B': board[i][j - 1] = new Biz(isRed, new Position(i, j - 1)); break;
                            default: board[i][j - 1] = null; break;
                        }
                    }
                    
                   // Flip the piece if red's turn (so that they will always be facing each other)
                   if (redTurn && board[i][j - 1] != null) {
                        board[i][j - 1].flipPiece();
                    }
                }
            }
        }
    }
}
