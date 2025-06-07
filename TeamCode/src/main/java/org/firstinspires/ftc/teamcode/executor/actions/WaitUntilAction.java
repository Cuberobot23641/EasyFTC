package org.firstinspires.ftc.teamcode.executor.actions;

import org.firstinspires.ftc.teamcode.util.Timer;

import java.util.function.Supplier;

public class WaitUntilAction extends Action {
    private Action action;
    private int timeout;
    private Timer timer;
    private int state;
    private Supplier<Boolean> condition;

    public WaitUntilAction(Supplier<Boolean> condition, Action action, int timeout) {
        this.action = action;
        this.condition = condition;
        this.timeout = timeout;
        timer = new Timer();
        state = -1;
    }

    @Override
    public void start() {
        if (condition.get()) {
            action.start();
            state = 0;
        }
    }

    @Override
    public void run() {
        if (state == 0) {
            if (timer.getTime() > timeout) {
                state = -1;
            }
            if (condition.get()) {
                state = 1;
            }
        } else if (state == 1) {
            if (action.isFinished()) {
                state = -1;
            }
            action.run();
        }
    }

    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
