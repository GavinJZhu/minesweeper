import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
        //set the icon of the button clicked
        button.setIcon(changeButtonIcon(button.getBombCount()));
        if (isBomb(button)) {
            System.out.println("Game Over");
        }

    }
    void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {

        }
    }

    public boolean isBomb(MinesweeperButton button) {
        boolean isBomb = false;
        int buttonState = button.getBombCount();
        return isBomb;
    }
}
