// eng zi ying & teow wei ting 
import java.util.ArrayList;
import java.util.List;


//this is the model class (MVC)
//handle and store the board state (single responsibility)
public class ChessBoard {
    private Piece[][] board; //to store every piece info(color,position)
    private TurnManager turnManager;

    public ChessBoard() {
        this.board = new Piece[8][5];
        initializeBoard();
    }

    //eng zi ying
    public void initializeBoard() {
         // First, clear the entire board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                board[row][col] = null; // Set all positions to null
            }
        }
        
        //Initialize each piece following the starting position (creating an instance for each piece)
        // Initialize ram
        for (int j = 0; j < 5; j++) {
            board[1][j] = new Ram(true,new Position(1,j)); //red 
            board[6][j] = new Ram(false,new Position(6,j)); //blue       
        }
        
        //Initialize Biz and Sau
        for(int j =1; j<4;j++){
            if(j==2){
                board[0][j] = new Sau(true,new Position(0,j)); //red 
                board[7][j] = new Sau(false ,new Position(7,j)); //blue 
            }else{
                board[0][j] = new Biz(true,new Position(0,j)); //red 
                board[7][j] = new Biz(false ,new Position(7,j)); //blue 
            }
        }
        
        //Initialize Tor
        board[0][0] = new Tor(true,new Position(0,0)); //red
        board[7][4] = new Tor(false,new Position(7,4)); //blue  
        
        //Initialize Xor
        board[0][4] = new Xor(true,new Position(0,4)); //red
        board[7][0] = new Xor(false,new Position(7,0)); //blue  
    }
    
    //eng zi ying
    //link the setTurnManager model
    public void setTurnManager(TurnManager turnManager){
        this.turnManager = turnManager;
    }
    
    //expose the board and piece from the board
    //eng zi ying
    public Piece[][] getBoard(){
        return board;
    }
    
    //eng zi ying
    public Piece getPieceAt(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    //eng zi ying
    public boolean checkIsAllies(Piece opponent){
        if(opponent == null) {
            return false; //no piece at the opponent position 
        }
        return opponent.getColor() == turnManager.isRedTurn(); // return true if opponent is an ally
    }
    
    //eng zi ying
    //update the piece position and data on the board after moving 
    public void movePiece(Position start, Position end) {
        Piece piece = getPieceAt(start);
        board[end.getRow()][end.getColumn()] = piece; //update the piece to the newly selected position
        board[start.getRow()][start.getColumn()] = null; //reset the old position after the move
        board[end.getRow()][end.getColumn()].setPosition(end); //uppdate the new position
    }
    
    //teow wei ting
    // check if a piece at the position is sau or not
    public boolean isSau(Position position) {
        Piece piece = getPieceAt(position);
        return piece instanceof Sau; // Returns true if the piece is an instance of Sau
    }
    
    //teow wei ting
    // mirror position
    public void flipBoard() {
        int rows = board.length;
        int cols = board[0].length;
    
        // Create a new board for the flipped state
        Piece[][] flippedBoard = new Piece[rows][cols];
    
        // Iterate over the entire board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Calculate the mirrored position
                int flippedRow = rows - row - 1;
                int flippedCol = cols - col - 1;
    
                // Place the piece in the mirrored position on the flipped board
                flippedBoard[flippedRow][flippedCol] = board[row][col];
    
                // Update the piece's position and state
                if (flippedBoard[flippedRow][flippedCol] != null) {
                    flippedBoard[flippedRow][flippedCol].setPosition(new Position(flippedRow, flippedCol));
                    flippedBoard[flippedRow][flippedCol].flipPiece();
                }
            }
        }
    
        // Copy the flipped board back to the original board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = flippedBoard[row][col];
            }
        }
    }
}
