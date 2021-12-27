package gui.components;

import managers.TileSetManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardView extends JPanel {
    private final int x, y;

    private JLayeredPane layers;
    private JPanel backgroundLayer, decorationLayer, collisionLayer;

    public BoardView(int x, int y, TileSetManager tileSetManager) {
        this.x = x;
        this.y = y;

        layers = new JLayeredPane();

        backgroundLayer = new JPanel();
        backgroundLayer.setLayout(new GridLayout(y, x));
        layers.add(backgroundLayer, 0);

        decorationLayer = new JPanel();
        decorationLayer.setLayout(new GridLayout(y, x));
        layers.add(decorationLayer, 1);

        collisionLayer = new JPanel();
        collisionLayer.setLayout(new GridLayout(y, x));
        layers.add(collisionLayer, 2);

        add(layers);

        createWithTileset(tileSetManager);
    }

    private void createWithTileset(TileSetManager tileSetManager) {
        Random random = new Random();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                backgroundLayer.add(new JLabel(tileSetManager.getTile()));

                if (random.nextInt(100) < 30)
                    decorationLayer.add(new JLabel(tileSetManager.getDecoration()));

                if (random.nextInt(100) < 10)

            }
        }
    }

}
