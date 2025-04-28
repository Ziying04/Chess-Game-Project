// Teow Wei Ting
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//this is the model class (MVC)
//extended from piece(inheritance) single responsibility
public class Biz extends Piece
{
    // constructor to initialize the biz with color and position
    Biz(boolean isRed, Position position){
        super(isRed,position);
    }
    
    @Override
    public void pieceExchange(Piece[][] board){
        //no exchange needed
    }
    
    // calculation for all valid moves for biz
    // it can only move in L-shape
    private ArrayList<Position> calculateValidMoves(Piece[][] board){
        final int[][] MOVES = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        ArrayList<Position> validMoves = new ArrayList<>(); // store valid moves
        int positionX = this.getPosition().getRow(); // current row
        int positionY = this.getPosition().getColumn(); // current column

        // iterate possible moves
        for (int[] move : MOVES) {
            int newPositionX = positionX + move[0]; // new row
            int newPositionY = positionY + move[1]; // new column

            // check if the new position is in the board
            if (newPositionX >= 0 && newPositionX < board.length && 
                newPositionY >= 0 && newPositionY < board[0].length) {

                // if position is empty or occupied by an opponent's piece
                if (board[newPositionX][newPositionY] == null || 
                    board[newPositionX][newPositionY].getColor() != this.getColor()) {
                    validMoves.add(new Position(newPositionX, newPositionY)); // add position as valid
                }
            }
        }

        return validMoves; // return the list
    }
    
    @Override
    // check a specific move is valid for biz
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        ArrayList<Position> validMoves = calculateValidMoves(board); // get all valid moves
        
        // check if the new position is in the list or not
        for (Position pos : validMoves) {
            if(pos.getRow() == newPosition.getRow() &&
                pos.getColumn() == newPosition.getColumn()){
                    return true; // Move is valid
            }
        }
        return false; // Move is invalid
    }
    
    @Override
    // get all possible moves for biz
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        return calculateValidMoves(board); // Return all valid moves
    }
}