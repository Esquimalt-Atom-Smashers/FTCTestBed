package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class BasicDriveOpMode extends LinearOpMode {

    protected ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx frontLeftMotor;
    private DcMotorEx frontRightMotor;
    private DcMotorEx rearLeftMotor;
    private DcMotorEx rearRightMotor;
    private final GamepadEx driverGamepad = new GamepadEx(gamepad1);

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveBaseConstants.FRONT_LEFT_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveBaseConstants.FRONT_RIGHT_MOTOR_NAME);
        rearLeftMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveBaseConstants.REAR_LEFT_MOTOR_NAME);
        rearRightMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveBaseConstants.REAR_RIGHT_MOTOR_NAME);

        configureMotors();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double forwardPower = Math.abs(driverGamepad.getLeftY()) >= 0.1 ? driverGamepad.getLeftY() : 0;
            double strafePower = Math.abs(driverGamepad.getLeftX()) >= 0.1 ? driverGamepad.getLeftX() : 0;
            double turnPower = Math.abs(driverGamepad.getRightX()) >= 0.1 ? driverGamepad.getRightX() : 0;

            telemetry.addData("Status", "Running");
            telemetry.addData("Run time", runtime.seconds());
            telemetry.addData("Forward", forwardPower);
            telemetry.addData("Strafe", strafePower);
            telemetry.addData("Turn", turnPower);
            telemetry.update();

            driveRobotCentric(forwardPower, strafePower, turnPower);
        }
    }

    private void configureMotors() {
        frontLeftMotor.setDirection(Constants.DriveBaseConstants.FRONT_LEFT_MOTOR_DIRECTION);
        frontRightMotor.setDirection(Constants.DriveBaseConstants.FRONT_RIGHT_MOTOR_DIRECTION);
        rearLeftMotor.setDirection(Constants.DriveBaseConstants.REAR_LEFT_MOTOR_DIRECTION);
        rearRightMotor.setDirection(Constants.DriveBaseConstants.REAR_RIGHT_MOTOR_DIRECTION);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetDriveEncoders();
    }

    private void resetDriveEncoders() {
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
}
