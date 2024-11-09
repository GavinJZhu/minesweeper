import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class GridButtons {
    int m_rows = 16;
    int m_columns = 16;
    Random random = new Random();
    MinesweeperButton[][] m_arrayOfButtons = new MinesweeperButton[m_rows][m_columns];

    // JButton m_button;
    GridButtons() {
        setupButtons();
    }

    void setupButtons() {
        for (int i = 0; i < m_rows; i++) {
            for (int j = 0; j < m_columns; j++) {
                MinesweeperButton button = new MinesweeperButton();
                button.setIcon(changeButtonIcon(8)); //move to setButtonState
                button.setRowColumn(i, j);
                // Sets button location. Saves a button at row&column
                m_arrayOfButtons[i][j] = button;
            }
        }
        chooseBombs();
    }

    public void chooseBombs() {
        for (int i = 0; i < 40; i++) {
            int randomX = random.nextInt(15) + 1;
            int randomY = random.nextInt(15) + 1;
            MinesweeperButton bomb = m_arrayOfButtons[randomX][randomY];
            bomb.setButtonState(-1);
            int buttonState = bomb.getButtonState();
            System.out.println("bomb: " + randomX + ", " + randomY + " Button State: " + buttonState);
        }

    }

    public ImageIcon changeButtonIcon(int surroundingBombs) {
        String iconFileName = "bomb" + surroundingBombs;
        URL imageURL = getClass().getResource(iconFileName + ".png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public MinesweeperButton[][] getArrayOfButtons() {
        return m_arrayOfButtons;
    }
}
