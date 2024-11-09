import javax.swing.*;

public class NumberPanel extends JPanel {
    JTextField numberField;
    int numberValue;

    NumberPanel() {
        setup();
    }

    public void setup() {
        numberField = new JTextField("test");
        this.add(numberField);
    }

    public void setNumber(int number) {
        numberField.setText(String.valueOf(number));
    }

    public void restart() {

    }
}