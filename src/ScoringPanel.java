import javax.swing.*;
import java.awt.*;

public class ScoringPanel extends JPanel {
    JButton remainingMines;
    JButton restartButton;
    JButton timer;

    ScoringPanel() {
        remainingMines = new JButton();
        restartButton = new JButton();
        timer = new JButton();
        setLayout(new GridLayout(3, 1));
    }
}
