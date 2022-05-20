package org.firstinspires.ftc.teamcode.extra_crap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@SuppressWarnings("unused")
@Autonomous(name = "Playground", group = "Auto")
public class Playground extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();
        String location;
        //This waits until we start
        waitForStart();
        r.movedist(Hard_Auto.direction.LEFT, 0.5, 1);


    }
}