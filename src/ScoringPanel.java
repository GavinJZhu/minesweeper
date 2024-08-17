import javax.swing.*;
import java.awt.*;

public class ScoringPanel extends JPanel {
    NumberPanel remainingMines;
    JButton restartButton;
    NumberPanel timer;

    ScoringPanel() {
       setup();
    }
    public void setup(){
        remainingMines = new NumberPanel();
        restartButton = new JButton();
        timer = new NumberPanel();
        setLayout(new GridLayout(1, 3));
        this.add(remainingMines);
        this.add(restartButton);
        this.add(timer);
    }
}
