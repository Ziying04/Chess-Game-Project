// venice goh kit yee
import java.awt.*;
import javax.swing.*;

// this is a view class (MVC)
//to display the messagepanel (show messages to the player) (single responsibility)
public class MessagePanel extends JFrame {
    private JTextArea messageArea;
    
    //popup message
    public void displayWinningMessage(String winningTeam) {
        JOptionPane.showMessageDialog(
            this, 
            "Game Over! " + winningTeam + " team wins!", 
            "Game Over", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(
            this, // centers the dialog box on main frame
            message, 
            "Game Message", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
