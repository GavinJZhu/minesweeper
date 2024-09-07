import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ScoringPanel extends JPanel {
    NumberPanel m_remainingMinesPanel;
    JButton m_restartButton;
    NumberPanel m_timerPanel;
    GridPanel m_gridPanel;
    ReplayPanel m_replayPanel;
    ScoringPanel() {
       setup();
    }
    public void setup(){
        //setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(100,100));
        m_remainingMinesPanel = new NumberPanel();
        m_timerPanel = new NumberPanel();
        this.add(m_remainingMinesPanel);
        this.add(createRestartButton());
        this.add(m_timerPanel);
    }
    private JButton createRestartButton(){
        m_restartButton = new JButton(loadImageRestartButton());
        m_restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            restart();
            System.out.println("testing action listener");
            }
        });
        m_restartButton.setSize(50,50);
        return m_restartButton;

    }
    public ImageIcon loadImageRestartButton() {
        URL imageURL = getClass().getResource("restartbutton.png");
        return new ImageIcon(imageURL);
    }
    public void restart(){
        setGamePanel(m_gridPanel, m_replayPanel);
    }
    public void setGamePanel(GridPanel gridPanel, ReplayPanel replayPanel){
        System.out.println("testScoring");
    }
}
