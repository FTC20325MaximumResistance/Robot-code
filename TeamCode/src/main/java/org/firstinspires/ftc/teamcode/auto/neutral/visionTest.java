package org.firstinspires.ftc.teamcode.auto.neutral;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@Autonomous(name = "visionTest", group = "Auto")
public class visionTest extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();
    @Override
    public void runOpMode() throws InterruptedException {
        r.initRobot(this);
        r.ready_vision();
        r.initAuto();
        waitForStart();
        String where = r.findDuck();
        r.end_vision();


    }
}
