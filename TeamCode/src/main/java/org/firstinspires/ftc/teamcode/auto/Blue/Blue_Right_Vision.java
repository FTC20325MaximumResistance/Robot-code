package org.firstinspires.ftc.teamcode.auto.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.cranearm.CraneArm;

@SuppressWarnings("unused")
@Autonomous(name = "Blue_Right_Vision", group = "Auto")
public class Blue_Right_Vision extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();
    //Vision v;

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();
        r.ready_vision();
        CraneArm arm = new CraneArm(this, r.crane_lift);
        //This waits until we start
        waitForStart();
        String where = r.findDuck();
        r.end_vision();
        switch (where) {
            case "right":
                arm.get_dir(3);
                break;
            case "center":
                arm.get_dir(2);
                break;
            case "left":
                arm.get_dir(1);
                break;
        }
        arm.start();
        r.movedist(Hard_Auto.direction.FORWARD, 0.7, 4);
        r.movedist(Hard_Auto.direction.LEFT, 0.8, 11);
        r.degree_rotate(175, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.3, 13.25);
        r.scoop(Hard_Auto.direction.BACKWARD);
        r.waiter(1500);
        r.scoop(Hard_Auto.direction.FORWARD);
        arm.get_dir(0);
        arm.start();
        r.movedist(Hard_Auto.direction.LEFT, 0.7, 27);
        r.movedist(Hard_Auto.direction.FORWARD, 0.6, 9);
        r.carousel(Hard_Auto.direction.CWISE, 0.9, 4000);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.8, 11);
        /*r.movedist(Hard_Auto.direction.FORWARD, 0.8, 14);
        r.degree_rotate(175, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.RIGHT, 0.8, 11); */


    }
}