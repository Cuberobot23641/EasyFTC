package org.firstinspires.ftc.teamcode.executor.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelAction extends Action {
    private List<Action> actions;
    private int state;
    private int index;

    public ParallelAction(Action...actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
        state = -1;
        index = 0;
    }

    @Override
    public void start() {
        if (actions != null) {
            state = 0;
            for (Action action: actions) {
                action.start();
            }
        }
    }

    @Override
    public void run() {
        if (state == 0) {
            if (allActionsFinished()) {
                state = -1;
            }
            for (Action action: actions) {
                action.run();
            }
        }
    }
    private boolean allActionsFinished() {
        for (Action action: actions) {
            if (!action.isFinished()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
