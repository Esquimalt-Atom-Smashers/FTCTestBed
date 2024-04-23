package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveBaseSubsystem;

public class Robot {
    private final OpMode opMode;
    public DriveBaseSubsystem driveBaseSubsystem;

    public Robot(OpMode opMode) {
        this.opMode = opMode;

        driveBaseSubsystem = new DriveBaseSubsystem(opMode.telemetry, opMode.hardwareMap, this);

        bindCommands();
    }

    private void bindCommands() {

    }

    public void run() {

    }
}
