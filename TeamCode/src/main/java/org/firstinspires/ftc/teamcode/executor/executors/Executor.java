package org.firstinspires.ftc.teamcode.executor.executors;

import org.firstinspires.ftc.teamcode.executor.actions.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {
    private List<Action> actions;
    private int state;
    private int index;

    public Executor(Action...actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
        state = -1;
        index = 0;
    }

    public void start() {
        if (actions != null) {
            state = 0;
            actions.get(0).start();
        }
    }

    public void update() {
        if (state == 0) {
            if (actions.get(index).isFinished()) {
                if (index < actions.size() - 1) {
                    index++;
                    actions.get(index).start();
                } else {
                    state = -1;
                }
            }
            for (Action action: actions) {
                action.run();
            }
        }
    }

    public boolean isFinished() {
        return state == -1;
    }
}
