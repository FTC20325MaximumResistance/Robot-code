package org.firstinspires.ftc.teamcode.auto.Red;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.Hardware;
@SuppressWarnings("unused")
@Autonomous(name = "Red_Left_Ducky", group = "Auto")
public class Red_Left_Ducky extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();
        r.movedist(Hard_Auto.direction.RIGHT, 0.3, 4);
        r.movedist(Hard_Auto.direction.FORWARD,0.6, 15);
        r.carousel(Hard_Auto.direction.CCWISE, 0.7, 8000);


    }
}