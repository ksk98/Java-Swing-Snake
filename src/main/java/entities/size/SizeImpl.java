package entities.size;

public class SizeImpl implements Size {
    private int x, y;
    private String name;

    public SizeImpl(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
