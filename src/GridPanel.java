import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class GridPanel extends JPanel implements MouseListener, ActionListener {
    ScoringPanel m_scoringPanel;
    ReplayPanel m_replayPanel;
    Minesweeper m_minesweeper;
    GridButtons m_gridButtons = new GridButtons();
    int revealedAmount = 0;
    int flaggedAmount = 0;
    int rows = 16;
    int columns = 16;
    boolean isTimerStarted = false;
    //make constructor take scoring panel, update grid panel to take scoring panel, set member variable to scoring panel
    //set remaining bombs to be 40
    GridPanel(ScoringPanel scoringPanel) {
        //add(new JButton("grid"));
        setup();
        scoringPanel = new ScoringPanel();
    }

    void setup() {
        GridLayout grid = new GridLayout(16, 16);
        setLayout(grid);
        setupButtons();
    }

    void setupButtons() {
        this.removeAll();
        // Retrieve our buttons from m_gridButtons
        MinesweeperButton[][] array = m_gridButtons.getArrayOfButtons();

        // now setup each button
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //adds button to panel
                this.add(array[i][j]);

                //adds mouseListener to button
                array[i][j].addMouseListener(this);
                array[i][j].addActionListener(this);
            }
        }
    }

    public void restart() {
        System.out.println("Restart from GridPanel");


        //create new buttons (m_gridButtons) on restart
        m_gridButtons.recreateNewButtons();

        //add new buttons  (m_gridButtons) to our panel (GridPanel)
        setupButtons();

        this.revalidate();
        revealedAmount = 0;

    }

    public void setGamePanel(Minesweeper minesweeper) {
        m_minesweeper = minesweeper;
        m_scoringPanel = minesweeper.scoringPanel;
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
    public boolean setTimerStarted(Boolean timerStarted){
        isTimerStarted = timerStarted;
        return isTimerStarted;
    }
    public boolean getTimerStarted(Boolean isTimerStarted){
        return isTimerStarted;
    }
    //changes icon when clicked
    public void setButtonStatus(MinesweeperButton button) {
        if (button == null) {
            // Button is invalid, does not exist....outside our GRID
        } else if (button.isRevealed()) {
            // Button has already been revealed...no further processing/checks needed
        } else {
            // Reveal button and set the icon
            button.setRevealed(true);
            button.setIcon(changeButtonIcon(button.getBombCount()));
            if (!isBomb(button)) {
                revealedAmount++;
            }
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
        } else if (!isButtonRecursionNeeded(button)) {
            //if false, stop recursion
            setButtonStatus(button);
        } else {
            //now spread surrounding buttons
            //get row and column array indices
            int row = button.getRow();
            int column = button.getColumn();
            //set the button's status to be revealed and sets the button's icon
            setButtonStatus(button);
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MinesweeperButton button = (MinesweeperButton) e.getSource();
        //flag a square
        if (e.getButton() == MouseEvent.BUTTON3 && !button.isFlagged() && !button.isRevealed()) {
            button.setFlagged(true);
            // negative 2 = flag icon
            button.setIcon(changeButtonIcon(-2));
            //call scoringPanel.remainingMinesPanel with flags and bombs
            flaggedAmount+=1;
            m_scoringPanel.setRemainingMines(flaggedAmount,40);
        }
        //unflag a square
        else if (e.getButton() == MouseEvent.BUTTON3 && button.isFlagged()) {
            button.setIcon(null);
            button.setRevealed(false);
            button.setFlagged(false);
            //call scoringPanel.remainingMinesPanel with flags and bombs
            flaggedAmount-=1;
            m_scoringPanel.setRemainingMines(flaggedAmount,40);
        }
        //left clicking a square
        else if(e.getButton() == MouseEvent.BUTTON1 && !button.isFlagged()){
            if (isBomb(button)) {
                setButtonStatus(button);
                JOptionPane.showConfirmDialog(null, "You lose... Want to try again?", "Oh no!", JOptionPane.YES_NO_OPTION);
            } else if (isBlank(button)) {
                spreadBlanks(button);
            } else {
                setButtonStatus(button);
            }
            if (revealedAmount == 216){
                JOptionPane.showConfirmDialog(null, "You win! Want to play another?", "Woo hoo!", JOptionPane.YES_NO_OPTION);
            }
        }
        System.out.println(revealedAmount);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(!isTimerStarted){
           setTimerStarted(true);
           m_scoringPanel.timer.start();
       }
    }
}
