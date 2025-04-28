// eng zi ying & teow wei ting & wong hui ting & venice goh

//this is the model class (MVC)
//acts as the middle layer that coordinates between the subcomponent of the model classes (board, pieces, and game logic )
//also used to exposed the subcomponent of the model class to the controller class
//also handle the main move piece logic 
public class ChessModel {
    private ChessBoard chessBoard;
    private TurnManager turnManager;
    private TorXorLogic torXorLogic;
    private SaveLoadGame saveLoadGame;
    private GameState gameState;

    // eng zi ying
    public ChessModel() {
        //initialize sub-component
        this.chessBoard = new ChessBoard();
        this.turnManager = new TurnManager(chessBoard);
        this.torXorLogic = new TorXorLogic(chessBoard,turnManager);
        this.saveLoadGame = new SaveLoadGame(chessBoard, turnManager);
        this.gameState = new GameState(chessBoard, turnManager);
        
        //link needed the sub-component
        chessBoard.setTurnManager(turnManager);
        turnManager.setTorXorLogic(torXorLogic);
    }

    //expose the needed object and attributes to other classes
    //wong hui ting
    public boolean isRedTurn() {
        return turnManager.isRedTurn();
    }
    
    // eng zi ying & teow wei ting
    public ChessBoard getChessBoard(){
        return chessBoard;
    }
    
    //eng zi ying
    public SaveLoadGame getSaveLoadGame(){
        return saveLoadGame;
    }
    
    //teow wei ting
    public GameState getGameState(){
        return gameState;
    }

    // handles the main move piece logic 
    // eng zi ying & teow wei ting & wong hui ting & venice goh
    public boolean movePiece(Position start, Position end) {
        Piece movingPiece = chessBoard.getPieceAt(start);
        Piece opponent = chessBoard.getPieceAt(end);
        
        // Validate the move
        if (movingPiece == null || 
            !movingPiece.isValidMove(end, chessBoard.getBoard()) ||  
            chessBoard.checkIsAllies(opponent) ||
            start==end){
            return false; // Invalid move
        }

        // Update chessboard to perform move
        chessBoard.movePiece(start, end);
    
        // Check if the game should end
        gameState.checkEndGame(opponent);
        if(gameState.getIsEndGame()){
            return true; // Game ends
        }
        
        // update turn and flip board
        turnManager.nextTurn();
        chessBoard.flipBoard();

        return true; // Done move
    }
}