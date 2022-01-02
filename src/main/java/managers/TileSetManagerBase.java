package managers;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class TileSetManagerBase implements TileSetManager {
    protected final List<ImageIcon> tiles, decorations, treasures, obstacles;
    protected final ImageIcon empty, test;
    private Random random;

    public TileSetManagerBase() throws URISyntaxException {
        tiles = new LinkedList<>();
        decorations = new LinkedList<>();
        treasures = new LinkedList<>();
        obstacles = new LinkedList<>();
        URL folderURL = ClassLoader.getSystemResource("sprites/");
        empty = createScaledIcon(new ImageIcon(folderURL.getPath() + "nothing.png"));
        test = createScaledIcon(new ImageIcon(folderURL.getPath() + "test.png"));
        random = new Random();
    }

    public ImageIcon createScaledIcon(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
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
