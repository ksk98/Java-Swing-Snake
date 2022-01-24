package views.views;

import managers.HighScoreManager;
import models.HighScoreRecord;
import views.components.ScoreEntry;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewHighScores extends ViewBase {
    private static final int maxRecords = 10;
    private final JButton buttonBack;

    public ViewHighScores() throws SQLException {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 600));

        List<HighScoreRecord> records =  HighScoreManager.getInstance().getTopScores(maxRecords);

        for (int i = 0; i < maxRecords; i++) {
            if (i >= records.size())
                add(new ScoreEntry("--EMPTY--", 0));
            else
                add(new ScoreEntry(records.get(i).getName(), records.get(i).getHighScore()));
        }

        buttonBack = new JButton("BACK");
        buttonBack.setHorizontalAlignment(JLabel.CENTER);
        add(buttonBack);
        pack();
        centerScreen();
    }

    public JButton getButtonBack() {
        return buttonBack;
    }
}
