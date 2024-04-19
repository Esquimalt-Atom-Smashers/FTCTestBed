package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

public class CustomSubsystemBase extends SubsystemBase {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private Robot robot;

    public CustomSubsystemBase(Telemetry telemetry, HardwareMap hardwareMap, Robot robot) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
        this.robot = robot;
        setDefaultCommand(getDefaultCommand());
    }

    public RunCommand getDefaultCommand() {
        return new RunCommand(() -> {}, this);
    }
}
