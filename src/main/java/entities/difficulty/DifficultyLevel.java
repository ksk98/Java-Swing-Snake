package entities.difficulty;

public enum DifficultyLevel {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private String name;

    DifficultyLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
