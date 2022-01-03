package entities.board.simple;

import enums.Direction;

import javax.swing.*;

public interface Drawable {
    ImageIcon getSprite();
    Direction getDirection();
    Coordinate getCoordinate();
    void setDirection(Direction direction);
    void setCoordinate(Coordinate coordinate);
    void setCoordinate(Drawable target);
    void setPosition(Coordinate coordinate, Direction direction);
    void setPosition(Drawable target);
    void setXCoordinate(int x);
    void setYCoordinate(int y);
}
