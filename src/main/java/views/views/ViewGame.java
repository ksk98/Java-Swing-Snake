package views.views;

import entities.difficulty.Difficulty;
import views.components.BoardPanel;
import views.components.BoardView;
import managers.TileSetManager;

import javax.swing.*;

public class ViewGame extends ViewBase {
    private BoardPanel boardPanel;
    private BoardView boardView;

    public ViewGame(int x, int y, TileSetManager tileSetManager, Difficulty difficulty) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        boardPanel = new BoardPanel();
        boardView = new BoardView(x, y, tileSetManager, difficulty);

        add(boardPanel);
        add(boardView);

        revalidate();
        pack();
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
