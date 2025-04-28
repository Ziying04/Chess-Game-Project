// teow wei ting 

//this is the model class (MVC)
//to handle the game state 
public class GameState
{
    private ChessBoard chessBoard;
    private TurnManager turnManager;
    private boolean isEndGame;
    private String winningTeam;
    
    public GameState(ChessBoard chessBoard, TurnManager turnManager) {
        this.chessBoard = chessBoard;
        this.turnManager = turnManager;
    }
    
   
    public void resetGame() {
        chessBoard.initializeBoard();
        turnManager.resetTurns();
    }
    
    public void checkEndGame(Piece opponent) {
        if (opponent instanceof Sau) {
            // Get the winning team from ChessModel
            determineWinningTeam(opponent);
            
            //set the endGame flag
            this.isEndGame = true;
            //return;
        }else{
            this.isEndGame = false;
        }
    }
    
    public boolean getIsEndGame(){
        return isEndGame;
    }
    
    public void determineWinningTeam(Piece opponent){
        if(opponent.getColor()){
            winningTeam = "Blue";
        } else {
            winningTeam = "Red";
        }
    }
    
    public String getWinningTeam(){
        return winningTeam;
    }
}
