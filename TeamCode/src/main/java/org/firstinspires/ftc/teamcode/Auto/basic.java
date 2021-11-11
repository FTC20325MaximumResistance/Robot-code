package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.hardware.Hardware;

@Autonomous(name = "basic", group = "Auto")
public class basic extends LinearOpMode {

    Hardware r = new Hardware();

    //This allows us to use the waiter method inside of our autonomous
    public void waiter(int time) {
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while (true) if (!(Time.milliseconds() < time)) break;
    }

    //This creates the method for four wheel drive it turns on the power, wait for the time to run out,
    //and then turns off the power
    public void move_y(Double power, int time){
        r.frontRight.setPower(power);
        r.frontLeft.setPower(power);
        r.backRight.setPower(power);
        r.backLeft.setPower(power);
        waiter(time);
        r.frontRight.setPower(0);
        r.frontLeft.setPower(0);
        r.backRight.setPower(0);
        r.backLeft.setPower(0);
    }

    //This will be for rotation, but I still need to do tests to figure it out
    public void rotate(String direction,int degrees){
        int time = degrees * 2;
        if(direction == "left"){
            r.frontRight.setPower(0.2);
            r.backRight.setPower(0.2);
            r.frontLeft.setPower(-0.2);
            r.backLeft.setPower(-0.2);
            waiter(time);
            r.frontRight.setPower(0);
            r.frontLeft.setPower(0);
            r.backRight.setPower(0);
            r.backLeft.setPower(0);
        }
        else if(direction == "right"){
            r.frontRight.setPower(-0.2);
            r.backRight.setPower(-0.2);
            r.frontLeft.setPower(0.2);
            r.backLeft.setPower(0.2);
            waiter(time);
            r.frontRight.setPower(0);
            r.frontLeft.setPower(0);
            r.backRight.setPower(0);
            r.backLeft.setPower(0);
        }
    }

    //This creates the method for strafing. I had to do some frantic googling, but it might work
    public void strafe(String direction, int time){
        if(direction == "left"){
            r.frontLeft.setPower(-0.5);
            r.backRight.setPower(-0.5);
            r.backLeft.setPower(0.5);
            r.frontRight.setPower(0.5);
            waiter(time);
            r.frontRight.setPower(0);
            r.frontLeft.setPower(0);
            r.backRight.setPower(0);
            r.backLeft.setPower(0);
        }
        else if(direction == "right"){
            r.frontLeft.setPower(0.5);
            r.backRight.setPower(0.5);
            r.backLeft.setPower(-0.5);
            r.frontRight.setPower(-0.5);
            waiter(time);
            r.frontRight.setPower(0);
            r.frontLeft.setPower(0);
            r.backRight.setPower(0);
            r.backLeft.setPower(0);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

        //This initializes the autonomous
        r.initRobot(this);
        r.initAuto();

        //This waits until we start
        waitForStart();

        //This is where the fun begins!
    }
}
