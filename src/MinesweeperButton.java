import javax.swing.*;

public class MinesweeperButton extends JButton {
    private int row;
    private int column;
    private int buttonState; //-1 = bomb, 0 = blank, 1-8 = surrounding bomb number

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

    public int getButtonState() {
        return buttonState;
    }

    public void setButtonState(int buttonState) {
        this.buttonState = buttonState;
    }
    public boolean isBomb(){
//        int buttonState = getButtonState();
//        if (buttonState == -1){
//            return true;
//        }
//        else{
//            return false;
//        }
        return getButtonState() == -1;
    }
}
