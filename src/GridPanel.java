import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    ScoringPanel m_scoringPanel;
    ReplayPanel m_replayPanel;
    GridPanel(){
        //add(new JButton("grid"));
        setup();
    }
    void setup(){
        setLayout(new GridLayout(16,16));
        for (int i=0; i<256; i++){
           // String buttonNumber = Integer.toString(i);
            JButton button = new JButton();
            this.add(button);
        }
    }
    public void restart(){
        setGamePanel(m_scoringPanel,m_replayPanel);
    }
    public void setGamePanel(ScoringPanel scoringPanel, ReplayPanel replayPanel){
        System.out.println("testingGridPanel");
    }
}
