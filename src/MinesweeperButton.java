import javax.swing.*;

public class MinesweeperButton extends JButton {
    private int row;
    private int column;
    private int bombCount; //-1 = bomb, 0 = blank, 1-8 = surrounding bomb number
    private boolean revealed = false;
    private boolean flagged = false;

    public void setRowColumn(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public boolean isBomb() {
        return bombCount == -1;
    }


    public boolean isBlank() {
        return bombCount == 0;
    }

    public void setRevealed(boolean setRevealed) {
        revealed = setRevealed;
    }

    public boolean getRevealed() {
        return revealed;
    }

    public void setFlagged(boolean isFlagged) {
        flagged = isFlagged;
    }

    public boolean getFlagged() {
        return flagged;
    }

}
