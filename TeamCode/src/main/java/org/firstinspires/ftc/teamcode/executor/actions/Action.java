package org.firstinspires.ftc.teamcode.executor.actions;

public abstract class Action {
    private Runnable runnable;
    private int state;
    public Action(Runnable runnable) {
        this.runnable = runnable;
        state = -1;
    }
    public Action() {}

    public void start() {
        state = 0;
    }
    public void run() {
        if (state == 0) {
            runnable.run();
            state = -1;
        }
    }
    public boolean isFinished() {
        return state == -1;
    }
}