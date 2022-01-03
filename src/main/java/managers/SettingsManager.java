package managers;

import entities.data.difficulty.Difficulty;
import entities.data.difficulty.DifficultyRepo;
import entities.data.size.Size;
import entities.data.size.SizeRepo;
import entities.data.size.BoardSize;
import entities.data.difficulty.DifficultyLevel;
import managers.Body.BodySet;
import managers.Body.RegularBodySet;
import managers.Tiles.DesertTileSet;
import managers.Tiles.TileSet;

import java.net.URISyntaxException;

public class SettingsManager implements SettingsGetter {
    private Difficulty difficulty;
    private Size size;
    private TileSet tileSet;
    private BodySet bodySet;

    public SettingsManager() throws URISyntaxException {
        difficulty = DifficultyRepo.getDifficulty(DifficultyLevel.EASY);
        size = SizeRepo.getSize(BoardSize.SMALL);
        tileSet = new DesertTileSet();
        bodySet = new RegularBodySet();
    }

    public void setDifficulty(DifficultyLevel difficultyLevel) {
        difficulty = DifficultyRepo.getDifficulty(difficultyLevel);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setSize(BoardSize boardSize) {
        size = SizeRepo.getSize(boardSize);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public TileSet getTileSet() {
        return tileSet;
    }

    @Override
    public BodySet getBodySet() {
        return bodySet;
    }

    @Override
    public Size getBoardSize() {
        return size;
    }
}
