package managers;

import entities.difficulty.Difficulty;
import entities.difficulty.DifficultyRepo;
import entities.size.Size;
import entities.size.SizeRepo;
import enums.BoardSize;
import enums.DifficultyLevel;

import java.net.URISyntaxException;

public class SettingsManager {
    private Difficulty difficulty;

    private Size size;

    private TileSetManager tileSetManager;

    public SettingsManager() throws URISyntaxException {
        difficulty = DifficultyRepo.getDifficulty("EASY");
        size = SizeRepo.getSize("SMALL");
        tileSetManager = new DesertTileSetManager();
    }

    public void setDifficulty(DifficultyLevel difficultyLevel) {
        difficulty = DifficultyRepo.getDifficulty(difficultyLevel);
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

    public Size getSize() {
        return size;
    }
}
