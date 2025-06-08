package org.firstinspires.ftc.teamcode.controllers.basic;

import org.firstinspires.ftc.teamcode.util.Timer;
public class PIDFController {
    private double kP, kD, kF, kI;
    private Timer timer;
    private double maxPower;

    private double prevErrorVal;
    private double target;
    private double minIntegral, maxIntegral;
    private double totalError;

    private double period;
    private FeedForward feedForward;

    public double calculate(double current) {
        period = timer.getTime();
        if (period <= 0) {
            return 0;
        }

        double error = target - current;
        double dError = (error - prevErrorVal) / period;

        totalError += period * error;
        totalError = totalError < minIntegral ? minIntegral : Math.min(maxIntegral, totalError);

        double total = kP * error + kD * dError + kF * feedForward.calculate(current) + kI * totalError;
        if (Math.abs(total) > maxPower) {
            return Math.signum(total) * maxPower;
        }

        prevErrorVal = error;
        timer.reset();

        return total;
    }

    public PIDFController(double kp, double ki, double kd, double kf, double mp, FeedForward f) {
        kI = ki;
        kP = kp;
        kD = kd;
        kF = kf;
        maxPower = mp;
        feedForward = f;

        minIntegral = -1.0;
        maxIntegral = 1.0;
        totalError = 0;

        timer = new Timer();
        reset();
    }

    public void reset() {
        prevErrorVal = 0;
        timer.reset();
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double t) {
        target = t;
    }
    public double[] getCoefficients() {
        return new double[]{kP, kI, kD, kF};
    }

    public void setCoefficients(double kp, double ki, double kd, double kf) {
        kP = kp;
        kI = ki;
        kD = kd;
        kF = kf;
    }

    public void setP(double kp) {
        kP = kp;
    }

    public void setI(double ki) {
        kI = ki;
    }

    public void setD(double kd) {
        kD = kd;
    }

    public void setF(double kf) {
        kF = kf;
    }

    public double getP() {
        return kP;
    }

    public double getI() {
        return kI;
    }

    public double getD() {
        return kD;
    }

    public double getF() {
        return kF;
    }

    public double getPeriod() {
        return period;
    }
}
