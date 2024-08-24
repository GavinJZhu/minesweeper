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
        //setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(100,100));
        remainingMines = new NumberPanel();
        timer = new NumberPanel();
        this.add(remainingMines);
        setupRestartButton();
        this.add(timer);
    }
    private void setupRestartButton(){
        restartButton = new JButton();
        restartButton.setPreferredSize(new Dimension(50,50));
        //restartButton.setIcon();

        this.add(restartButton);
    }
}
