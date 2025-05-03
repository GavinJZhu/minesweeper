import java.util.Random;

public class GridButtons {
    static int rows = 16;
    static int columns = 16;
    Random random = new Random();
    static MinesweeperButton[][] arrayOfButtons = new MinesweeperButton[rows][columns];

    // find icon for blank squares and bombs
    GridButtons() {
        createGrid();
    }

    private void createGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                MinesweeperButton button = new MinesweeperButton();
                // Sets button location. Saves a button at row&column
                button.setRowColumn(i, j);
                //****set each button icon to be blank****
                arrayOfButtons[i][j] = button;
                //defaults each button to 0 bombs
                button.setBombCount(0);
            }
        }
        // chooses locations for bombs and sets their state to -1
        setBombs();
        // sets each (nonBomb) button's bombs corresponding to the number of surrounding bombs
        setNonBombs();
    }

    public void recreateNewButtons() {
        createGrid();
    }

    //assigns row and column to all buttons, then sets state

    public void setBombs() {
        int numberOfBombs = 0;
        while (numberOfBombs < 40) {
            int randomRow = random.nextInt(15) + 1;
            int randomColumn = random.nextInt(15) + 1;
            MinesweeperButton bomb = arrayOfButtons[randomRow][randomColumn];
            if (bomb.getBombCount() == 0) {
                bomb.setBombCount(-1);
                numberOfBombs++;
            }
        }
    }

    public void setNonBombs() {
        //1. create two for loops of 16... see setupButtons method
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //2.    check if a button has a state of -1
                MinesweeperButton button = arrayOfButtons[i][j];
                int bombCount = button.getBombCount();
                //3.    if button's state is -1, move on to next button
                //4.    if button's state is not -1, check number of surrounding bombs... call a method getSurroundingBombs
                //skip button because bombs are already set
                if (bombCount != -1) {
                    //get number of bombs surrounding a button
                    int surroundingBombs = getSurroundingBombs(button);
                    if (surroundingBombs > 0) {
                        button.setBombCount(surroundingBombs);
                    }
                }
            }
        }
    }
    public MinesweeperButton[][] getArrayOfButtons() {
        return arrayOfButtons;
    }
    public static MinesweeperButton getButton(int row, int column) {
        MinesweeperButton button = null;
        //if row and column are valid, get the button; otherwise return null
        if ((row >= 0) && (row < rows) && (column >= 0) && (column < columns)) {
            // valid button....inside our GRID
            button = arrayOfButtons[row][column];
        }
        return button;
    }

    public int getSurroundingBombs(MinesweeperButton button) {
        int row = button.getRow();
        int column = button.getColumn();
        int surroundingBombs = 0;

        MinesweeperButton temp;

        //get north button
        temp = getButton(row - 1, column);
        surroundingBombs += isNeighborBomb(temp);
        //get northeast button
        temp = getButton(row - 1, column + 1);
        surroundingBombs += isNeighborBomb(temp);
        //get northwest bomb
        temp = getButton(row - 1, column - 1);
        surroundingBombs += isNeighborBomb(temp);
        //get south bomb
        temp = getButton(row + 1, column);
        surroundingBombs += isNeighborBomb(temp);
        //get southeast bomb
        temp = getButton(row + 1, column + 1);
        surroundingBombs += isNeighborBomb(temp);
        //get southwest bomb
        temp = getButton(row + 1, column - 1);
        surroundingBombs += isNeighborBomb(temp);
        //get west bomb
        temp = getButton(row, column - 1);
        surroundingBombs += isNeighborBomb(temp);
        //get east bomb
        temp = getButton(row, column + 1);
        surroundingBombs += isNeighborBomb(temp);
        return surroundingBombs;
    }

    private static int isNeighborBomb(MinesweeperButton temp) {
        if ((temp != null) && (temp.isBomb())) {
            return 1;
        }
        return 0;
    }
}