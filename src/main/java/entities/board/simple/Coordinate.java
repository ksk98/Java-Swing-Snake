package entities.board.simple;

public class Coordinate {
    public int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate){
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public boolean equals(Coordinate target){
        return this.x == target.x && this.y == target.y;
    }
}
