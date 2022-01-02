package managers;

import entities.difficulty.Difficulty;
import entities.difficulty.DifficultyRepo;
import entities.size.Size;
import entities.size.SizeRepo;
import entities.size.BoardSize;
import entities.difficulty.DifficultyLevel;

import java.net.URISyntaxException;

public class SettingsManager {
    private Difficulty difficulty;

    private Size size;

    private TileSetManager tileSetManager;

    public SettingsManager() throws URISyntaxException {
        difficulty = DifficultyRepo.getDifficulty(DifficultyLevel.EASY);
        size = SizeRepo.getSize(BoardSize.SMALL);
        tileSetManager = new DesertTileSetManager();
    }

    public void setDifficulty(DifficultyLevel difficultyLevel) {
        difficulty = DifficultyRepo.getDifficulty(difficultyLevel);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public TileSetManager getTileSetManager() {
        return tileSetManager;
    }

    public void setSize(BoardSize boardSize) {
        size = SizeRepo.getSize(boardSize);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getBoardSize() {
        return size;
    }
}
