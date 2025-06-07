package org.firstinspires.ftc.teamcode.executor.actions;

import java.util.function.Supplier;

public class ConditionalAction extends Action {
    private Action action;
    private int state;
    private Supplier<Boolean> condition;
    public ConditionalAction(Supplier<Boolean> condition, Action action) {
        this.action = action;
        this.condition = condition;
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
