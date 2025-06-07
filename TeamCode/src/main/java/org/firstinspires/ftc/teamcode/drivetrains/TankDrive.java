package org.firstinspires.ftc.teamcode.drivetrains;

import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.imuName;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.leftFrontMotorDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.leftFrontMotorName;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.leftRearMotorDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.leftRearMotorName;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.logoDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.powerMultiplier;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.rightFrontMotorDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.rightFrontMotorName;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.rightRearMotorDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.rightRearMotorName;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.usbDirection;
import static org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants.useBreakMode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drivetrains.MecanumDriveConstants;

public class TankDrive {
    private DcMotorEx fl, bl, fr, br;
    private HardwareMap hardwareMap;
    private double powerScaler = powerMultiplier;
    private boolean robotCentric = true;
    private IMU imu;

    // somehow add movement vectors? idk
    public TankDrive(HardwareMap hardwareMap, MecanumDriveConstants driveConstants, boolean robotCentric) {
        this.hardwareMap = hardwareMap;
        this.robotCentric = robotCentric;
        fl = this.hardwareMap.get(DcMotorEx.class, leftFrontMotorName);
        bl = this.hardwareMap.get(DcMotorEx.class, leftRearMotorName);
        fr = this.hardwareMap.get(DcMotorEx.class, rightFrontMotorName);
        br = this.hardwareMap.get(DcMotorEx.class, rightRearMotorName);
        fl.setDirection(leftFrontMotorDirection);
        bl.setDirection(leftRearMotorDirection);
        fr.setDirection(rightFrontMotorDirection);
        br.setDirection(rightRearMotorDirection);

        if (useBreakMode) {
            fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        imu = hardwareMap.get(IMU.class, imuName);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection));
        imu.initialize(parameters);
    }

    public void loop(double y, double x, double rx) {
        double frontLeftPower;
        double backLeftPower;
        double frontRightPower;
        double backRightPower;
        if (robotCentric) {
            double leftPower = y + rx/2;
            double rightPower = y - rx/2;

            double max = Math.max(Math.abs(leftPower), Math.abs(rightPower)) / powerScaler;
            if (max > 1.0) {
                leftPower /= max;
                rightPower /= max;
            }

            frontLeftPower = leftPower;
            backLeftPower = leftPower;
            frontRightPower = rightPower;
            backRightPower = rightPower;
        } else {
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            double leftPower = rotY + rx/2;
            double rightPower = rotY - rx/2;

            double max = Math.max(Math.abs(leftPower), Math.abs(rightPower)) / powerMultiplier;
            if (max > 1.0) {
                leftPower /= max;
                rightPower /= max;
            }

            frontLeftPower = leftPower;
            backLeftPower = leftPower;
            frontRightPower = rightPower;
            backRightPower = rightPower;
        }
        fl.setPower(frontLeftPower);
        bl.setPower(backLeftPower);
        fr.setPower(frontRightPower);
        br.setPower(backRightPower);
    }

    public void setPowerScaler(double powerScaler) {
        this.powerScaler = powerScaler;
    }
}