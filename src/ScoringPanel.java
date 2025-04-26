import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ScoringPanel extends JPanel implements ActionListener {
    NumberPanel m_remainingMinesPanel;
    JButton m_restartButton;
    NumberPanel m_timerPanel;
    Minesweeper m_minesweeper;
    public Timer timer;
    int time = 0;
    int remainingMines;

    ScoringPanel() {
        setPreferredSize(new Dimension(100, 100));
        m_remainingMinesPanel = new NumberPanel();
        m_timerPanel = new NumberPanel();
        m_remainingMinesPanel.setNumber(40);
        this.add(m_remainingMinesPanel);
        this.add(createRestartButton());
        this.add(m_timerPanel);
        this.addTimer();
    }

    private JButton createRestartButton() {
        m_restartButton = new JButton();
        m_restartButton.setIcon(loadImageRestartButton());
        m_restartButton.addActionListener(this);
        m_restartButton.setPreferredSize(new Dimension(50, 50));
        return m_restartButton;
    }

    public ImageIcon loadImageRestartButton() {
        URL imageURL = getClass().getResource("restartbutton.png");
        return new ImageIcon(imageURL);
    }

    public void setGamePanel(Minesweeper minesweeper) {
        m_minesweeper = minesweeper;
    }

    //Set the value listed in the panel to number of bombs - flags
    public void setRemainingMines(int flags, int bombs) {
        remainingMines = bombs - flags;
        m_remainingMinesPanel.setNumber(remainingMines);
        m_remainingMinesPanel.revalidate();
    }

    public void addTimer() {
        //1 second delay
        int delay = 1000;
        this.timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update the panel each second that passes
                m_timerPanel.setNumber(time);
                m_timerPanel.revalidate();
                time += 1;
            }
        });
    }

    public void scoringPanelRestart() {
        time = 0;
        setRemainingMines(0, 40);
        m_minesweeper.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoringPanelRestart();
    }
}
