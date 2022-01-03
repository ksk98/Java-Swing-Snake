package managers.Tiles;

import utility.ImageIconUtility;

import javax.swing.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class TileSetBase implements TileSet {
    protected final List<ImageIcon> tiles, decorations, treasures, obstacles;
    protected final ImageIcon empty, test;
    private Random random;

    public TileSetBase() {
        tiles = new LinkedList<>();
        decorations = new LinkedList<>();
        treasures = new LinkedList<>();
        obstacles = new LinkedList<>();
        URL folderURL = ClassLoader.getSystemResource("sprites/");
        empty = ImageIconUtility.createScaledIcon(new ImageIcon(folderURL.getPath() + "nothing.png"));
        test = ImageIconUtility.createScaledIcon(new ImageIcon(folderURL.getPath() + "test.png"));
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
    public ImageIcon getEmpty() {
        return empty;
    }

    @Override
    public ImageIcon getTest() {
        return test;
    }

    @Override
    public int getTileBorderSize() {
        if (tiles.size() > 0)
            return tiles.get(0).getIconWidth();
        else return 0;
    }
}
