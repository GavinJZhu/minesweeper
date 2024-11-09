import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayPanel extends JPanel implements ActionListener {
    GridPanel m_gridPanel;
    ScoringPanel m_scoringPanel;
    Minesweeper m_minesweeper;
    JSlider slider = new JSlider();
    JButton play = new JButton("play");

    ReplayPanel() {
        setup();
    }

    void setup() {
        play.addActionListener(this);
        this.add(play);
        this.add(slider);
    }

    public void restart() {
        System.out.println("replay clicks and cursor movements on the minesweeper board");
        //replay clicks and cursor movements on the minesweeper board
    }

    public void setGamePanel(Minesweeper minesweeper) {
        m_minesweeper = minesweeper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_minesweeper.restart();
    }
}
