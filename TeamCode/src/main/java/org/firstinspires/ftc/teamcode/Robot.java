package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveBaseSubsystem;

public class Robot {
    public DriveBaseSubsystem driveBaseSubsystem;

    public Robot(OpMode opMode) {
        driveBaseSubsystem = new DriveBaseSubsystem(opMode.telemetry, opMode.hardwareMap, this);
    }
}
