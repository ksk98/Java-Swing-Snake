package entities.data.difficulty;

public interface Difficulty {
    int getObstacleChanceInPercent();
    int getFrameDelay();
    int getPointMultiplier();
    String toString();
}
