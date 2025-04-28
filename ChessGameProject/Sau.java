//venice goh kit yee
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//this is the model class (MVC)
//extended from piece(inheritance)
public class Sau extends Piece
{
    Sau(boolean isRed, Position position){
        super(isRed,position);
    }
    
    @Override
    public void pieceExchange(Piece[][] board){
        //no exchange needed
    }
    
    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board){
        // function for Sau
        // calculate row and column differences between current position and new position.
        int rowDiff = Math.abs(newPosition.getRow() - this.position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - this.position.getColumn());
        
        // Sau can move one step in any direction
        if ((rowDiff <= 1 && colDiff <= 1) && (rowDiff + colDiff > 0)) {
            // check if target square is empty or occupied
            Piece targetPiece = board[newPosition.getRow()][newPosition.getColumn()];
            return targetPiece == null || targetPiece.getColor() != this.isRed; 
        }
        
        return false; // Invalid move  
    }
    
    private ArrayList<Position> calculateValidMoves(Piece[][] board){
        ArrayList<Position> validMoves = new ArrayList<>();
        int currentRow = this.position.getRow();
        int currentCol = this.position.getColumn();

        // Check all adjacent squares for valid moves
        for (int row = currentRow - 1; row <= currentRow + 1; row++) {
            for (int col = currentCol - 1; col <= currentCol + 1; col++) {
                if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
                    Position newPosition = new Position(row, col);
                    if (isValidMove(newPosition, board)) {
                        validMoves.add(newPosition);
                    }
                }
            }
        }

        return validMoves;
    }
       
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        return calculateValidMoves(board); // delegate to helper method
    }
}