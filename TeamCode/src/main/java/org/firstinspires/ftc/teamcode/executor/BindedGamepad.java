package org.firstinspires.ftc.teamcode.executor;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.executor.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BindedGamepad {
    private UnbindedGamepad gamepad;
    private List<Action> actions;
    private List<String> bindedButtons;
    private HashMap<String, Action> actionMap;
    private HashMap<String, Float> thresholdMap;
    public BindedGamepad(Gamepad gamepad) {
        this.gamepad = new UnbindedGamepad(gamepad);
        actionMap = new HashMap<>();
        thresholdMap = new HashMap<>();
        actions = new ArrayList<>();
        bindedButtons = new ArrayList<>();
    }

    public void update() {
        gamepad.update();
        for (String button: bindedButtons) {
            if (gamepad.justPressed(button)) {
                Objects.requireNonNull(actionMap.get(button)).start();
            } else if (thresholdMap.containsKey(button)) {
                if ((gamepad.getValue(button) > thresholdMap.get(button)) && !(gamepad.getPrevValue(button) > thresholdMap.get(button))) {
                    Objects.requireNonNull(actionMap.get(button)).start();
                }
            }
        }

        for (Action action: actions) {
            action.run();
        }
    }

    public void bind(String buttonName, Action action) {
        bindedButtons.add(buttonName);
        actions.add(action);
        actionMap.put(buttonName, action);
    }

    public void setThreshold(String triggerName, float threshold) {
        thresholdMap.put(triggerName, threshold);
    }

    public double getThreshold(String triggerName) {
        if (thresholdMap.containsKey(triggerName)) {
            return thresholdMap.get(triggerName);
        }
        return 0.0;
    }

    public double getValue(String buttonName) {
        return gamepad.getValue(buttonName);
    }

    public double getPrevValue(String buttonName) {
       return gamepad.getPrevValue(buttonName);
    }

    public boolean justPressed(String buttonName) {
        return gamepad.justPressed(buttonName);
    }

    public boolean justReleased(String buttonName) {
        return gamepad.justReleased(buttonName);
    }
}
