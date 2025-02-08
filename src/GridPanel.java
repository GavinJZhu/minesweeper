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
        //System.out.println(imageURL);
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
        System.out.println("row = " + row + ", column = " + column + ", bomb count = " + button.getBombCount());
        if (isBomb(button)) {
            setButtonStatus(button);
            System.out.println("Game Over");
        } else if (isBlank(button)) {
            spreadBlanks(button);
//            recursivelySpreadBlanks(button);
//            if(isButtonRecursionNeeded(button)){
//                recursivelySpreadBlanks(button);
            //}
        }
        else {
            setButtonStatus(button);
        }

    }

    //public boolean
    public boolean isBomb(MinesweeperButton button) {
        boolean isBomb = false;
        int buttonState = button.getBombCount();
        if (buttonState == -1) {
            isBomb = true;
        }
        return isBomb;
    }

    public boolean isBlank(MinesweeperButton button) {
        boolean isBlank = false;
        int buttonState = button.getBombCount();
        if (buttonState == 0) {
            isBlank = true;

        }
        return isBlank;
    }

    public void setButtonStatus(MinesweeperButton button) {
        if (button == null) {
            // Button is invalid, does not exist....outside our GRID
        } else if (button.isRevealed()) {
            // Button has already been revealed...no further processing/checks needed
        } else {
            // Reveal button and set the icon
            button.setRevealed(true);
            button.setIcon(changeButtonIcon(button.getBombCount()));
        }
    }

    public boolean isButtonRecursionNeeded(MinesweeperButton button) {
        boolean isButtonRecursionNeeded = false;
        if (button == null) {
            //button off the board
        }
        else if (!button.isBlank()){
            //recursion not needed, not a blank
        } else if (button.isRevealed()) {
            //recursion not needed, already revealed
        } else{
            isButtonRecursionNeeded = true;
        }
        return isButtonRecursionNeeded;
    }
    public void spreadBlanks(MinesweeperButton button) {
        if (button == null) {
            //stop recursion, invalid button
            System.out.println("button is outside the board, stop spread blanks");
        } else if (!isButtonRecursionNeeded(button)) {
            //if false, stop recursion
            System.out.println("if button is already revealed and/or is blank, don't spread to that square");
            setButtonStatus(button);
        } else {
            //set the button's status to be revealed and sets the button's icon
            setButtonStatus(button);

            //now spread surrounding buttons
            //get row and column array indices
            int row = button.getRow();
            int column = button.getColumn();

            System.out.println("recursively spreading blanks for north button, " + row + ", " + column);
            //get north button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row - 1, column));
            //get northeast button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row - 1, column + 1));
            //get northwest button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row - 1, column - 1));
            //get east button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row, column + 1));
            //get west button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row, column - 1));
            //get south button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row + 1, column));
            //get southeast button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row + 1, column + 1));
            //get southwest button and recursively spread blanks
            spreadBlanks(GridButtons.getButton(row + 1, column - 1));
        }
    }
}
