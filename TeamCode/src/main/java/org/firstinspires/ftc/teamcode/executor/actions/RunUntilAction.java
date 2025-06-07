package org.firstinspires.ftc.teamcode.executor.actions;

import org.firstinspires.ftc.teamcode.util.Timer;

import java.util.function.Supplier;

// the reverse of the waitUntil: starts by running and waits until its done
public class RunUntilAction extends Action {
    private Action action;
    private int timeout;
    private Timer timer;
    private int state;
    private Supplier<Boolean> condition;

    public RunUntilAction(Supplier<Boolean> condition, Action action, int timeout) {
        this.action = action;
        this.condition = condition;
        this.timeout = timeout;
        timer = new Timer();
        state = -1;
    }

    @Override
    public void start() {
        action.start();
        state = 0;
    }

    @Override
    public void run() {
        if (state == 0) {
            if ((timer.getTime() > timeout) || condition.get()) {
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
