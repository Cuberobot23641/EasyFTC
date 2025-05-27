package org.firstinspires.ftc.teamcode.controllers.basic;

public class PDFLController {
    interface FeedForward {
        double calculate(double t);
    }
    // same thing, but a lower limit directional term as l and a feedforward f
    private double kP, kI, kD, kF, kL;
    private double setPoint;
    private double measuredValue;

    private double errorVal_p;
    private double errorVal_v;

    private double prevErrorVal;

    private double

    private double lastTimeStamp;
    private double period;
    private FeedForward feedForward;

    public PDFLController(double kp, double kd, FeedForward f, double kL) {
        feedForward = f;
    }

    public double calculate() {
        prevErrorVal = errorVal_p;

        double currentTimeStamp = (double) System.nanoTime() / 1E9;
        if (lastTimeStamp == 0) lastTimeStamp = currentTimeStamp;
        period = currentTimeStamp - lastTimeStamp;
        lastTimeStamp = currentTimeStamp;

        double error = setPoint - measuredValue;
        double dError = (error - prevErrorVal) / period;
        prevErrorVal = error;

//        if (measuredValue == pv) {
//            errorVal_p = setPoint - measuredValue;
//        } else {
//            errorVal_p = setPoint - pv;
//            measuredValue = pv;
//        }
//
//        if (Math.abs(period) > 1E-6) {
//            errorVal_v = (errorVal_p - prevErrorVal) / period;
//        } else {
//            errorVal_v = 0;
//        }

        return kP * error + kD * dError + kF * feedForward.calculate(target) + kL * Math.signum(errorVal_p);
    }

    /**
     * The base constructor for the PIDF controller
     */
    public PDFLController(double kp, double ki, double kd, double kf) {
        this(kp, ki, kd, kf, 0, 0);
    }

    /**
     * This is the full constructor for the PIDF controller. Our PIDF controller
     * includes a feed-forward value which is useful for fighting friction and gravity.
     * Our errorVal represents the return of e(t) and prevErrorVal is the previous error.
     *
     * @param sp The setpoint of the pid control loop.
     * @param pv The measured value of he pid control loop. We want sp = pv, or to the degree
     *           such that sp - pv, or e(t) < tolerance.
     */
    public PDFLController(double kp, double ki, double kd, double kf, double sp, double pv) {
        kP = kp;
        kI = ki;
        kD = kd;
        kF = kf;

        setPoint = sp;
        measuredValue = pv;

        lastTimeStamp = 0;
        period = 0;

        errorVal_p = setPoint - measuredValue;
        reset();
    }

    public void reset() {
        prevErrorVal = 0;
        lastTimeStamp = 0;
    }



    /**
     * Returns the current setpoint of the PIDFController.
     *
     * @return The current setpoint.
     */
    public double getSetPoint() {
        return setPoint;
    }

    /**
     * Sets the setpoint for the PIDFController
     *
     * @param sp The desired setpoint.
     */
    public void setSetPoint(double sp) {
        setPoint = sp;
        errorVal_p = setPoint - measuredValue;
        errorVal_v = (errorVal_p - prevErrorVal) / period;
    }



    /**
     * @return the PIDF coefficients
     */
    public double[] getCoefficients() {
        return new double[]{kP, kI, kD, kF};
    }

    /**
     * @return the positional error e(t)
     */
    public double getPositionError() {
        return errorVal_p;
    }

    /**
     * @return the tolerances of the controller
     */

    /**
     * @return the velocity error e'(t)
     */
    public double getVelocityError() {
        return errorVal_v;
    }

    /**
     * Calculates the next output of the PIDF controller.
     *
     * @return the next output using the current measured value via
     * {@link #calculate(double)}.
     */

    /**
     * Calculates the next output of the PIDF controller.
     *
     * @param pv The given measured value.
     * @param sp The given setpoint.
     * @return the next output using the given measurd value via
     * {@link #calculate(double)}.
     */
    public double calculate(double pv, double sp) {
        // set the setpoint to the provided value
        setSetPoint(sp);
        return calculate(pv);
    }

    /**
     * Calculates the control value, u(t).
     *
     * @param pv The current measurement of the process variable.
     * @return the value produced by u(t).
     */
    public double calculate(double pv) {
        prevErrorVal = errorVal_p;

        double currentTimeStamp = (double) System.nanoTime() / 1E9;
        if (lastTimeStamp == 0) lastTimeStamp = currentTimeStamp;
        period = currentTimeStamp - lastTimeStamp;
        lastTimeStamp = currentTimeStamp;

        if (measuredValue == pv) {
            errorVal_p = setPoint - measuredValue;
        } else {
            errorVal_p = setPoint - pv;
            measuredValue = pv;
        }

        if (Math.abs(period) > 1E-6) {
            errorVal_v = (errorVal_p - prevErrorVal) / period;
        } else {
            errorVal_v = 0;
        }

        // returns u(t)
        return kP * errorVal_p + kD * errorVal_v + kF * setPoint;
    }

    public void setPIDF(double kp, double ki, double kd, double kf) {
        kP = kp;
        kI = ki;
        kD = kd;
        kF = kf;
    }


    public void clearTotalError() {
        totalError = 0;
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



