package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drivetrains.MecanumDrive;

@TeleOp(name="Example Drivetrain")
public class ExampleDrivetrain extends OpMode {
    private MecanumDrive drivetrain;
    private double forwardSpeed = 1;
    private double strafeSpeed = 1;
    private double turnSpeed = 1;

    @Override
    public void init() {
        drivetrain = new MecanumDrive(hardwareMap, true);
    }

    @Override
    public void start() {}

    @Override
    public void loop() {
        drivetrain.update(-gamepad1.left_stick_y * forwardSpeed, -gamepad1.left_stick_x * strafeSpeed, -gamepad1.right_stick_x * turnSpeed);
    }
}
