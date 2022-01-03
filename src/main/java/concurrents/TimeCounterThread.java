package concurrents;

import views.components.TimerOutput;

public class TimeCounterThread extends Thread {
    private TimerOutput out;
    private int timeInSeconds;
    private boolean isRunning;

    public TimeCounterThread(TimerOutput out) {
        this.out = out;
        timeInSeconds = 0;
        isRunning = true;
    }

    public void stopRunning() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                out.setTime(timeInSeconds);
                timeInSeconds++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
