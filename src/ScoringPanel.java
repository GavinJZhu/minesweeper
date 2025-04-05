import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ScoringPanel extends JPanel implements ActionListener {
    NumberPanel m_remainingMinesPanel;
    JButton m_restartButton;
    NumberPanel m_timerPanel;
    ReplayPanel m_replayPanel;
    Minesweeper m_minesweeper;
    private Timer timer;

    ScoringPanel() {
        setup();
    }

    public void setup() {
        //setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(100, 100));
        m_remainingMinesPanel = new NumberPanel();
        m_timerPanel = new NumberPanel();
        this.add(m_remainingMinesPanel);
        this.add(createRestartButton());
        addTimer();
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

    public void restart() {
        System.out.println("reset game, clear game out of replay panel");
    }

    public void setGamePanel(Minesweeper minesweeper) {
        m_minesweeper = minesweeper;
    }
    //Set the value listed in the panel to number of bombs - flags
    public void setRemainingMines(int flags, int bombs){
        int remainingMines = bombs-flags;
        m_remainingMinesPanel.setNumber(remainingMines);
        m_remainingMinesPanel.revalidate();
    }
    public void addTimer(){
        //1 second delay
        int delay = 1000;
        this.timer = new Timer(delay, new ActionListener() {
            int time = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                //update the panel each second that passes
                m_timerPanel.setNumber(time);
                m_timerPanel.revalidate();
                time +=1;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_minesweeper.restart();
    }
}
