package org.firstinspires.ftc.teamcode.util;

public class Timer {
    private long startTime;

    public Timer() {
        reset();
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }
}
