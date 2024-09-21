import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Minesweeper {
    JFrame minesweeperFrame;
    ScoringPanel scoringPanel; //ScoringPanel extends JPanel
    GridPanel gridPanel; //GridPanel extends JPanel
    ReplayPanel replayPanel; // ReplayPanel extends JPanel
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.setup();
    }
    Minesweeper(){
        minesweeperFrame = new JFrame();
        minesweeperFrame.setLayout(new GridLayout(3,1));
        scoringPanel = new ScoringPanel();
        scoringPanel.setGamePanel(this);
       //scoringPanel.setSize(100,100);
        gridPanel = new GridPanel();
        gridPanel.setGamePanel(this);
        replayPanel = new ReplayPanel();
        replayPanel.setGamePanel(this);
        //minesweeperFrame.addMouseListener(scoringPanel);
    }
    public void setup(){
        minesweeperFrame.add(scoringPanel);
        minesweeperFrame.add(gridPanel);
        minesweeperFrame.add(replayPanel);
        minesweeperFrame.setSize(WIDTH, HEIGHT);
        minesweeperFrame.setVisible(true);
        minesweeperFrame.setDefaultCloseOperation(minesweeperFrame.EXIT_ON_CLOSE);
    }
    public void restart(){
        gridPanel.restart();
        replayPanel.restart();
        scoringPanel.restart();
    }
}