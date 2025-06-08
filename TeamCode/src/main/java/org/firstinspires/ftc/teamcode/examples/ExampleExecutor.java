package org.firstinspires.ftc.teamcode.examples;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.executor.actions.Action;
import org.firstinspires.ftc.teamcode.executor.actions.SequentialAction;
import org.firstinspires.ftc.teamcode.executor.actions.WaitAction;
import org.firstinspires.ftc.teamcode.executor.executors.Executor;

@Autonomous(name = "Example Executor")
public class ExampleExecutor extends OpMode {
    private Executor executor;
    private Servo servo;
    private SequentialAction openClaw;
    private SequentialAction closeClaw;

    @Override
    public void loop() {
        executor.update();
    }

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        openClaw = new SequentialAction(
                new Action(() -> servo.setPosition(1)),
                new WaitAction(1000)
        );
        closeClaw = new SequentialAction(
                new Action(() -> servo.setPosition(0)),
                new WaitAction(1000)
        );
        executor = new Executor(
                openClaw,
                closeClaw
        );
    }

    @Override
    public void start() {
        executor.start();
    }
}
