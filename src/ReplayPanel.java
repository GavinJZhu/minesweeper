import javax.swing.*;

public class ReplayPanel extends JPanel {
    GridPanel m_gridPanel;
    ScoringPanel m_scoringPanel;
    JSlider slider = new JSlider();
    JButton play = new JButton("play");

    ReplayPanel(){
       setup();
    }
    void setup(){
        this.add(play);
        this.add(slider);
    }
    public void restart(){
        System.out.println("testing replay panel");
    }
    public void setGamePanel(GridPanel gridPanel, ScoringPanel scoringPanel){
        m_gridPanel = gridPanel;
        m_scoringPanel = scoringPanel;
    }
}
