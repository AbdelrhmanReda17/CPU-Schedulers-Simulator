package cpu.cpu.simulator.Utilities;

import java.sql.Time;

public class Duration {
    int startTime;
    int endTime;
    public Duration(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public int getDuration(){
        return endTime - startTime ;
    }
    @Override
    public String toString() {
        return "Duration{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
