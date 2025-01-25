import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GridPanel extends JPanel implements ActionListener {
    ScoringPanel m_scoringPanel;
    ReplayPanel m_replayPanel;
    Minesweeper m_minesweeper;
    GridButtons gridButtons;
    int rows = 16;
    int columns = 16;

    GridPanel() {
        //add(new JButton("grid"));
        setup();
    }

    void setup() {
        GridLayout grid = new GridLayout(16, 16);
        setLayout(grid);
        setupButtons();
    }

    void setupButtons() {
        GridButtons buttons = new GridButtons();
        MinesweeperButton[][] array = buttons.getArrayOfButtons();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.add(array[i][j]);
                array[i][j].addActionListener(this);
            }
        }
    }

    public void restart() {
        System.out.println("if won then communicate win with replay panel");
    }

    public void setGamePanel(Minesweeper minesweeper) {
        m_minesweeper = minesweeper;
    }

    public ImageIcon changeButtonIcon(int surroundingBombs) {
        String iconFileName = "bomb" + surroundingBombs;
        URL imageURL = getClass().getResource(iconFileName + ".png");
        System.out.println(imageURL);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //get the button thats clicked
        MinesweeperButton button = (MinesweeperButton) e.getSource();
        int row = button.getRow();
        int column = button.getColumn();
        System.out.println("row = " + row+ ", column = " + column + ", bomb count = " + button.getBombCount());
        button.setRevealed(true);
        //set the icon of the button clicked
        button.setIcon(changeButtonIcon(button.getBombCount()));
        if (isBomb(button)) {
            System.out.println("Game Over");
        }
        else if(isBlank(button)){
            recursivelySpreadBlanks(button);
            if(isButtonRecursionNeeded(button)){
                recursivelySpreadBlanks(button);
            }
        }
    }
    //public boolean
    public boolean isBomb(MinesweeperButton button) {
        boolean isBomb = false;
        int buttonState = button.getBombCount();
        if(buttonState == -1){
            isBomb = true;
        }
        return isBomb;
    }
    public boolean isBlank(MinesweeperButton button) {
        boolean isBlank = false;
        int buttonState = button.getBombCount();
        if(buttonState == 0){
            isBlank = true;

        }
        return isBlank;
    }
    public void buttonRecursionFormat(MinesweeperButton button){
        if ( button == null )
        {
            // Button is invalid, does not existence....outside our GRID
        }
        else if ( button.isRevealed() )
        {
            // Button has already been revealed...no further processing/checks needed
        }
        else
        {
            // Reveal button and set the icon
            button.setRevealed( true );
            button.setIcon(changeButtonIcon(button.getBombCount()));
            if (button.isBlank())
            {
                // recursively spread blanks for this button
                recursivelySpreadBlanks(button);
            }
        }
    }
    public boolean isButtonRecursionNeeded(MinesweeperButton button){
//      int row = button.getRow();
//      int column = button.getColumn();
        boolean isButtonRecursionNeeded = false;
//      MinesweeperButton temp = null;
        if(!button.isRevealed() && button.isBlank()){
            isButtonRecursionNeeded = true;
        }
        return isButtonRecursionNeeded;
    }
    public void recursivelySpreadBlanks(MinesweeperButton button) {
        //need surrounding numbers to appear and also need recursion
        int row = button.getRow();
        int column = button.getColumn();
        MinesweeperButton temp = null;

        //get north button
        if(row>0){
        temp = GridButtons.getButton(row-1, column);
        buttonRecursionFormat(temp);
        }
        //get north east button
        temp = GridButtons.getButton(row-1, column+1);
        buttonRecursionFormat(temp);
        //get north west button
        temp = GridButtons.getButton(row-1, column-1);
        buttonRecursionFormat(temp);
        //get south button
        temp = GridButtons.getButton(row+1, column);
        buttonRecursionFormat(temp);
        //get south east button
        temp = GridButtons.getButton(row+1, column+1);
        buttonRecursionFormat(temp);
        //get south west button
        temp = GridButtons.getButton(row+1, column-1);
        buttonRecursionFormat(temp);
        //get west button
        temp = GridButtons.getButton(row, column-1);
        buttonRecursionFormat(temp);
        //get east button
        temp = GridButtons.getButton(row, column+1);
        buttonRecursionFormat(temp);
    }
}
