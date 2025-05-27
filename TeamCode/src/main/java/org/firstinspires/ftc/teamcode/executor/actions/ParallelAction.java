package org.firstinspires.ftc.teamcode.executor.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: the biggest thing we can do here is resetting actions. this would also reset all children of the action.
public class ParallelAction extends Action {
    private List<Runnable> runnables;
    private List<Action> actions;
    private int state = -1;
    private int index = 0;

    public ParallelAction(Runnable... runnables) {
        this.runnables = new ArrayList<>();
        this.runnables.addAll(Arrays.asList(runnables));
    }
    public ParallelAction(Action... actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
    }

    @Override
    public void start() {
        if (actions != null) { // action was initialized with actions
            for (Action action: actions) {
                action.start();
            }
            setState(0);
        } else { // action was initialized with runnables
            for (Runnable runnable: runnables) {
                runnable.run();
            }
            setState(-1);
        }
    }

    @Override
    public void run() {
        switch (state) {
            case 0:
                if (isFinished()) {
                    setState(-1);
                } else {
                    for (Action action: actions) {
                        action.run();
                    }
                }
                break;
        }
    }


    public void setState(int x) {
        state = x;
    }

    @Override
    public boolean isFinished() {
        if (actions != null) {
            for (Action action : actions) {
                if (!action.isFinished()) {
                    return false;
                }
            }
        }
        return true;
    }
}