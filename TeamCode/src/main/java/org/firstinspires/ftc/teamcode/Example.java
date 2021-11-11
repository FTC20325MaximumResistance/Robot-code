package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.hardware.Hardware;

@TeleOp(name = "Example", group = "TeleOp")
public class Example extends OpMode {

    boolean inReverse = false;//reverse button is b
    boolean bWasPressed = false;

    Hardware r = new Hardware();

    @Override
    public void init() {

        r.initRobot(this);

    }

    @Override
    public void loop() {
        {
            //Message to future Evan: Don't mess with this
            double deflator;
            deflator = gamepad1.right_bumper ? .4 : .9;
            if (gamepad1.left_bumper)
                deflator = 1;
            //legacy code that runs our mecanum drive wheels in any direction we want
            //this first section creates the variables that will be used later
            if (gamepad1.b && !bWasPressed)
                inReverse = !inReverse;
            bWasPressed = gamepad1.b;//first we must translate the rectangular values of the joystick into polar coordinates;
            double y = -1 * gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double angle = 0;
            if (y > 0 && x > 0)//quadrant 1
                angle = Math.atan(y / x);
            else {
                double angle1 = Math.toRadians(180) + Math.atan(y / x);
                if (y > 0 && x < 0)//quadrant 2
                    angle = angle1;
                else if (y < 0 && x < 0)//quadrant 3
                    angle = angle1;
                else if (y < 0 && x > 0)//quadrant 4
                    angle = Math.toRadians(360) + Math.atan(y / x);
            }
            if (y == 0 && x > 1)
                angle = 0;
            if (y > 0 && x == 0)
                angle = Math.PI / 2;
            if (y == 0 && x < 0)
                angle = 3 * Math.PI / 2;
           double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
            double rotation = -(gamepad1.right_stick_x);
            if (inReverse)//reverse button
                angle += Math.toRadians(180);
            angle += Math.toRadians(180);
            //equations taking the polar coordinates and turing them into motor powers
            double power1 = velocity * Math.cos(angle + (Math.PI / 4)) - rotation;
            double power2 = velocity * Math.sin(angle + (Math.PI / 4)) + rotation;
            double power3 = velocity * Math.sin(angle + (Math.PI / 4)) - rotation;
            double power4 = velocity * Math.cos(angle + (Math.PI / 4)) + rotation;
            r.frontLeft.setPower(power1 * deflator);
            r.frontRight.setPower(power2 * deflator);
            r.backLeft.setPower(power3 * deflator);
            r.backRight.setPower(power4 * deflator);
        }

        //This entire bottom section is dedicated to the extra non-drive motors
        //This *may* (and when I say may I mean probably) become useless with the next design

        //This controls the ducky spinner and it's reverse function
        if(gamepad1.y){
            if(inReverse) {
                r.spin.setPower(-0.6);
            }
            else{
                r.spin.setPower(0.6);
            }
        }else{
            r.spin.setPower(0);
        }

        //This controls the scoop
        if(gamepad1.left_bumper){
            r.scoop.setPower(0.5);
        }else if(gamepad1.right_bumper){
            r.scoop.setPower(-0.5);
        }else{
            r.scoop.setPower(0);
        }

    }
}
