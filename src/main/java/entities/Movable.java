package entities;

public interface Movable {
    /**
     * Move entity to exact (X,Y) coordinates.
     * @throws ArrayIndexOutOfBoundsException if placed out of bounds
     */
    void moveTo(int x, int y);

    /**
     * Move entity to exact X coordinate without changing the Y position.
     * @throws ArrayIndexOutOfBoundsException if placed out of bounds
     */
    void moveOnXTo(int x);

    /**
     * Move entity to exact Y coordinate without changing the X position.
     * @throws ArrayIndexOutOfBoundsException if placed out of bounds
     */
    void moveOnYTo(int y);

    /**
     * Move entity by a certain number of fields in both directions.
     * @throws ArrayIndexOutOfBoundsException if moved out of bounds
     */
    void moveBy(int x, int y);

    /**
     * Move entity by a certain number of fields on the X axis.
     * @throws ArrayIndexOutOfBoundsException if moved out of bounds
     */
    void moveByX(int x);

    /**
     * Move entity by a certain number of fields on the Y axis.
     * @throws ArrayIndexOutOfBoundsException if moved out of bounds
     */
    void moveByY(int y);
}
