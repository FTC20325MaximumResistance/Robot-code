package org.firstinspires.ftc.teamcode.auto.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;

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

        //v = new Vision(this);
        //This waits until we start
        waitForStart();
        String where = r.findDuck();
        r.end_vision();
        r.crane_lift(Hard_Auto.direction.DOWN, 0.8);
        r.movedist(Hard_Auto.direction.FORWARD, 0.7, 4);
        r.movedist(Hard_Auto.direction.LEFT, 0.8, 11);
        r.degree_rotate(175, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.3, 13.25);
        switch (where) {
            case "right":
                r.crane_lift(Hard_Auto.direction.TOP, 0.9);
                break;
            case "center":
                r.crane_lift(Hard_Auto.direction.MIDDLE, 0.9);
                break;
            case "left":
                r.crane_lift(Hard_Auto.direction.BOTTOM, 0.9);
                break;
        }
        r.scoop(Hard_Auto.direction.BACKWARD);
        r.waiter(1500);
        r.scoop(Hard_Auto.direction.FORWARD);
        r.crane_lift(Hard_Auto.direction.DOWN, 0.9);
        r.movedist(Hard_Auto.direction.LEFT, 0.7, 27);
        r.movedist(Hard_Auto.direction.FORWARD, 0.6, 9);
        r.carousel(Hard_Auto.direction.CWISE, 0.9, 4000);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.8, 11);
        /*r.movedist(Hard_Auto.direction.FORWARD, 0.8, 14);
        r.degree_rotate(175, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.RIGHT, 0.8, 11); */


    }
}