package org.firstinspires.ftc.teamcode.controllers.basic;

public class BangBangController {
    private double maxPower;
    private double target;
    public BangBangController() {
        this(1);
    }

    public BangBangController(double maxPower) {
        this.maxPower = maxPower;
    }

    public double calculate(double current) {
        if (current < target) {
            return maxPower;
        }
        return 0.0;
    }

    public void setTarget(double t) {
        target = t;
    }

    public double getTarget() {
        return target;
    }

    public void setMaxPower(double x) {
        maxPower = x;
    }

    public double getMaxPower() {
        return maxPower;
    }
}
