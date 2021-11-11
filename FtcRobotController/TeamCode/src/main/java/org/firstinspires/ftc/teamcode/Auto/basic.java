package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.hardware.Hardware;

@Autonomous(name = "basic", group = "Auto")
public class basic extends LinearOpMode {

    Hardware r = new Hardware();

    @Override
    public void runOpMode() throws InterruptedException {
        r.initRobot(this);
        r.initAuto();

        waitForStart();
    }
}
