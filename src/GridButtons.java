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
    MinesweeperButton[][] arrayOfButtons = new MinesweeperButton[m_rows][m_columns];

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
                arrayOfButtons[i][j] = button;
            }
        }
        chooseBombs();
    }

    public void chooseBombs() {
        for (int i = 0; i < 40; i++) {
            int randomRow = random.nextInt(15) + 1;
            int randomColumn = random.nextInt(15) + 1;
            MinesweeperButton bomb = arrayOfButtons[randomRow][randomColumn];
            bomb.setButtonState(-1);
            int buttonState = bomb.getButtonState();
            System.out.println("bomb: " + randomRow + ", " + randomColumn + " Button State: " + buttonState);
        }

    }

    private void surroundingBombs(MinesweeperButton button) {
        int row = button.getRow();
        int column = button.getColumn();
        int surroundingBombs = 0;
        if (column > 0) {
            int buttonStateLeft = arrayOfButtons[row][column - 1].getButtonState();
            if (buttonStateLeft == -1){
                surroundingBombs ++;
            }
            if (row>0){
                int buttonStateTopLeft = arrayOfButtons[row-1][column - 1].getButtonState();
                if(buttonStateTopLeft == -1){
                    surroundingBombs ++;
                }
            }
            if (row<15){
                int buttonStateBottomLeft = arrayOfButtons[row+1][column - 1].getButtonState();
                if(buttonStateBottomLeft == -1){
                    surroundingBombs ++;
                }
            }
        }
        if (column < 15) {
            int buttonStateRight = arrayOfButtons[row][column+1].getButtonState();
            if (buttonStateRight == -1){
                surroundingBombs ++;
            }
            if (row>0){
                int buttonStateTopRight = arrayOfButtons[row-1][column + 1].getButtonState();
                if(buttonStateTopRight == -1){
                    surroundingBombs ++;
                }
            }
            if (row<15){
                int buttonStateBottomLeft = arrayOfButtons[row+1][column + 1].getButtonState();
                if(buttonStateBottomLeft == -1){
                    surroundingBombs ++;
                }
            }
        }
    }
}