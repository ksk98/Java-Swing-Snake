package entities.difficulty;

public interface Difficulty {
    int getObstacleChanceInPercent();
    int getSpeed();
    int getPointMultiplier();
    String getDisplayName();
}
