// venice goh kit yee
import javax.swing.*;

// this is a view class (MVC)
//to display the menu bar and item on the board (single responsibility)
public class MenuBarManager {
    private JMenuBar menuBar;
    private JMenuItem saveGameItem, newGameItem, loadGameItem, exitItem, guideItem;
    
    //create the menu bar
    public MenuBarManager() {
        menuBar = new JMenuBar();

        // File menu for game actions
        JMenu fileMenu = new JMenu("File");
        newGameItem = new JMenuItem("New Game");
        loadGameItem = new JMenuItem("Load Game");
        saveGameItem = new JMenuItem("Save Game");
        exitItem = new JMenuItem("Exit");
        
        // Add menu items to File menu
        fileMenu.add(newGameItem);
        fileMenu.add(loadGameItem);
        fileMenu.add(saveGameItem);
        fileMenu.add(exitItem);

        // Guide menu
        JMenu guideMenu = new JMenu("Guide");
        guideItem = new JMenuItem("Show Guide");
        guideMenu.add(guideItem);

        // Add menu to menu bar
        menuBar.add(fileMenu);
        menuBar.add(guideMenu);
    }

    //expose the menu item and bar to other classes
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenuItem getSaveGameItem() {
        return saveGameItem;
    }

    public JMenuItem getNewGameItem() {
        return newGameItem;
    }
    
    public JMenuItem getLoadGameItem(){
        return loadGameItem;
    }
    
    public JMenuItem getGuideItem(){
        return guideItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }
}
