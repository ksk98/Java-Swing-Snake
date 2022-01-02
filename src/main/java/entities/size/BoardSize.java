package entities.size;

public enum BoardSize {
    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private String name;

    BoardSize(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
