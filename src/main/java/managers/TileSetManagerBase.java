package managers;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class TileSetManagerBase implements TileSetManager {
    protected final List<ImageIcon> tiles, decorations, treasures, obstacles;
    private Random random;

    public TileSetManagerBase() {
        tiles = new LinkedList<>();
        decorations = new LinkedList<>();
        treasures = new LinkedList<>();
        obstacles = new LinkedList<>();
        random = new Random();
    }

    @Override
    public ImageIcon getTile() {
        return tiles.get(random.nextInt(tiles.size()));
    }

    @Override
    public ImageIcon getDecoration() {
        return decorations.get(random.nextInt(decorations.size()));
    }

    @Override
    public ImageIcon getTreasure() {
        return treasures.get(random.nextInt(treasures.size()));
    }

    @Override
    public ImageIcon getObstacle() {
        return obstacles.get(random.nextInt(obstacles.size()));
    }

    @Override
    public int getTileBorderSize() {
        if (tiles.size() > 0)
            return tiles.get(0).getIconWidth();
        else return 0;
    }
}
