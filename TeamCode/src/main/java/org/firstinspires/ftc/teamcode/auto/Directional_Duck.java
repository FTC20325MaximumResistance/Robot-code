package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
@SuppressWarnings("unused")
@Autonomous(name = "Directional Duck", group = "Auto")
public class Directional_Duck extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();
    LinearOpMode l;

    @Override
    public void runOpMode() throws InterruptedException {
        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();
        String location;
        r.ready_vision();
        //This waits until we start
        waitForStart();
        location = r.findDuck();
        r.end_vision();
        if (location.equals("right")){
            r.crane_lift(Hard_Auto.direction.TOP, 0.8);
        }
        if (location.equals("center")){
            r.crane_lift(Hard_Auto.direction.MIDDLE, 0.8);
        }
        if (location.equals("left")){
            r.crane_lift(Hard_Auto.direction.BOTTOM, 0.8);
        }


    }
}