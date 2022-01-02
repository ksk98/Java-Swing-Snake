package managers;

import javax.swing.*;

public interface TileSetManager {
    ImageIcon getTile();
    ImageIcon getDecoration();
    ImageIcon getTreasure();
    ImageIcon getObstacle();
    ImageIcon getEmpty();
    ImageIcon getTest();
    int getTileBorderSize();
}
