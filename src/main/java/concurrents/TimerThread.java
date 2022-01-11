package concurrents;

import java.util.Date;

public class TimerThread {
    private Date date;
    private long lastCall;
    private long coolDownMS;

    public TimerThread(int coolDownMS) {
        date = new Date();
        this.coolDownMS = coolDownMS;
        lastCall = date.getTime() - coolDownMS;
    }

    public void start() {
        date = new Date();
        lastCall = date.getTime();
    }

    public boolean timePassed() {
        date = new Date();
        return lastCall + coolDownMS < date.getTime();
    }

    public void changeTimePeriodToCount(int coolDownMS){
        this.coolDownMS = coolDownMS;
    }
}
