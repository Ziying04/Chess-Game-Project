// wong hui ting
import java.util.ArrayList;
import java.util.List;

//this is the model class (MVC)
// handels the TorXorLogic (exchange or not) (single responsibility)
public class TorXorLogic
{
    private TurnManager turnManager; //manage the game turn
    private ChessBoard chessBoard;
    private Piece[][] board;
    
    public TorXorLogic(ChessBoard chessBoard, TurnManager turnManager){
        this.chessBoard = chessBoard;
        this.turnManager = turnManager;
        board = chessBoard.getBoard();
    }
    
    //find and return the Xor piece
    public Piece getXorPiece(boolean isRed){
        //loop through the entire board to find the Xor piece
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                //check if the piece is an instance of Xor and matches the given color
                if (board[i][j] instanceof Xor && board[i][j].getColor() == isRed){
                    return board[i][j]; //return the Xor piece
                }
            }
        }
        return null;
    }
    
    //find and return the Tor piece
    public Piece getTorPiece(boolean isRed){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] instanceof Tor && board[i][j].getColor() == isRed){
                    return board[i][j]; //return the Tor piece
                }
            }
        }
        return null;
    }
    
    //pieces exchange logic
    public boolean shouldExchange(){
        //get the current turn from turn manager
        int turnCounter = turnManager.getTurnCounter();
        //check the turn counter is greater than 0 and it is an even turn(which mean after two turns)
        if (turnCounter > 0 && turnCounter % 2 == 0) {
            // exchange Xor and Tor for RED
            Piece redXor = getXorPiece(true); // get red's Xor piece
            Piece redTor = getTorPiece(true); // get red's Tor piece
            if (redXor != null && redTor != null) {
                //exchange pieces
                redXor.pieceExchange(board);
                redTor.pieceExchange(board);
            }

            // exchange Xor and Tor for BLUE
            Piece blueXor = getXorPiece(false); // Get blue's Xor piece
            Piece blueTor = getTorPiece(false); // Get blue's Tor piece
            if (blueXor != null && blueTor != null) {
                blueXor.pieceExchange(board);
                blueTor.pieceExchange(board);
            }

            return true; //exchange occurred
        }   
        return false; //no exchange occurred
    }
}
