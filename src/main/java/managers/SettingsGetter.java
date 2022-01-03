package managers;

import entities.data.difficulty.Difficulty;
import entities.data.size.Size;
import managers.Body.BodySet;
import managers.Tiles.TileSet;

public interface SettingsGetter {
    Difficulty getDifficulty();
    TileSet getTileSet();
    BodySet getBodySet();
    Size getBoardSize();
}
