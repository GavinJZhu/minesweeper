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
    // find icon for blank squares and bombs
    GridButtons() {
        setupButtons();
    }

    void setupButtons() {
        for (int i = 0; i < m_rows; i++) {
            for (int j = 0; j < m_columns; j++) {
                MinesweeperButton button = new MinesweeperButton();
                //button.setIcon(getButtonIcon(8)); //move to setButtonState
                button.setRowColumn(i, j);
                // Sets button location. Saves a button at row&column
                arrayOfButtons[i][j] = button;
                //****set each button icon to be blank****
            }
        }
        setBombs();
        setNonBombs();
    }

    public void setBombs() {
        for (int i = 0; i < 40; i++) {
            int randomRow = random.nextInt(15) + 1;
            int randomColumn = random.nextInt(15) + 1;
            MinesweeperButton bomb = arrayOfButtons[randomRow][randomColumn];
            bomb.setButtonState(-1);
            int buttonState = bomb.getButtonState();
            System.out.println("bomb: " + randomRow + ", " + randomColumn + " Button State: " + buttonState);
            //surroundingBombs(bomb); does not work yet
        }
    }
    public void setNonBombs(){
        //1. create two for loops of 16... see setupButtons method
        for (int i = 0; i < m_rows; i++) {
            for (int j = 0; j < m_columns; j++) {
                //2.    check if a button has a state of -1
                MinesweeperButton button = arrayOfButtons[i][j];
                int buttonState = button.getButtonState();
                //3.    if button's state is -1, move on to next button
                if (buttonState == -1) {
                    //move on to the next column
                }
                //4.    if button's state is not -1, check number of surrounding bombs... call a method getSurroundingBombs
                else {
                    //get number of bombs surrounding a button
                    button.setButtonState(getSurroundingBombs(button));
                    button.setIcon(getButtonIcon(buttonState));
                }
            }
        }
    }
    //-1 = bomb, 0 = no surrounding bombs, 1-8 = 1-8 surrounding bombs
    public ImageIcon getButtonIcon(int surroundingBombs) {
        ImageIcon defaultIcon = new ImageIcon();
        //create filename based off surrounding bombs
        String iconFileName = "bomb" + surroundingBombs;
        //create URL for file
        URL imageURL = getClass().getResource(iconFileName + ".png");
        //check if imageURL is null/empty
        //System.out.println(imageURL);
        if(imageURL == null){
            //got an invalid url... file doesn't exist
            System.out.println("imageURL is null, iconFileName:"+iconFileName);

        }
        else{
            //got a valid url
            BufferedImage image = null;
            try {
                //reads the url/file and gets an image based off it, BufferedImage
                image = ImageIO.read(imageURL);
            }
            catch (IOException e) {
                //crashes program if there was an error reading file
                throw new RuntimeException(e);
            }
            Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return defaultIcon;
    }
    public MinesweeperButton[][] getArrayOfButtons(){
        return arrayOfButtons;
    }

    public MinesweeperButton getButton(int row, int column){
        MinesweeperButton button = null;
        //if row and column are valid, get the button; otherwise return null
        /*
        if ((row >= 0) && (row <= m_rows-1)){
            if((column >= 0) && (column<=m_columns-1)) {
                button = arrayOfButtons[row][column];
            }
        }
        */


        if ((row <= 0) || (row >= m_rows)){
            // row is invalid
        }
        else if ((column <= 0) || (column >= m_columns))
        {
            // column is invalid
        }
        else
        {
            // valid button
            button = arrayOfButtons[row][column];
        }

        return button;
    }

    public int getSurroundingBombs(MinesweeperButton button) {
        int row = button.getRow();
        int column = button.getColumn();
        int surroundingBombs = 0;

        MinesweeperButton temp = null;

        //get north button
        temp = getButton(row-1, column);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get north east button
        temp = getButton(row-1, column+1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get north west bomb
        temp = getButton(row-1, column-1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get south bomb
        temp = getButton(row+1, column);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get south east bomb
        temp = getButton(row+1, column+1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get south west bomb
        temp = getButton(row+1, column-1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get west bomb
        temp = getButton(row, column-1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        //get east bomb
        temp = getButton(row, column+1);
        if ( (temp != null) && (temp.isBomb()) ){
            surroundingBombs++;
        }
        /*if (column > 0) {
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
                int buttonStateBottomRight = arrayOfButtons[row+1][column + 1].getButtonState();
                if(buttonStateBottomRight == -1){
                    surroundingBombs ++;
                }
            }
        }
        if (row>0){
            int buttonStateTop = arrayOfButtons[row-1][column].getButtonState();
            if(buttonStateTop == -1){
                surroundingBombs ++;
            }
        }
        if (row<15){
            int buttonStateBottom = arrayOfButtons[row+1][column].getButtonState();
            if(buttonStateBottom == -1){
                surroundingBombs ++;
            }
        }
         */
        return surroundingBombs;
    }
}