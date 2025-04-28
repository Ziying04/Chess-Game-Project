// wong hui ting

//this is the model class (MVC)
//handles and store the turn related info(single responsibility)
public class TurnManager {
    private ChessBoard chessBoard;
    private TorXorLogic torXorLogic;
    private Piece[][] board;
    private boolean redTurn;
    private int turnCounter;

    public TurnManager(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        redTurn = false; // blue starts
        turnCounter = 0; //initialize turn counter to 0
        board = chessBoard.getBoard();
    }

    public void setTorXorLogic(TorXorLogic torXorLogic){
        this.torXorLogic = torXorLogic;
    }

    //check if it is red's turn
    public boolean isRedTurn() {
        return redTurn;
    }
    
    //get the current turn counter
    public int getTurnCounter(){
        return turnCounter;
    } 
    
    //update the turn counter
    public void setTurnCounter(int turnCounter){
        this.turnCounter = turnCounter;
    }
    
    //set whether it is red's turn or not
    public void setRedTurn(boolean isRedTurn){
        this.redTurn = isRedTurn;
    }
    
    public void nextTurn() {
        //increment the turn counter for all pieces
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                Piece piece = board[i][j];
                if (piece != null){
                    piece.incrementTurnCounter();
                }
            }
        }
        
        //if it is red's turn, increment the turn counter for the game and check for piece exchanges
        if (redTurn){
            turnCounter++;
            torXorLogic.shouldExchange();
        }
        
        redTurn = !redTurn; // Switch turn
    }

    //reset the turn counter
    public void resetTurns() {
        redTurn = false; //set to blue's turn
        turnCounter = 0;
    }
}
