package org.firstinspires.ftc.teamcode.auto.Red;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@SuppressWarnings("unused")
@Autonomous(name = "Red_Left_Ducky_And_Warehouse", group = "Auto")
public class Red_Left_Ducky_And_Warehouse extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();

        r.movedist(Hard_Auto.direction.RIGHT, 0.3, 2);
        r.movedist(Hard_Auto.direction.FORWARD,0.6, 15.15);
        r.carousel(Hard_Auto.direction.CCWISE, 0.7, 8000);
        r.movedist(Hard_Auto.direction.RIGHT, 0.6, 11);
        r.movedist(Hard_Auto.direction.FORWARD, 0.3, 8.5);
        r.degree_rotate(180,0.3, Hard_Auto.direction.LEFT);


    }
}