package views.components;

import entities.difficulty.Difficulty;
import managers.TileSetManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardView extends JPanel {
    private final int x, y;

    private JLayeredPane layers;
    private JPanel backgroundLayer, decorationLayer, collisionLayer;

    private TileSetManager tileSetManager;
    private Difficulty difficulty;

    public BoardView(int x, int y, TileSetManager tileSetManager, Difficulty difficulty) {
        this.x = x;
        this.y = y;

        this.tileSetManager = tileSetManager;
        this.difficulty = difficulty;

        layers = new JLayeredPane();
        layers.setPreferredSize(
                new Dimension(
                        x * tileSetManager.getTileBorderSize(),
                        y * tileSetManager.getTileBorderSize()
                )
        );

        backgroundLayer = new JPanel();
        backgroundLayer.setLayout(new GridLayout(y, x));
        backgroundLayer.setBounds(0, 0, x * tileSetManager.getTileBorderSize(), y * tileSetManager.getTileBorderSize());
        layers.add(backgroundLayer);
        layers.setLayer(backgroundLayer, 0);

        decorationLayer = new JPanel();
        decorationLayer.setLayout(new GridLayout(y, x));
        decorationLayer.setOpaque(false);
        decorationLayer.setBounds(0, 0, x * tileSetManager.getTileBorderSize(), y * tileSetManager.getTileBorderSize());

        layers.add(decorationLayer);
        layers.setLayer(decorationLayer, 1);

        collisionLayer = new JPanel();
        collisionLayer.setLayout(new GridLayout(y, x));
        collisionLayer.setOpaque(false);
        collisionLayer.setBounds(0, 0, x * tileSetManager.getTileBorderSize(), y * tileSetManager.getTileBorderSize());
        layers.add(collisionLayer);
        layers.setLayer(collisionLayer, 2);

        add(layers);

        createWithTileset();
    }

    private void createWithTileset() {
        Random random = new Random();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                backgroundLayer.add(new JLabel(tileSetManager.getTile()));
                if (random.nextInt(100) < 20)
                    decorationLayer.add(new JLabel(tileSetManager.getDecoration()));
                else
                    decorationLayer.add(new JLabel(tileSetManager.getEmpty()));

                if (random.nextInt(100) < difficulty.getObstacleChanceInPercent()) {
                    JLabel obstacle = new JLabel(tileSetManager.getObstacle());
                    obstacle.setText(" ");
                    collisionLayer.add(obstacle);
                } else {
                    collisionLayer.add(new JLabel(tileSetManager.getEmpty()));
                }
            }
        }
    }

}
