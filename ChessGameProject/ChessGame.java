// eng zi ying & teow wei ting

//this is the main class to compile the application
// this is a controller class (MVC)
//act as a bridge to link the all the model classes and the view classes (MVC)
//it also ochestra all the controller classes for them to work together.
public class ChessGame {
    
    public static void main(String[] args) {
        // Initialize MVC components
        ChessModel model = new ChessModel(); //main bridge to access the sub component of model classes
        ChessView view = new ChessView(); // main bridge to access the sub component of view classes
 
        
        //Initialize controllers (ochestra all the subcomponent of controller classes)
        GameFlowController gameFlowController = new GameFlowController(model, view);
        BoardInteractionController boardController = new BoardInteractionController(model, view);
        MenuController menuController = new MenuController(view, gameFlowController);

        // Link sub component of controllers class
        gameFlowController.setBoardController(boardController);
        boardController.setGameFlowController(gameFlowController);
        
        //update the board
        gameFlowController.updateViewBoard();
        
        // Show the view
        view.setVisible(true);
    }
}
