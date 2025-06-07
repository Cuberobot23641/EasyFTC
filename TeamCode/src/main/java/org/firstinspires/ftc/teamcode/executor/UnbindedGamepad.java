package org.firstinspires.ftc.teamcode.executor;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class UnbindedGamepad {
    private Gamepad gamepad;
    private Gamepad prevGamepad;
    private Gamepad currentGamepad;
    private HashMap<String, Function<Gamepad, Boolean>> buttonMap;
    private HashMap<String, Boolean> buttonValueMap;
    private HashMap<String, Boolean> fallingButtonValueMap;
    private HashMap<String, Function<Gamepad, Float>> triggerMap;
    private HashMap<String, Float> triggerValueMap;
    private HashMap<String, Float> fallingTriggerValueMap;

    public UnbindedGamepad(Gamepad gamepad) {
        this.gamepad = gamepad;
        currentGamepad = new Gamepad();
        prevGamepad = new Gamepad();

        buttonMap = new HashMap<>();
        buttonMap.put("x", gp -> gp.x);
        buttonMap.put("y", gp -> gp.y);
        buttonMap.put("a", gp -> gp.a);
        buttonMap.put("b", gp -> gp.b);
        buttonMap.put("left_bumper", gp -> gp.left_bumper);
        buttonMap.put("right_bumper", gp -> gp.right_bumper);
        buttonMap.put("left_stick", gp -> gp.left_stick_button);
        buttonMap.put("right_stick", gp -> gp.right_stick_button);
        buttonMap.put("dpad_up", gp -> gp.dpad_up);
        buttonMap.put("dpad_down", gp -> gp.dpad_down);
        buttonMap.put("dpad_left", gp -> gp.dpad_left);
        buttonMap.put("dpad_right", gp -> gp.dpad_right);

        buttonValueMap = new HashMap<>();
        buttonValueMap.put("x", false);
        buttonValueMap.put("y", false);
        buttonValueMap.put("a", false);
        buttonValueMap.put("b", false);
        buttonValueMap.put("left_bumper", false);
        buttonValueMap.put("right_bumper", false);
        buttonValueMap.put("left_stick", false);
        buttonValueMap.put("right_stick", false);
        buttonValueMap.put("dpad_up", false);
        buttonValueMap.put("dpad_down", false);
        buttonValueMap.put("dpad_left", false);
        buttonValueMap.put("dpad_right", false);

        fallingButtonValueMap = new HashMap<>();
        fallingButtonValueMap.put("x", false);
        fallingButtonValueMap.put("y", false);
        fallingButtonValueMap.put("a", false);
        fallingButtonValueMap.put("b", false);
        fallingButtonValueMap.put("left_bumper", false);
        fallingButtonValueMap.put("right_bumper", false);
        fallingButtonValueMap.put("left_stick", false);
        fallingButtonValueMap.put("right_stick", false);
        fallingButtonValueMap.put("dpad_up", false);
        fallingButtonValueMap.put("dpad_down", false);
        fallingButtonValueMap.put("dpad_left", false);
        fallingButtonValueMap.put("dpad_right", false);

        triggerMap = new HashMap<>();
        triggerMap.put("left_stick_x", gp -> gp.left_stick_x);
        triggerMap.put("left_stick_y", gp -> gp.left_stick_y);
        triggerMap.put("right_stick_x", gp -> gp.right_stick_x);
        triggerMap.put("right_stick_y", gp -> gp.right_stick_y);
        triggerMap.put("left_trigger", gp -> gp.left_trigger);
        triggerMap.put("right_trigger", gp -> gp.right_trigger);

        triggerValueMap = new HashMap<>();
        triggerValueMap.put("left_stick_x", 0.0f);
        triggerValueMap.put("left_stick_y", 0.0f);
        triggerValueMap.put("right_stick_x", 0.0f);
        triggerValueMap.put("right_stick_y", 0.0f);
        triggerValueMap.put("left_trigger", 0.0f);
        triggerValueMap.put("right_trigger", 0.0f);

        fallingTriggerValueMap = new HashMap<>();
        fallingTriggerValueMap.put("left_stick_x", 0.0f);
        fallingTriggerValueMap.put("left_stick_y", 0.0f);
        fallingTriggerValueMap.put("right_stick_x", 0.0f);
        fallingTriggerValueMap.put("right_stick_y", 0.0f);
        fallingTriggerValueMap.put("left_trigger", 0.0f);
        fallingTriggerValueMap.put("right_trigger", 0.0f);
    }
    private boolean getValueBoolean(Gamepad gamepad, String inputName) {
        Function<Gamepad, Boolean> extractor = buttonMap.get(inputName);
        return (extractor != null) ? extractor.apply(gamepad) : false;
    }

    private double getValueDouble(Gamepad gamepad, String inputName) {
        Function<Gamepad, Float> extractor = triggerMap.get(inputName);
        return (extractor != null) ? extractor.apply(gamepad) : 0.0f;
    }

    public void update() {
        prevGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad);

        for (Map.Entry<String, Function<Gamepad, Boolean>> entry : buttonMap.entrySet()) {
            Boolean prevValue = entry.getValue().apply(prevGamepad);
            Boolean currValue = entry.getValue().apply(currentGamepad);
            String key = entry.getKey();

            if (currValue && !prevValue) {
                buttonValueMap.put(key, true);
            }

            if (!currValue && prevValue) {
                fallingButtonValueMap.put(key, true);
            }
        }

        for (Map.Entry<String, Function<Gamepad, Float>> entry : triggerMap.entrySet()) {
            Float prevValue = entry.getValue().apply(prevGamepad);
            Float currValue = entry.getValue().apply(currentGamepad);
            String key = entry.getKey();
            triggerValueMap.put(key, currValue);
            fallingTriggerValueMap.put(key, prevValue);
        }
    }
    public double getValue(String buttonName) {
        if (triggerValueMap.containsKey(buttonName)) {
            return triggerValueMap.get(buttonName);
        }
        return 0.0;
    }

    public double getPrevValue(String buttonName) {
        if (fallingTriggerValueMap.containsKey(buttonName)) {
            return fallingTriggerValueMap.get(buttonName);
        }
        return 0.0;
    }

    public boolean justPressed(String buttonName) {
        return Boolean.TRUE.equals(buttonValueMap.get(buttonName));
    }

    public boolean justReleased(String buttonName) {
        return Boolean.TRUE.equals(fallingButtonValueMap.get(buttonName));
    }
}
