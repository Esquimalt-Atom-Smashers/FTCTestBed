package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveBaseSubsystem;

public class Robot {
    private final OpMode opMode;
    private final GamepadEx driverGamepad;
    private final GamepadEx operatorGamepad;
    public DriveBaseSubsystem driveBaseSubsystem;

    public Robot(OpMode opMode) {
        this.opMode = opMode;

        driverGamepad = new GamepadEx(opMode.gamepad1);
        operatorGamepad = new GamepadEx(opMode.gamepad2);

        driveBaseSubsystem = new DriveBaseSubsystem(opMode.telemetry, opMode.hardwareMap, this);
    }

    public void run() {
        double forwardPower = Math.abs(driverGamepad.getLeftY()) >= 0.1 ? driverGamepad.getLeftY() : 0;
        double strafePower = Math.abs(driverGamepad.getLeftX()) >= 0.1 ? driverGamepad.getLeftX() : 0;
        double turnPower = Math.abs(driverGamepad.getRightX()) >= 0.1 ? driverGamepad.getRightX() : 0;

        driveBaseSubsystem.driveRobotCentric(forwardPower, strafePower, turnPower);
    }
}