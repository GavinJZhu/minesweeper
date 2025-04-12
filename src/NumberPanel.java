import javax.swing.*;

public class NumberPanel extends JPanel {
    JTextField numberField;
    int numberValue;

    NumberPanel() {
        setup();
    }

    public void setup() {
        numberField = new JTextField("0");
        numberField.setEnabled(false);
        this.add(numberField);
    }

    public void setNumber(int number) {
        numberField.setText(String.valueOf(number));
        numberField.revalidate();
        this.revalidate();
    }

    public void restart() {

    }
}