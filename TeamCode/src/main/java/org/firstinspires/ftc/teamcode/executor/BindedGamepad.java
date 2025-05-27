package org.firstinspires.ftc.teamcode.executor;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class BindedGamepad {
    private Gamepad gamepad;
    private Gamepad prevGamepad;
    private Gamepad currentGamepad;
    private HashMap<String, Function<Gamepad, Boolean>> gamepadMap;
    private HashMap<String, Function<Gamepad, Float>> valueMap;
    private HashMap<String, Action> actionMap;
    private HashMap<String, Action> actionValueMap;
    private HashMap<String, Double> thresholdMap;

    public BindedGamepad(Gamepad gamepad) {
        this.gamepad = gamepad;
        gamepadMap = new HashMap<>();
        gamepadMap.put("left_stick_y", gp -> gp.x);
        gamepadMap.put("right_stick_x", gp -> gp.y);
        actionMap = new HashMap<>();
        valueMap = new HashMap<>();
        actionValueMap = new HashMap<>();
        thresholdMap = new HashMap<>();

        // some are thresholds, some are booleans
        // its all a big mess
    }
    public boolean getValue(Gamepad gamepad, String inputName) {
        Function<Gamepad, Boolean> extractor = gamepadMap.get(inputName);
        return (extractor != null) ? extractor.apply(gamepad) : false;
    }

    public double getValueDouble(Gamepad gamepad, String inputName) {
        Function<Gamepad, Float> extractor = valueMap.get(inputName);
        return (extractor != null) ? extractor.apply(gamepad) : 0.0f;
    }

    public void update() {
        prevGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad);

        for (Map.Entry<String, Function<Gamepad, Boolean>> entry : gamepadMap.entrySet()) {

            String key = entry.getKey(); // use this for applying the action
            Boolean prevValue = entry.getValue().apply(prevGamepad);
            Boolean currValue = entry.getValue().apply(currentGamepad);

            if (currValue && !prevValue) {
                // initialize all of them with a blank function but u can change this
                // actions will be like normal? but no real executor? you just declare them and bind
                // then you call start(), make sure robot is looping
                // these functions should be reset when start() is called
                // do this recursively: all actions below should be started
                actionMap.get(key).start();
            }
//            System.out.println("Key: " + key + ", Value: " + value);
        }

        for (Map.Entry<String, Function<Gamepad, Float>> entry : valueMap.entrySet()) {

            String key = entry.getKey(); // use this for applying the action
            Float prevValue = entry.getValue().apply(prevGamepad);
            Float currValue = entry.getValue().apply(currentGamepad);
            double threshold = thresholdMap.get(key);
            // maybe the threshold map should be an object: threshold, continuous (boolean)
            // something for later kevin to do

            if (currValue > threshold && !(prevValue > threshold)) {
                actionMap.get(key).start();
            }
//            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
    // so how can you make a button do multiple things?
    // in your action at the start, you can rebind the button to another action

    public void bind(String buttonName, Action action) {
        actionMap.put(buttonName, action);
    }

    public void bind(String buttonName, double threshold, Action action) {
        actionMap.put(buttonName, action);
    }
    // this one is useful for the drivetrains class. i can make a bindedGamepad and call
    // oh yeah drivetrain speed should be normalized with a maxPower attribute
    // setMovement(gamepad1.getValue("left_stick_y)*forwardSpeed, etc.)

    public double getValue(String buttonName) {
        return valueMap.get(buttonName).apply(currentGamepad);
    }

    public boolean justPressed(String buttonName) {
        // if its in the buttonMap then use rising edge
        // if its in the
    }


    public boolean justReleased(String buttonName) {

    }

    public void setThreshold(String buttonName, double threshold) {

    }

    public void getThreshold(String buttonName, double threshold) {

    }
}
