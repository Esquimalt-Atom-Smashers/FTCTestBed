package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class BasicDriveOpMode extends LinearOpMode {

    protected ElapsedTime runtime = new ElapsedTime();
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            robot.run();
            telemetry.update();
        }
    }
}
