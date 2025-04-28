// teow wei ting
import javax.swing.*;

// this is a view class (MVC)
// to display the guide interface and define the guide content(single responsibility)
public class Guide {
    private String content;

    public Guide() {
        // Define the guide content here
        this.content = """
            Welcome to the Chess Game!
            
            Instructions:
            1. The Ram piece can only move forward, 1 step. If it reaches the end of the board, it turns around and 
               starts heading back the other way. It cannot skip over other pieces.
            2. The Biz piece moves in a 3x2 L shape in any orientation (kind of like the knight in standard 
               chess.) This is the only piece that can skip over other pieces.
            3. The Tor piece can move orthogonally only but can go any distance. It cannot skip over other pieces.
               However, after 2 turns, it transforms into the Xor piece.
            4. The Xor piece can move diagonally only but can go any distance. It cannot skip over other pieces. 
               However, after 2 turns, it transforms into the Tor piece.
            5. The Sau piece can move only one step in any direction. The game ends when the Sau is captured by 
               the other side.
               
            To be caution:
            1. None of the pieces are allowed to skip over other pieces, except for Biz.
            2. After 2 turns (counting one blue move and one red move as one turn), all Tor pieces will turn into Xor pieces, and all
               Xor pieces will turn into Tor pieces. 
            
            Enjoy the game!
            """;
    }

    public String getContent() {
        return content;
    }

    //create the guide popup
    public void showGuidePopup() {
        // Create a dialog to display the guide content
        JDialog guideDialog = new JDialog((JFrame) null, "Game Guide", true);
        guideDialog.setSize(500, 400);

        // Create a scrollable text area
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the dialog
        guideDialog.add(scrollPane);

        // Set the dialog location and make it visible
        guideDialog.setLocationRelativeTo(null);
        guideDialog.setVisible(true);
    }
}
