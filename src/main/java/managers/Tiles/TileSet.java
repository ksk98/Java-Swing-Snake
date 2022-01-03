package managers.Tiles;

import javax.swing.*;

public interface TileSet {
    ImageIcon getTile();
    ImageIcon getDecoration();
    ImageIcon getTreasure();
    ImageIcon getObstacle();
    ImageIcon getEmpty();
    ImageIcon getTest();
    int getTileBorderSize();
}
