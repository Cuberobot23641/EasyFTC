package org.firstinspires.ftc.teamcode.controllers.basic;

import org.firstinspires.ftc.teamcode.util.Timer;
public class PDFLController {
    private double kP, kD, kF, kL;
    private Timer timer;
    private double maxPower;

    private double prevErrorVal;
    private double target;

    private double period;
    private FeedForward feedForward;

    public double calculate(double current) {
        period = timer.getTime();
        if (period <= 0) {
            return 0;
        }

        double error = target - current;
        double dError = (error - prevErrorVal) / period;

        prevErrorVal = error;
        timer.reset();

        double total = kP * error + kD * dError + kF * feedForward.calculate(target) + kL * Math.signum(error);
        if (Math.abs(total) > maxPower) {
            return Math.signum(total) * maxPower;
        }
        return total;
    }

    public PDFLController(double kp, double kd, double kf, double kl, double mp, FeedForward f) {
        kL = kl;
        kP = kp;
        kD = kd;
        kF = kf;
        maxPower = mp;
        feedForward = f;

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
        return new double[]{kP, kL, kD, kF};
    }

    public void setCoefficients(double kp, double kd, double kf, double kl) {
        kP = kp;
        kL = kl;
        kD = kd;
        kF = kf;
    }

    public void setP(double kp) {
        kP = kp;
    }

    public void setL(double kl) {
        kL = kl;
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

    public double getL() {
        return kL;
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



