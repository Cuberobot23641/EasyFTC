package org.firstinspires.ftc.teamcode.executor.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialAction extends Action {
    private List<Action> actions;
    private int state;
    private int index;

    public SequentialAction(Action...actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
        state = -1;
        index = 0;
    }

    @Override
    public void start() {
        if (actions != null) {
            state = 0;
            actions.get(0).start();
        }
    }

    @Override
    public void run() {
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

    @Override
    public boolean isFinished() {
        return state == -1;
    }
}
