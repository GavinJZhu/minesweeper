import javax.swing.*;
import java.awt.*;

public class Minesweeper {
    JFrame minesweeperFrame;
    ScoringPanel scoringPanel; //ScoringPanel extends JPanel
    GridPanel gridPanel; //GridPanel extends JPanel
    ReplayPanel replayPanel; // ReplayPanel extends JPanel
    //GridBagLayout
    static final int WIDTH = 375;
    static final int HEIGHT = 500;

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.setup();
    }

    Minesweeper() {
        minesweeperFrame = new JFrame();
        //minesweeperFrame.setLayout(new GridLayout(3,1));
        minesweeperFrame.setLayout(new BorderLayout());
        scoringPanel = new ScoringPanel();
        scoringPanel.setGamePanel(this);
        scoringPanel.setSize(100, 50);
        gridPanel = new GridPanel(scoringPanel);
        gridPanel.setPreferredSize(new Dimension(80, 80));
        gridPanel.setGamePanel(this);
        replayPanel = new ReplayPanel();
        replayPanel.setGamePanel(this);
        //minesweeperFrame.addMouseListener(scoringPanel);
    }
    public void setup() {
//        minesweeperFrame.add(scoringPanel);
//        minesweeperFrame.add(gridPanel);
//        minesweeperFrame.add(replayPanel);
        minesweeperFrame.add(scoringPanel, BorderLayout.NORTH);
        minesweeperFrame.add(gridPanel, BorderLayout.CENTER);
        minesweeperFrame.add(replayPanel, BorderLayout.SOUTH);
        minesweeperFrame.setSize(WIDTH, HEIGHT);
        minesweeperFrame.setVisible(true);
        minesweeperFrame.setDefaultCloseOperation(minesweeperFrame.EXIT_ON_CLOSE);
    }

    public void restart() {

        replayPanel.restart();
        scoringPanel.restart();
        gridPanel.restart();
        minesweeperFrame.revalidate();
    }
}