package org.firstinspires.ftc.teamcode.executor.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialAction extends Action{ // this one is a sequential action
    private List<Runnable> runnables;
    private List<Action> actions;
    private int state = -1;
    private int index = 0;

    // each constructor should be overloaded
    // if it has runnable functions, the functions should be executed

    // should act like a commander for actions: run everything sequentially if its an action
    // however if it's a runnable then just run all of them

    // lastly i should add sequential and parallel actions
    // actually i dont think so, i'll skip the action class ad make a sequential and parallel one
    // this one will be parallel, but runnables all execute at the same time

    public SequentialAction(Runnable... runnables) {
        this.runnables = new ArrayList<>();
        this.runnables.addAll(Arrays.asList(runnables));
    }
    public SequentialAction(Action... actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
    }

    @Override
    public void start() {
        if (actions != null) { // action was initialized with actions
            actions.get(0).start();
            actions.get(0).run();
            setState(0);
        } else { // action was initialized with runnables
            for (Runnable runnable: runnables) {
                runnable.run();
                setState(-1);
            }
        }
    }

    @Override
    public void run() {
        switch (state) {
            case 0:
                if (actions != null) {
                    if (actions.get(index).isFinished()) {
                        if (index < actions.size() - 1) {
                            index++;
                            actions.get(index).start();
                        } else {
                            setState(-1);
                        }
                    }
                    actions.get(index).run();
                }
                break;
        }
    }
    public void setState(int x) {
        state = x;
    }

    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
