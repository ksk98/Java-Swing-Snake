package difficulty;

public class Difficulty {
    private int obstacleChanceInPercent, speed, pointMultiplier;

    public Difficulty() {
        obstacleChanceInPercent = 0;
        speed = 1;
        pointMultiplier = 1;
    }

    public int getObstacleChanceInPercent() {
        return obstacleChanceInPercent;
    }

    public void setObstacleChanceInPercent(int obstacleChanceInPercent) {
        this.obstacleChanceInPercent = obstacleChanceInPercent;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPointMultiplier() {
        return pointMultiplier;
    }

    public void setPointMultiplier(int pointMultiplier) {
        this.pointMultiplier = pointMultiplier;
    }
}
