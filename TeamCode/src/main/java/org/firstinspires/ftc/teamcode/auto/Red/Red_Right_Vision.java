package org.firstinspires.ftc.teamcode.auto.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;

@SuppressWarnings("unused")
@Autonomous(name = "Red_Right_Vision", group = "Auto")
public class Red_Right_Vision extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();
    //Vision v;
    LinearOpMode l;

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.ready_vision();
        //v = new Vision(this);
        r.initAuto();
        //This waits until we start
        waitForStart();
        String where = r.findDuck();
        r.end_vision();
        r.crane_lift(Hard_Auto.direction.DOWN, 0.8);
        r.movedist(Hard_Auto.direction.LEFT, 0.8, 11);
        r.movedist(Hard_Auto.direction.FORWARD, 0.8, 2);
        r.degree_rotate(180, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.BACKWARD, 0.5, 15.75);
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
        r.movedist(Hard_Auto.direction.FORWARD, 0.8, 14);
        r.degree_rotate(190, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.RIGHT, 0.8, 11);
    }
}