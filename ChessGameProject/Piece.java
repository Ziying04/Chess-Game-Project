// eng zi ying & teow wei ting & wong hui ting
import java.util.ArrayList; 
 
//this is the utility class to store each piece info (single responsibility)
public abstract class Piece
{
    protected Position position;
    protected boolean isRed;
    protected int turnCounter;
    private boolean boardFlipped = false; // set to be false when the board
      
    public Piece(boolean isRed, Position position)
    {
        this.isRed = isRed;
        this.position = position;
        this.turnCounter = 0;
    }
    
    //teow wei ting  & eng zi ying
    public String getImagePath() {
        String color, pieceName, imagePath;
        if(getColor()){ //piece is red
            color = "red";
        }else{
            color = "blue";
        }
        
        //to determine the piece name 
        pieceName = getClass().getSimpleName().toLowerCase();
        
        //if boardFlipped, get the rotated img 
        if(boardFlipped)
        {
            imagePath = color + "_rotated_" + pieceName + ".png";
        } else{
            imagePath =  color + "_" + pieceName + ".png";
        }
        return imagePath;
    }
    
    // teow wei ting
    public void flipPiece() {
        this.boardFlipped = !this.boardFlipped; // Toggle flipped state
    }
       
    //getters and setter method
    public boolean getColor(){
        return isRed;
    }
    
    public Position getPosition(){
        return position;
    }
    
    public void setPosition(Position position) {
      this.position = position;
    }
    
    // wong hui ting
    //increment the turn counter
    public void incrementTurnCounter(){
        turnCounter++;
    }

    //abstract method for each piece to be override from
    public abstract void pieceExchange(Piece[][] board);
    public abstract boolean isValidMove(Position newPosition, Piece[][]board);
    public abstract ArrayList<Position> getPossibleMoves(Piece[][] board);
}
