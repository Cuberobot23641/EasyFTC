package org.firstinspires.ftc.teamcode.drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

public class DrivetrainConstants {
    public static String leftFrontMotorName;
    public static String leftRearMotorName;
    public static String rightFrontMotorName;
    public static String rightRearMotorName;
    public static DcMotorSimple.Direction leftFrontMotorDirection;
    public static DcMotorSimple.Direction rightFrontMotorDirection;
    public static DcMotorSimple.Direction leftRearMotorDirection;
    public static DcMotorSimple.Direction rightRearMotorDirection;
    public static String imuName;
    public static RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    public static RevHubOrientationOnRobot.UsbFacingDirection usbDirection;
    public static double powerMultiplier;
    public static boolean useBreakMode;

    public DrivetrainConstants() {}

    static {
        leftFrontMotorName = "leftFront";
        leftRearMotorName = "leftRear";
        rightFrontMotorName = "rightFront";
        rightRearMotorName = "rightRear";
        leftFrontMotorDirection = Direction.REVERSE;
        rightFrontMotorDirection = Direction.REVERSE;
        leftRearMotorDirection = Direction.FORWARD;
        rightRearMotorDirection = Direction.FORWARD;
        imuName = "imu";
        logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        powerMultiplier = 1;
        useBreakMode = true;
    }
}
