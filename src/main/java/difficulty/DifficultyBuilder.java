package difficulty;

public class DifficultyBuilder {
    private Difficulty difficulty;

    public DifficultyBuilder() {
        reset();
    }

    public void reset() {
        difficulty = new Difficulty();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setObstacleChanceInPercent(int obstacleChanceInPercent) {
        difficulty.setObstacleChanceInPercent(obstacleChanceInPercent);
    }

    public void setSpeed(int speed) {
        difficulty.setSpeed(speed);
    }

    public void setPointMultiplier(int pointMultiplier) {
        difficulty.setPointMultiplier(pointMultiplier);
    }
}
