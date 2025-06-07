package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.controllers.basic.PDFLController;

public class ExampleController {
    private PDFLController pdfl;
    private DcMotorEx motor;
    public ExampleController(HardwareMap hardwareMap) {
        pdfl = new PDFLController(0.1, 0.001, 0.01, 0.001, 1, (t) -> t);
        // many feedforwards exist, this one is linear
        // say you wanted to do a cosine of the angle where you need ticks to radians
        // pdfl = new PDFLController(0.1, 0.001, 0.01, 0.001, 1, (t) -> Math.cos(t/100));
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        // configure motor direction, etc.
    }
    public void loop() {
        double currentPos = motor.getCurrentPosition();
        pdfl.calculate(currentPos);
    }
    public void setTarget(double t) {
        pdfl.setTarget(t);
    }
}
