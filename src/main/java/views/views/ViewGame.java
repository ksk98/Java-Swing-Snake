package views.views;

import entities.difficulty.Difficulty;
import views.components.BoardPanel;
import views.components.BoardView;
import managers.TileSetManager;

import javax.swing.*;

public class ViewGame extends ViewBase {
    private BoardView boardView;
    private BoardPanel boardPanel;

    public ViewGame(int x, int y, TileSetManager tileSetManager, Difficulty difficulty) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        boardView = new BoardView(x, y, tileSetManager, difficulty);
        boardPanel = new BoardPanel();

        add(boardView);
        add(boardPanel);

        revalidate();
        pack();
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
