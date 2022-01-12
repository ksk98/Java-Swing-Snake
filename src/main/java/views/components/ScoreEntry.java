package views.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ScoreEntry extends JPanel {
    private static final int maxNameLength = 24;

    public ScoreEntry(String name, int score) {
//        setPreferredSize(new Dimension(128, 32));

        setLayout(new BorderLayout());
        setAlignmentX(CENTER_ALIGNMENT);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel labelName = new JLabel(getNameOfProperSize(name));
        labelName.setBorder(border);
//        add(labelName, BorderLayout.WEST);

        JLabel labelScore = new JLabel(Integer.toString(score));
        labelScore.setBorder(border);
//        add(labelScore, BorderLayout.EAST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, labelName, labelScore);
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);
        add(splitPane, BorderLayout.CENTER);
    }

    private String getNameOfProperSize(String name) {
        if (name.length() > maxNameLength)
            return name.substring(0, maxNameLength);
        else return name;
    }
}
