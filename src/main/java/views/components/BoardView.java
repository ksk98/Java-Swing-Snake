package views.components;

import entities.data.difficulty.Difficulty;
import enums.Direction;
import managers.Tiles.TileSet;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BoardView extends JPanel {
    private final int boardX, boardY;

    private JLayeredPane layers;
    private JPanel backgroundLayer, decorationLayer, collisionLayer;

    private TileSet tileSet;
    private Difficulty difficulty;

    public BoardView(int boardX, int boardY, TileSet tileSet, Difficulty difficulty) {
        this.boardX = boardX;
        this.boardY = boardY;

        this.tileSet = tileSet;
        this.difficulty = difficulty;

        layers = new JLayeredPane();
        layers.setPreferredSize(
                new Dimension(
                        boardX * tileSet.getTileBorderSize(),
                        boardY * tileSet.getTileBorderSize()
                )
        );

        backgroundLayer = new JPanel();
        backgroundLayer.setLayout(new GridLayout(boardY, boardX));
        backgroundLayer.setBounds(0, 0, boardX * tileSet.getTileBorderSize(), boardY * tileSet.getTileBorderSize());
        layers.add(backgroundLayer);
        layers.setLayer(backgroundLayer, 0);

        decorationLayer = new JPanel();
        decorationLayer.setLayout(new GridLayout(boardY, boardX));
        decorationLayer.setOpaque(false);
        decorationLayer.setBounds(0, 0, boardX * tileSet.getTileBorderSize(), boardY * tileSet.getTileBorderSize());
        layers.add(decorationLayer);
        layers.setLayer(decorationLayer, 1);

        collisionLayer = new JPanel();
        collisionLayer.setLayout(new GridLayout(boardY, boardX));
        collisionLayer.setOpaque(false);
        collisionLayer.setBounds(0, 0, boardX * tileSet.getTileBorderSize(), boardY * tileSet.getTileBorderSize());
        layers.add(collisionLayer);
        layers.setLayer(collisionLayer, 2);

        add(layers);
        createWithTileset();
    }

    private void createWithTileset() {
        Random random = new Random();

        for (int i = 0; i < boardY; i++) {
            for (int j = 0; j < boardX; j++) {
                backgroundLayer.add(new JLabel(tileSet.getTile()));
                if (random.nextInt(100) < 20)
                    decorationLayer.add(new JLabel(tileSet.getDecoration()));
                else
                    decorationLayer.add(new JLabel(tileSet.getEmpty()));

                if ((i != 0 && i != boardY -1 && j != 0 && j != boardX -1) &&
                        (i < (boardY/2)-2 && i > (boardY/2)+2 && j < (boardX/2) && j > (boardX/2)) &&
                        (random.nextInt(100) < difficulty.getObstacleChanceInPercent())) {
                    JLabel obstacle = new JLabel(tileSet.getObstacle());
                    obstacle.setToolTipText(" ");
                    collisionLayer.add(obstacle);
                } else {
                    JLabel field = new JLabel(tileSet.getEmpty());
                    field.setToolTipText("");
                    collisionLayer.add(field);
                }
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        if (x < 0 || y < 0 || x >= boardX || y >= boardY)
            throw new IndexOutOfBoundsException(
                    "Attempted to access invalid field of coordinates (" + x + ", " + y + ")."
            );
        JLabel field = ((JLabel) collisionLayer.getComponent( (y * boardX) + x));
        return field.getToolTipText().equals(" ");
    }

    public void draw(int x, int y, ImageIcon imageIcon, Direction direction, boolean collides){
        if (x >= boardX || x < 0 || y >= boardY || y < 0)
            throw new IndexOutOfBoundsException("Attempted to draw out of bound: " + x + ", " + y + ".");

        if (imageIcon == null)
            return;

        // Unless the direction specified equals Direction.up, we need to flip the icon
        ImageIcon finalIcon;
        if (direction == Direction.up)
            finalIcon = imageIcon;
        else {
            // Convert given ImageIcon to BufferedImage
            BufferedImage image = new BufferedImage(
                    imageIcon.getIconWidth(),
                    imageIcon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics g = image.createGraphics();
            imageIcon.paintIcon(null, g, 0,0);
            g.dispose();

            //Flip the BufferedImage
            double rads = Math.toRadians(90*direction.ordinal());
            double sin = Math.abs(Math.sin(rads));
            double cos = Math.abs(Math.cos(rads));
            int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
            int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
            BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
            AffineTransform at = new AffineTransform();
            at.translate(w * 1.0f / 2, h * 1.0f / 2);
            at.rotate(rads,0, 0);
            at.translate(-image.getWidth() * 1.0f / 2, -image.getHeight() * 1.0f / 2);
            AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            rotateOp.filter(image,rotatedImage);

            finalIcon = new ImageIcon(rotatedImage);
        }

        JLabel field = ((JLabel)collisionLayer.getComponent((y * boardX) + x));
        field.setIcon(finalIcon);
        if (collides)
            field.setToolTipText(" ");
        else
            field.setToolTipText("");
    }

    public void draw(int x, int y, ImageIcon imageIcon, boolean collides) {
        draw(x, y, imageIcon, Direction.up, collides);
    }

    public void erase(int x, int y) {
        draw(x, y, tileSet.getEmpty(), Direction.up, false);
    }
}
