package org.firstinspires.ftc.teamcode.executor.actions;

public class WaitAction extends Action {
    private int waitMilliseconds = 0;
    private Timer timer;
    private int state = -1;
    public WaitAction(int waitMilliseconds) {
        this.waitMilliseconds = waitMilliseconds;
        timer = new Timer();
    }

    public void setState(int x) {
        state = x;
    }

    @Override
    public void start() {
        timer.resetTimer();
        setState(0);
    }

    @Override
    public void run() {
        switch (state) {
            case 0:
                if (timer.getElapsedTime() >= waitMilliseconds) {
                    setState(-1);
                }
                break;
        }
    }
    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
