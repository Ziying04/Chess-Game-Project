// venice goh kit yee
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//this is the model class (MVC)
//extended from piece(inheritance)
public class Ram extends Piece {
    private boolean isReachEnd; // True moving backward, false if moving forward
    private final int BOARD_ROWS = 8; // Number of rows on the board

    public Ram(boolean isRed, Position position) {
        super(isRed, position);
        this.isReachEnd = false; 
    }
    
    @Override
    public void pieceExchange(Piece[][] board){
        //no exchange needed
    }
    
    
    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        ArrayList<Position> validMoves = calculateValidMoves(board);
        
        // Check if the new position is in the list of valid moves
        for (Position pos : validMoves) {
            if(pos.getRow() == newPosition.getRow() &&
                pos.getColumn() == newPosition.getColumn()){
                    //update if reaches the other end
                    if(newPosition.getRow() == 0){
                        this.isReachEnd = true; //moves backward
                    }
                    return true; // valid move
            }
        }
        
        return false; // invalid move
    }

    private ArrayList<Position> calculateValidMoves(Piece[][] board) {
        ArrayList<Position> validMoves = new ArrayList<>();
        int currentRow = this.position.getRow();
        int currentCol = this.position.getColumn();
        int nextRow = -1;
        
        // determine direction based on Ram has reached the end or not
        if(this.isReachEnd){
            nextRow = currentRow  + 1; //moving backwards
            if (nextRow >= 0 && nextRow < BOARD_ROWS) {
            }
        }else{
            nextRow = currentRow  - 1; //moving forward
        }
        
        // check if the next row is within bounds
        if(nextRow != -1){
            // check if target square is empty or occupied
            if (nextRow >= 0 && nextRow < BOARD_ROWS) {
                if(board[nextRow][currentCol] == null ||
                   board[nextRow][currentCol].getColor() != this.isRed){
                       validMoves.add(new Position(nextRow, currentCol));
                }
            }         
        }
        return validMoves;
    }

    @Override
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        return calculateValidMoves(board); // delegate to the helper method
    }
}
