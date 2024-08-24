import javax.swing.*;

public class ReplayPanel extends JPanel {
    JSlider slider = new JSlider();
    JButton play = new JButton("play");
    ReplayPanel(){
       setup();
    }
    void setup(){
        this.add(play);
        this.add(slider);
    }
}
