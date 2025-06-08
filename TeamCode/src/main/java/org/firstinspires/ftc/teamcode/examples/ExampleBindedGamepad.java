package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.executor.BindedGamepad;
import org.firstinspires.ftc.teamcode.executor.actions.Action;

@TeleOp(name="Example Binded Gamepad")
public class ExampleBindedGamepad extends OpMode {
    private DcMotorEx motor;
    private BindedGamepad bindedGamepad;
    private Action forwardMotor;
    private Action reverseMotor;
    
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        forwardMotor = new Action(() -> motor.setPower(0.5));
        reverseMotor = new Action(() -> motor.setPower(-0.5));

        bindedGamepad = new BindedGamepad(gamepad1);
        bindedGamepad.bind("x", forwardMotor);
        bindedGamepad.bind("y", reverseMotor);
    }

    @Override
    public void start() {}

    @Override
    public void loop() {
        bindedGamepad.update();
    }
}
