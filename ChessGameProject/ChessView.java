// teow wei ting & eng zi ying
import javax.swing.*;
import java.awt.*;

// this is a view class (MVC)
//acts as the middle layer that coordinates between the subcomponent of the view classes
//also used to exposed all the subcomponent of the view class to the controller class
public class ChessView extends JFrame {
    private ChessBoardPanel chessBoardPanel;
    private MenuBarManager menuBarManager;
    private HighlightManager hightlightManager;
    private MessagePanel  messagePanel;
    private Guide guide;

    //create the game GUI main interface & Initialize subcomponents
    public ChessView() {
        //set up fo the program GUI
        setTitle("Kwazam Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize view class subcomponents
        chessBoardPanel = new ChessBoardPanel(75);
        menuBarManager = new MenuBarManager();
        hightlightManager = new HighlightManager(chessBoardPanel.getButtons());
        messagePanel = new MessagePanel();
        guide = new Guide();

        // Set menu bar
        setJMenuBar(menuBarManager.getMenuBar());

        // Add components to the frame
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // Background color for the padding
        mainPanel.add(chessBoardPanel); //add the chessboard into the frame

        add(mainPanel);
        setSize(700, 800);
        setMinimumSize(new Dimension(100, 200)); //for resizable frame 
        setLocationRelativeTo(null);
    }

    // Expose subcomponent methods as needed
    public ChessBoardPanel getChessBoardPanel() {
        return chessBoardPanel;
    }

    public Guide getGuide(){
        return guide;
    }
    
    public MenuBarManager getMenuBarManager() {
        return menuBarManager;
    }
    
    public HighlightManager getHightlightManager(){
        return hightlightManager;
    }
    
    public MessagePanel getMessagePanel(){
        return messagePanel;
    }
    
}
