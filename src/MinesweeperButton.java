import javax.swing.*;

public class MinesweeperButton extends JButton {
    private int m_row;
    private int m_column;
    private int m_buttonState; //-1 = bomb, 0 = blank, 1-8 = surrounding bomb number

    public void setRowColumn(int row, int column){
        m_row = row;
        m_column = column;
    }
    public int getRow(){
        return m_row;
    }
    public int getColumn(){
        return m_column;
    }
    public int getButtonState(){
        return m_buttonState;
    }
    public void setButtonState(int buttonState){
        buttonState = m_buttonState;
    }


}
