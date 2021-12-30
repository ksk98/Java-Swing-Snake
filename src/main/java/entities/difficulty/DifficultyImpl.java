package entities.difficulty;

public class DifficultyImpl implements Difficulty {
    private int obstacleChanceInPercent, speed, pointMultiplier;
    private String name;

    public DifficultyImpl(int obstacleChanceInPercent, int speed, int pointMultiplier, String name) {
        this.obstacleChanceInPercent = obstacleChanceInPercent;
        this.speed = speed;
        this.pointMultiplier = pointMultiplier;
        this.name = name;
    }

    @Override
    public int getObstacleChanceInPercent() {
        return obstacleChanceInPercent;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getPointMultiplier() {
        return pointMultiplier;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
