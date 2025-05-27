package org.firstinspires.ftc.teamcode.drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
// to find motor direction, it would be a good idea to include a testing opmode
public class MecanumDriveConstants {
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
    public static double powerMultiplier = 1;
    public static boolean useBreakMode;
}
