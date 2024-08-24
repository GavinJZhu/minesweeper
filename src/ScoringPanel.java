import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
        this.add(createRestartButton());
        this.add(timer);
    }
    private JButton createRestartButton(){
        restartButton = new JButton(loadImage());
        restartButton.setSize(50,50);
        return restartButton;

    }
    public ImageIcon loadImage() {
        URL imageURL = getClass().getResource("restartbutton.png");
        return new ImageIcon(imageURL);
    }
}
