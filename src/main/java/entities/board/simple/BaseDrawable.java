package entities.board.simple;

import enums.Direction;

import javax.swing.*;

public class BaseDrawable implements Drawable {
    private final ImageIcon sprite;
    private Coordinate coordinate;
    private Direction direction;

    public BaseDrawable(Drawable target) {
        this(target.getSprite(), new Coordinate(target.getCoordinate()), target.getDirection());
    }

    public BaseDrawable(ImageIcon sprite, Coordinate coordinate) {
        this(sprite, coordinate, Direction.up);
    }

    public BaseDrawable(ImageIcon sprite, Coordinate coordinate, Direction direction) {
        this.sprite = sprite;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    @Override
    public ImageIcon getSprite() {
        return sprite;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = new Coordinate(coordinate);
    }

    @Override
    public void setCoordinate(Drawable target) {
        setCoordinate(new Coordinate(target.getCoordinate()));
    }

    @Override
    public void setPosition(Coordinate coordinate, Direction direction) {
        this.coordinate = new Coordinate(coordinate);
        this.direction = direction;
    }

    @Override
    public void setPosition(Drawable target) {
        this.coordinate = new Coordinate(target.getCoordinate());
        this.direction = target.getDirection();
    }

    @Override
    public void setXCoordinate(int x) {
        this.coordinate.x = x;
    }

    @Override
    public void setYCoordinate(int y) {
        this.coordinate.y = y;
    }
}
