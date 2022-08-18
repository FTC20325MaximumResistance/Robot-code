package org.firstinspires.ftc.teamcode.auto.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@SuppressWarnings("unused")
@Autonomous(name = "Red_Right_Supply_Area", group = "Auto")
public class Red_Right_Supply_Area extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();
        r.movedist(Hard_Auto.direction.FORWARD, 0.6, 22);
    }
}