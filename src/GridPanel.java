import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel implements ActionListener {
    ScoringPanel m_scoringPanel;
    ReplayPanel m_replayPanel;
    Minesweeper m_minesweeper;
    GridPanel(){
        //add(new JButton("grid"));
        setup();
    }
    void setup(){
        GridLayout grid = new GridLayout(16,16);
        setLayout(grid);
        for (int i=0; i<256; i++){
           // String buttonNumber = Integer.toString(i);
            JButton button = new JButton();
            //button.setPreferredSize(new Dimension(5,5));
            button.addActionListener(this);
            this.add(button);
        }
    }
    public void restart(){
        System.out.println("if won then communicate win with replay panel");
    }
    public void setGamePanel(Minesweeper minesweeper){
       m_minesweeper = minesweeper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("debug GridPanel");
        m_minesweeper.restart();
    }
}
