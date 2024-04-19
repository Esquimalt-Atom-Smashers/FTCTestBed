package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.DriveBaseConstants;
import org.firstinspires.ftc.teamcode.Robot;

public class DriveBaseSubsystem extends CustomSubsystemBase {

    public final DcMotorEx frontLeftMotor;
    public final DcMotorEx frontRightMotor;
    public final DcMotorEx rearLeftMotor;
    public final DcMotorEx rearRightMotor;

    public DriveBaseSubsystem(Telemetry telemetry, HardwareMap hardwareMap, Robot robot) {
        super(telemetry, hardwareMap, robot);

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, DriveBaseConstants.FRONT_LEFT_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, DriveBaseConstants.FRONT_RIGHT_MOTOR_NAME);
        rearLeftMotor = hardwareMap.get(DcMotorEx.class, DriveBaseConstants.REAR_LEFT_MOTOR_NAME);
        rearRightMotor = hardwareMap.get(DcMotorEx.class, DriveBaseConstants.REAR_RIGHT_MOTOR_NAME);

        configureMotors();

    }

    private void configureMotors() {
        frontLeftMotor.setDirection(DriveBaseConstants.FRONT_LEFT_MOTOR_DIRECTION);
        frontRightMotor.setDirection(DriveBaseConstants.FRONT_RIGHT_MOTOR_DIRECTION);
        rearLeftMotor.setDirection(DriveBaseConstants.REAR_LEFT_MOTOR_DIRECTION);
        rearRightMotor.setDirection(DriveBaseConstants.REAR_RIGHT_MOTOR_DIRECTION);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
    }

    public void resetEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void driveRobotCentric(double forward, double strafe, double turn) {
        double frontLeftPower = Range.clip(forward - strafe + turn, -1 , 1);
        double frontRightPower = Range.clip(forward - strafe - turn, -1 , 1);
        double rearLeftPower = Range.clip(forward + strafe + turn, -1 , 1);
        double rearRightPower = Range.clip(forward + strafe - turn, -1 , 1);

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        rearLeftMotor.setPower(rearLeftPower);
        rearRightMotor.setPower(rearRightPower);
    }

    public RunCommand getDefaultCommand(GamepadEx driverGamepad, GamepadEx operatorGamepad) {
        return new RunCommand(() -> {
            double forwardPower = Math.abs(driverGamepad.getLeftY()) >= 0.1 ? driverGamepad.getLeftY() : 0;
            double strafePower = Math.abs(driverGamepad.getLeftX()) >= 0.1 ? driverGamepad.getLeftX() : 0;
            double turnPower = Math.abs(driverGamepad.getRightX()) >= 0.1 ? driverGamepad.getRightX() : 0;

            driveRobotCentric(forwardPower, strafePower, turnPower);

        }, this);
    }
}
