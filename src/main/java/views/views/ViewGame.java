package views.views;

import concurrents.TimeCounterThread;
import entities.data.difficulty.Difficulty;
import views.components.BoardPanel;
import views.components.BoardView;
import managers.Tiles.TileSet;

import javax.swing.*;

public class ViewGame extends ViewBase {
    private BoardView boardView;
    private BoardPanel boardPanel;
    private TimeCounterThread timer;

    public ViewGame(int x, int y, TileSet tileSet, Difficulty difficulty) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        boardView = new BoardView(x, y, tileSet, difficulty);
        boardPanel = new BoardPanel();
        timer = new TimeCounterThread(boardPanel);

        add(boardView);
        add(boardPanel);

        revalidate();
        pack();
        centerScreen();
        setFocusable(true);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public JButton getExitButton() {
        return boardPanel.getExit();
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stopRunning();
    }
}
