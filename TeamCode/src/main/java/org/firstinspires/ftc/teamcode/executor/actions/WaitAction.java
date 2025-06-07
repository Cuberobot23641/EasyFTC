package org.firstinspires.ftc.teamcode.executor.actions;

import org.firstinspires.ftc.teamcode.util.Timer;

public class WaitAction extends Action {
    private int waitTime;
    private Timer timer;
    private int state;

    public WaitAction(int waitTime) {
        this.waitTime = waitTime;
        timer = new Timer();
        state = -1;
    }
    @Override
    public void start() {
        timer.reset();
        state = 0;
    }

    @Override
    public void run() {
        if (state == 0) {
            if (timer.getTime() > waitTime) {
                state = -1;
            }
        }
    }
    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
