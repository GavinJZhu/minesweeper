import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ScoringPanel extends JPanel implements ActionListener {
    NumberPanel remainingMinesPanel;
    JButton restartButton;
    NumberPanel timerPanel;
    Minesweeper minesweeper;
    public Timer timer;
    int time = 0;
    int remainingMines;

    ScoringPanel() {
        setPreferredSize(new Dimension(100, 100));
        remainingMinesPanel = new NumberPanel();
        timerPanel = new NumberPanel();
        remainingMinesPanel.setNumber(40);
        this.add(remainingMinesPanel);
        this.add(createRestartButton());
        this.add(timerPanel);
        this.addTimer();
    }

    private JButton createRestartButton() {
        restartButton = new JButton();
        restartButton.setIcon(loadImageRestartButton());
        restartButton.addActionListener(this);
        restartButton.setPreferredSize(new Dimension(50, 50));
        return restartButton;
    }

    public ImageIcon loadImageRestartButton() {
        URL imageURL = getClass().getResource("restartbutton.png");
        return new ImageIcon(imageURL);
    }

    public void setGamePanel(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    public void setRemainingMines(int flags, int bombs) {
        //Set the value listed in the panel to number of bombs - flags
        remainingMines = bombs - flags;
        remainingMinesPanel.setNumber(remainingMines);
        remainingMinesPanel.revalidate();
    }

    public void addTimer() {
        //1 second delay
        int delay = 1000;
        this.timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update the panel each second that passes
                timerPanel.setNumber(time);
                timerPanel.revalidate();
                time++;
            }
        });
    }

    public void scoringPanelRestart() {
        time = 0;
        setRemainingMines(0, 40);
        minesweeper.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoringPanelRestart();
    }
}
