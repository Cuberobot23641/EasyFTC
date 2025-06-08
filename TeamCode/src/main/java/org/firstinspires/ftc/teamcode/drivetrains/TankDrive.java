package org.firstinspires.ftc.teamcode.drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TankDrive {
    private DcMotorEx fl, bl, fr, br;
    private HardwareMap hardwareMap;
    private double powerScaler = 1;
    private boolean robotCentric = true;
    private IMU imu;
    public TankDrive(HardwareMap hardwareMap, boolean robotCentric) {
        this.hardwareMap = hardwareMap;
        this.robotCentric = robotCentric;
        powerScaler = DrivetrainConstants.powerMultiplier;
        fl = this.hardwareMap.get(DcMotorEx.class, DrivetrainConstants.leftFrontMotorName);
        bl = this.hardwareMap.get(DcMotorEx.class, DrivetrainConstants.leftRearMotorName);
        fr = this.hardwareMap.get(DcMotorEx.class, DrivetrainConstants.rightFrontMotorName);
        br = this.hardwareMap.get(DcMotorEx.class, DrivetrainConstants.rightRearMotorName);
        fl.setDirection(DrivetrainConstants.leftFrontMotorDirection);
        bl.setDirection(DrivetrainConstants.leftRearMotorDirection);
        fr.setDirection(DrivetrainConstants.rightFrontMotorDirection);
        br.setDirection(DrivetrainConstants.rightRearMotorDirection);

        if (DrivetrainConstants.useBreakMode) {
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
        imu = hardwareMap.get(IMU.class, DrivetrainConstants.imuName);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(DrivetrainConstants.logoDirection, DrivetrainConstants.usbDirection));
        imu.initialize(parameters);
    }

    public void update(double y, double x, double rx) {
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

            double max = Math.max(Math.abs(leftPower), Math.abs(rightPower)) / powerScaler;
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