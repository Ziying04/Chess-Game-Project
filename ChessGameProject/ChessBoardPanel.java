// teow wei ting & wong hui ting
import javax.swing.*;
import java.awt.*;

// this is a view class (MVC)
// to display the chessboard panel (buttons to interact with player) (single responsibility)
public class ChessBoardPanel extends JPanel {
    private JButton[][] buttons;
    private final int buttonSize;
    private final int rows =8;
    private final int cols=5;
    
   // teow wei ting 
   //create the chessboard 
    public ChessBoardPanel(int buttonSize) {
        this.buttonSize = buttonSize;
        this.setLayout(new GridLayout(rows, cols)); // set to a new Gridlayout to create a grid of buttons (8x5)
        this.setPreferredSize(new Dimension(buttonSize * cols, buttonSize * rows)); // set preferred size of panel
  
        //create buttons to the board 8x5
        buttons = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setPreferredSize(new Dimension(buttonSize, buttonSize)); //make it resizable 
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                this.add(button); //add it into the panel 
                buttons[i][j] = button; // store into array
            }
        }
    }
     
    //expose the necessary info 
    // teow wei ting
    public JButton getButton(int row, int col) {
        return buttons[row][col]; // get specific button at given row and column
    }
    
    // teow wei ting
    public JButton[][] getButtons(){
        return buttons; // get button of 2D array
    }
    
    //teow wei ting
    // override, to dynamically adjust the panel size based on parent container
    public Dimension getPreferredSize() {
        int size = Math.min(getParent().getWidth(), getParent().getHeight()); // take smaller dimension
        return new Dimension(size, size); // return square dimension
    }
    
    //wong hui ting
    //update the square with image at the specified row and column (indicating the cell occupied by a piece)
    public void updateSquare(int row, int col, String imagePath) {
        buttons[row][col].setIcon(null); //remove any existing icon
        if (imagePath != null) {//set a new icon if the image path is not null
            buttons[row][col].setIcon(loadAndResizeIcon(imagePath));
        }
    }

    // wong hui ting
    public void clearSquare(int row, int col) {
        buttons[row][col].setIcon(null); //remove the icon
    }

    //teow wei ting
    // resize the img icon accodingly to the button size (make it visible as a whole)
    private ImageIcon loadAndResizeIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath); // load image
        Image img = icon.getImage(); // get ori image
        Image resizedImg = img.getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH); // resize
        return new ImageIcon(resizedImg); // return image as an icon
    }
    
}
