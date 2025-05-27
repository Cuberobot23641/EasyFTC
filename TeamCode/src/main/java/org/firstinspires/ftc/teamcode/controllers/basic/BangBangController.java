package org.firstinspires.ftc.teamcode.controllers.basic;

public class BangBangController {
    private double maxPower;
    private double errorThreshold;
    private double target;
    public BangBangController() {
        this(1, 0);
    }

    public BangBangController(double maxPower, double errorThreshold) {
        this.maxPower = maxPower;
        this.errorThreshold = errorThreshold;
    }

    public double calculate(double current, double t) {
        target = t;
        if (Math.abs(target - current) > errorThreshold) {
            return Math.signum(target - current) * maxPower;
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

    public void setErrorThreshold(double x) {
        errorThreshold = x;
    }

    public double getMaxPower() {
        return maxPower;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }
}
