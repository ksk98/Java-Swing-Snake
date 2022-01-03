package entities.data.difficulty;

public class DifficultyImpl implements Difficulty {
    private int obstacleChanceInPercent, frameDelay, pointMultiplier;
    private String name;

    public DifficultyImpl(int obstacleChanceInPercent, int frameDelayMS, int pointMultiplier, String name) {
        this.obstacleChanceInPercent = obstacleChanceInPercent;
        this.frameDelay = frameDelayMS;
        this.pointMultiplier = pointMultiplier;
        this.name = name;
    }

    @Override
    public int getObstacleChanceInPercent() {
        return obstacleChanceInPercent;
    }

    @Override
    public int getFrameDelay() {
        return frameDelay;
    }

    @Override
    public int getPointMultiplier() {
        return pointMultiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}
