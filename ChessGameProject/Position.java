// eng zi ying

//this is the model class (MVC)
//also an utility class. Used for identifying and validating board locations.
public class Position
{
    int row, column;
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    public boolean isValid(int boardRows, int boardColumns){
        return row >= 0 && row < boardRows && column >= 0 && column < boardColumns;
    }
}
