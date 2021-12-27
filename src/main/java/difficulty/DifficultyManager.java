package difficulty;

public class DifficultyManager {
    DifficultyBuilder builder;

    public DifficultyManager(DifficultyBuilder builder) {
        this.builder = builder;
    }

    public void createEasyDifficulty() {
        builder.setObstacleChanceInPercent(4);
        builder.setPointMultiplier(1);
        builder.setSpeed(1);
    }

    public void createMediumDifficulty() {
        builder.setObstacleChanceInPercent(10);
        builder.setPointMultiplier(2);
        builder.setSpeed(3);
    }

    public void createHardDifficulty() {
        builder.setObstacleChanceInPercent(16);
        builder.setPointMultiplier(3);
        builder.setSpeed(5);
    }
}
