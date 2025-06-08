package org.firstinspires.ftc.teamcode.drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.drivetrains.DrivetrainConstants;

public class DTConstants {
    static {
        DrivetrainConstants.leftFrontMotorName = "front_left";
        DrivetrainConstants.leftRearMotorName = "back_left";
        DrivetrainConstants.rightFrontMotorName = "front_right";
        DrivetrainConstants.rightRearMotorName = "back_right";

        DrivetrainConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        DrivetrainConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        DrivetrainConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        DrivetrainConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        DrivetrainConstants.imuName = "imu";
        DrivetrainConstants.logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        DrivetrainConstants.usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;

        DrivetrainConstants.powerMultiplier = 1;
        DrivetrainConstants.useBreakMode = true;
    }
}
