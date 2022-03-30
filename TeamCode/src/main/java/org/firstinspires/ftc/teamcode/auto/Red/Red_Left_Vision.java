package org.firstinspires.ftc.teamcode.auto.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;

@SuppressWarnings("unused")
@Autonomous(name = "Red_Left_Vision", group = "Auto")
public class Red_Left_Vision extends LinearOpMode {

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
        telemetry.setAutoClear(false);
        telemetry.addData("Crane Pos", where);
        telemetry.update();
        r.crane_lift(Hard_Auto.direction.DOWN, 0.8);
        r.movedist(Hard_Auto.direction.FORWARD, 0.8, 8);
        r.movedist(Hard_Auto.direction.RIGHT, 0.8, 13);
        r.degree_rotate(180, 0.7, Hard_Auto.direction.RIGHT);
        switch (where) {
            case "right":
                r.movedist(Hard_Auto.direction.BACKWARD, 0.5, 13.25);
                r.crane_lift(Hard_Auto.direction.TOP, 0.9);
                break;
            case "center":
                r.movedist(Hard_Auto.direction.BACKWARD, 0.5, 13.25);
                r.crane_lift(Hard_Auto.direction.MIDDLE, 0.9);
                break;
            case "left":
                r.movedist(Hard_Auto.direction.BACKWARD, 0.5, 13);
                r.crane_lift(Hard_Auto.direction.BOTTOM, 0.9);
                break;
        }
        r.scoop(Hard_Auto.direction.BACKWARD);
        r.waiter(1500);
        r.scoop(Hard_Auto.direction.FORWARD);
        r.crane_lift(Hard_Auto.direction.DOWN, 0.9);
        r.movedist(Hard_Auto.direction.FORWARD, 0.8, 13);
        r.degree_rotate(90, 0.7, Hard_Auto.direction.RIGHT);
        r.movedist(Hard_Auto.direction.FORWARD, 0.8, 36.5);
        r.carousel(Hard_Auto.direction.CCWISE, 0.8, 4000);
        r.movedist(Hard_Auto.direction.RIGHT, 0.8, 10.5);
        r.movedist(Hard_Auto.direction.FORWARD,0.8, 6);
        r.degree_rotate(180, 0.7, Hard_Auto.direction.RIGHT);
    }
}