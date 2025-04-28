// eng zi ying
import java.util.ArrayList;

//this is the controller class (MVC) to handle the user interaction with the chessboard  (single responsibility)
// eg: what will happen if player click on the board (move/select piece)
public class BoardInteractionController {
    //declare the needed object to call the handle difference function from different classes
    private ChessModel model;
    private ChessView view;
    private GameFlowController gameFlowController;
    private Position previousSelectedPosition = null;

    public BoardInteractionController(ChessModel model, ChessView view) {
        //initialze the model and view (a bridge reach to each subcomponent model and view classes)
        this.model = model;
        this.view = view;
        
        // Add listeners to each square (handles the player clicked on the board)
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                final int r = row, c = col;
                //get the buttons from view(ChessBoardPanel) and add listener to it 
                view.getChessBoardPanel().getButton(row, col).addActionListener(e -> squareClicked(r, c)); 
            }
        }
    }
 
    // link the setGameFlowController
    public void setGameFlowController(GameFlowController gameFlowController) {
        this.gameFlowController = gameFlowController;
    }

    // Clear the selected position on board
    public void clearSelection() {
        previousSelectedPosition = null; 
    }
 
    //handle the squareClicked (what will it do)
    private void squareClicked(int row, int col) {
        Position clickedPosition = new Position(row, col);
        Piece clickedPiece = model.getChessBoard().getPieceAt(clickedPosition); // get the clicked piece from model(ChessBoard)

        if (previousSelectedPosition == null) { // Handles the First click
            selectPiece(clickedPiece,clickedPosition);
        } else { // Handles the Second click
            handleMove(clickedPosition);
        }
    }
    
    //handles the player click on a piece (highlight piece & possible moves)
    private void selectPiece(Piece clickedPiece, Position clickedPosition){
        if (clickedPiece != null && clickedPiece.getColor() == model.isRedTurn()) { //checks the conditions
                previousSelectedPosition = clickedPosition; //indicates the first click has been made
                view.getHightlightManager().highlightPiece(clickedPosition.getRow(), clickedPosition.getColumn());//call the view(HighlightManager) to highlight the piece clicked 
                ArrayList<Position> possibleMoves = clickedPiece.getPossibleMoves(model.getChessBoard().getBoard()); // get the piece's possible move from model
                int[][] possibleMovesArray = new int[8][5]; //the possibleMoveArray is the size of the board
                for (Position move : possibleMoves) {
                    possibleMovesArray[move.getRow()][move.getColumn()] = 1; //if there's a possible move, flag it to 1 from the array, used for highlighting it later
                }
                view.getHightlightManager().highlightPossibleMoves(possibleMovesArray); //pass the flagged possibleMoveArray to view to display the highlight on board
        }
    }
    
    //handles the player click on the designated position on the board to move the piece (move piece)
    private void handleMove(Position clickedPosition){
        boolean moveSuccessful = model.movePiece(previousSelectedPosition, clickedPosition); //calls the model to check if the position to move piece is valid or not
            view.getHightlightManager().dismissHighlights(); // Clear highlights on the previously higlighted position on the board
            if (moveSuccessful) {
                if(model.getGameState().getIsEndGame()){ //call the model to check if a end game (true)
                    gameFlowController.endGame(); //call the controller (gameFlowController) to handle the end game
                }else{ 
                    gameFlowController.updateViewBoard(); // Update the board 
                }
            }
            previousSelectedPosition = null; // Reset selection
    }
}
