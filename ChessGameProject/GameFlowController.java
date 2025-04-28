// eng zi ying & venice goh
import java.io.IOException;

//this is the contoller class (MVC) which mainly to handle the game flow (single responsibility)
public class GameFlowController {
    private ChessModel model;
    private ChessView view;
    private BoardInteractionController boardController;
    
    //eng zi ying
    // Initialize the model and view object for linking purpose
    public GameFlowController(ChessModel model, ChessView view) {
        this.model = model;
        this.view = view;
    }
    
    //eng zi ying
    //Initialize the setBoardController class obj to interact with it 
    public void setBoardController(BoardInteractionController boardController) {
        this.boardController = boardController;
    }
    
    // eng zi ying
    public void resetGame() {
        model.getGameState().resetGame(); // Reset the model
        view.getHightlightManager().dismissHighlights(); // Clear highlights
        boardController.clearSelection(); // Clear board controller's state
        updateViewBoard(); // Update the view
    }
    
    // venice goh
    public void endGame(){
        // Get WinningTeam name (Red or Blue) from the model
        String winningTeam = model.getGameState().getWinningTeam();
        view.getMessagePanel().displayWinningMessage(winningTeam);
        resetGame(); // Reset game
    }
    
    //eng zi ying
    public void updateViewBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                Position position = new Position(row,col);
                Piece piece = model.getChessBoard().getPieceAt(position); //get the piece position from model(ChessBoard)
                if (piece != null) {
                    view.getChessBoardPanel().updateSquare(row, col, piece.getImagePath()); //call the view (ChessBoardPanel) to update the piece to the chessboard)
                } else {
                    view.getChessBoardPanel().clearSquare(row, col); //call the view (ChessBoardPanel) to clear the square (no piece will be displayed on the chessboard)
                }
            }
        }
    }
    
    //eng zi ying & venice goh
    public void saveGame() {
        try {
            model.getSaveLoadGame().saveToFile("chess_save.txt"); //call the model(SaveLoadGame) to save the board and piece
            view.getMessagePanel().displayMessage("Game saved successfully!"); //notify the player
        } catch (IOException e) {
            view.getMessagePanel().displayMessage("Error saving game: " + e.getMessage());//error message to notify player
        }
    }
    
    //eng zi ying
    public void loadGame() {
        try {
            model.getSaveLoadGame().loadFromFile("chess_save.txt"); //get the loaded data from model(SaveLoadGame)
            updateViewBoard(); //pass the loaded data to update the view board
            view.getHightlightManager().dismissHighlights(); //clear the previous highlighted button(if any)
            view.getMessagePanel().displayMessage("Game loaded successfully!"); //notify player 
        } catch (IOException e) {
            view.getMessagePanel().displayMessage("Error loading game: " + e.getMessage()); //error message to notify player
        }
    }
}
