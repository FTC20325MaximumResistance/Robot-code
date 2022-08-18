package org.firstinspires.ftc.teamcode.extra_crap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@SuppressWarnings("unused")
@Autonomous(name = "reset_crane", group = "Auto")
public class reset_crane extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();
        r.crane_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }
}