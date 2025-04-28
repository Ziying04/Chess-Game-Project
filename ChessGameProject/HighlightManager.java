// eng zi ying & wong hui ting
import javax.swing.*;
import java.awt.*;

// this is a view class (MVC)
//to display/clear the highlight on the board(single responsibility)
public class HighlightManager {
    private JButton[][] buttons; // Reference to the buttons in the view
    private JButton previouslySelectedButton = null;

    public HighlightManager(JButton[][] buttons) {
        this.buttons = buttons;
    }

    // eng zi ying
    // Highlights possible moves on the board
    public void highlightPossibleMoves(int[][] possibleMoves) {
        for (int row = 0; row < possibleMoves.length; row++) {
            for (int col = 0; col < possibleMoves[row].length; col++) {
                if (possibleMoves[row][col] == 1) {
                    buttons[row][col].setBackground(Color.GRAY);
                }
            }
        }
    }

    // Highlights the selected piece
    public void highlightPiece(int row, int col) {
        JButton selectedButton = buttons[row][col];
        selectedButton.setBackground(Color.LIGHT_GRAY);
        selectedButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        previouslySelectedButton = selectedButton;
    }

    // wong hui ting
    // Clears all highlights on the board
    public void dismissHighlights() {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }
        previouslySelectedButton = null;
    }
}
