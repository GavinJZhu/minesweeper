import javax.swing.*;

public class MinesweeperButton extends JButton {
    private int row;
    private int column;
    private int bombCount; //-1 = bomb, 0 = blank, 1-8 = surrounding bomb number

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
    public boolean isBomb(){
//        int buttonState = getButtonState();
//        if (buttonState == -1){
//            return true;
//        }
//        else{
//            return false;
//        }
        return getBombCount() == -1;
    }
}
