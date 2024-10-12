import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GridPanel extends JPanel implements ActionListener {
    ScoringPanel m_scoringPanel;
    ReplayPanel m_replayPanel;
    Minesweeper m_minesweeper;
    int rows = 16;
    int columns = 16;
    JButton m_button;
    GridPanel(){
        //add(new JButton("grid"));
        setup();
    }
    void setup(){
        GridLayout grid = new GridLayout(16,16);
        setLayout(grid);
        setupButtons();
    }
    void setupButtons(){
        JButton[][] array = new JButton[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                 JButton button = new JButton();
                 button.setIcon(changeButtonIcon());
                 button.setName("test");
                 this.add(button);
                 button.addActionListener(this);
            }
        }

    }
    void setupButtons0(){
        for (int i=0; i<256; i++){
            //String buttonNumber = Integer.toString(i);
            m_button = new JButton();
            //button.setPreferredSize(new Dimension(5,5));
            m_button.addActionListener(this);
            this.add(m_button);
        }
    }
    public void restart(){
        System.out.println("if won then communicate win with replay panel");
    }
    public void setGamePanel(Minesweeper minesweeper){
       m_minesweeper = minesweeper;
    }
    public ImageIcon changeButtonIcon() {
        URL imageURL = getClass().getResource("minesweeper.jpg");
        return new ImageIcon(imageURL);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("debug GridPanel");
        m_minesweeper.restart();
//      tbd: figure out how to identify what button was selected

    }
}
