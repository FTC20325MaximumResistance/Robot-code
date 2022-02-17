package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.Hardware;
@SuppressWarnings("unused")
@Autonomous(name = "Diagnostics", group = "Auto")
public class Diagnostics extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();
        r.crane_lift(Hard_Auto.direction.DOWN, 0.5);
        r.movedist(Hard_Auto.direction.FORWARD, 0.5, 6);
        r.waiter(1000);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.5, 6);
        r.waiter(1000);
        r.movedist(Hard_Auto.direction.LEFT, 0.5, 6);
        r.waiter(1000);
        r.movedist(Hard_Auto.direction.RIGHT, 0.5, 6);
        r.waiter(1000);
        r.start_intake(Hard_Auto.direction.IN);
        r.waiter(1000);
        r.stop_intake();
        r.waiter(1000);
        r.carousel(Hard_Auto.direction.CWISE, 0.5, 2000);
        r.waiter(1000);
        r.crane_lift(Hard_Auto.direction.BOTTOM, 0.5);
        r.waiter(1000);
        r.crane_lift(Hard_Auto.direction.MIDDLE, 0.5);
        r.waiter(1000);
        r.crane_lift(Hard_Auto.direction.TOP, 0.5);
        r.waiter(1000);
        r.scoop(Hard_Auto.direction.BACKWARD);
        r.waiter(1000);
        r.scoop(Hard_Auto.direction.FORWARD);
        r.waiter(1000);
        r.crane_lift(Hard_Auto.direction.DOWN, 0.5);


    }
}