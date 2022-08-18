package org.firstinspires.ftc.teamcode.auto.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.Hardware;
@SuppressWarnings("unused")
@Autonomous(name = "Blue_Right_Warehouse", group = "Auto")
public class Blue_Right_Warehouse extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {

        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();


//        r.setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.movedist(Hard_Auto.direction.FORWARD, 0.6, 27);
        r.movedist(Hard_Auto.direction.RIGHT, 0.6, 10.5);
        r.degree_rotate(180, 0.3, Hard_Auto.direction.RIGHT);



    }
}