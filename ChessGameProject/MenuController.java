// venice goh kit yee
import java.awt.event.ActionListener;

//this is the controller class (MVC) to handle the menu clicked(single responsibility)
public class MenuController {
    private ChessView view;
    private GameFlowController gameFlowController;
 
    public MenuController(ChessView view, GameFlowController gameFlowController) {
        this.view = view;
        this.gameFlowController = gameFlowController;

        // Add listeners to menu items (each will call designated class to process the click)
        view.getMenuBarManager().getExitItem().addActionListener(e -> System.exit(0));
        view.getMenuBarManager().getNewGameItem().addActionListener(e -> gameFlowController.resetGame());
        view.getMenuBarManager().getSaveGameItem().addActionListener(e -> gameFlowController.saveGame());
        view.getMenuBarManager().getLoadGameItem().addActionListener(e -> gameFlowController.loadGame());
        view.getMenuBarManager().getGuideItem().addActionListener(e -> view.getGuide().showGuidePopup());
    }
}
