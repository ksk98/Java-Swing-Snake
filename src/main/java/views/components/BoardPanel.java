package views.components;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements TimerOutput, ScoreOutput {
    private JLabel score, time;
    private JButton exit;

    public BoardPanel() {
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        score = new JLabel("SCORE: 0");
        add(score);

        exit = new JButton("EXIT");
        add(exit);

        time = new JLabel("TIME: 00:00");
        add(time);
    }

    public JButton getExit() {
        return exit;
    }

    @Override
    public void setTime(int timeInSeconds) {
        int tenMinutes = Math.floorDiv(timeInSeconds, 600);
        int minutes = Math.floorDiv(timeInSeconds, 60) % 10;
        int tenSeconds = Math.floorDiv(timeInSeconds, 10) % 6;
        int seconds = timeInSeconds % 10;
        this.time.setText("TIME: " + tenMinutes + minutes + ":" + tenSeconds + seconds);
    }

    @Override
    public void setScore(int score){
        this.score.setText("SCORE: " + score);
    }
}
