import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//wong hui ting
//this is the model class (MVC)
//extended from piece(inheritance)
public class Xor extends Piece
{
    private int turnCounter;
    Xor(boolean isRed, Position position){
        super(isRed,position);
    }
    
    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board){
         // function for Xor
         //check if the new position is within bounds
         if (!newPosition.isValid(board.length, board[0].length)){
             return false;
         }
         
         int currentRow = this.getPosition().getRow();
         int currentColumn = this.getPosition().getColumn();
         int targetRow = newPosition.getRow();
         int targetColumn = newPosition.getColumn();
         
         //check if the move is diagonal
         if (Math.abs(currentRow - targetRow) != Math.abs(currentColumn - targetColumn)){
             return false;
         }
         
         //determine the direction of movement
         int rowIncrement = Integer.compare(targetRow, currentRow);
         int columnIncrement = Integer.compare(targetColumn, currentColumn);
         
         //check all squares along the path to ensure they are unoccupied
         int row = currentRow + rowIncrement;
         int column = currentColumn +columnIncrement;
         while (row != targetRow || column != targetColumn){
             if (board[row][column] != null){
                 return false; //path is blocked by another piece
             }
             row += rowIncrement;
             column += columnIncrement;
         }
         
         //check destination square
         Piece targetPiece = board[targetRow][targetColumn];
         if (targetPiece != null && targetPiece.getColor() == this.getColor()){
             return false; //cannot capture a piece of the same color
         }
         return true;  
     }
     
    public void pieceExchange(Piece[][] board){
        //swap xor with tor
        Tor tor = new Tor(this.getColor(), this.getPosition());
        board[this.getPosition().getRow()][this.getPosition().getColumn()] = tor; //replace current xor with new tor
        tor.setPosition(this.getPosition()); //update new tor position
    }
     
    private ArrayList<Position> calculateValidMoves(Piece[][] board) {
        ArrayList<Position> validMoves = new ArrayList<>();
        int positionX = this.getPosition().getRow();
        int positionY = this.getPosition().getColumn();
        
        // define diagonal directions: up-right, down-right, down-left, up-left
        final int[][] DIRECTIONS = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
        
        // check each direction
        for (int[] direction : DIRECTIONS) {
            int newX = positionX;
            int newY = positionY;
            
            // continue in the current direction until we hit a boundary or piece
            while (true) {
                newX += direction[0];
                newY += direction[1];
                
                // check if we're still on the board
                if (newX < 0 || newX >= board.length || 
                    newY < 0 || newY >= board[0].length) {
                    break;
                }
                
                // if empty space, add as valid move
                if (board[newX][newY] == null) {
                    validMoves.add(new Position(newX, newY));
                    continue;
                }
                
                // if enemy piece, add as valid move and stop in this direction
                if (board[newX][newY].getColor() != this.getColor()) {
                    validMoves.add(new Position(newX, newY));
                }
                
                // stop when we hit any piece (enemy or friend)
                break;
            }
        }
        
        return validMoves;
    }
     
     @Override
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        return calculateValidMoves(board); // Return all valid moves
    }
}