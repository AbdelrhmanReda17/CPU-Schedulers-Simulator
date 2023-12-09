package cpu.cpu.simulator.Utilities;

import java.sql.Time;

public class Duration {
    Time startTime;
    Time endTime;
    public Duration(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    public Long getDuration(){
        return endTime.getTime() - startTime.getTime();
    }
    @Override
    public String toString() {
        return "Duration{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
