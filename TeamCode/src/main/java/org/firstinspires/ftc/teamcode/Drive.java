package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Drive", group = "TeleOp")
public class Drive extends OpMode {

    boolean inReverse = false;//reverse button is a
    boolean bWasPressed;
    boolean craneDown = true;
    boolean aWasPressed = false;
    boolean precision_mode = false;//Precision mode makes the robot move slower for precision
    boolean speed_mode = false;//Speed mode makes the robot move fast for when it has to
    double pos = 0.0;
    Hardware r = new Hardware();
    Hard_Auto a = new Hard_Auto();



    @Override
    public void init() {

        r.initRobot(this);

        composeTelemetry();

    }

    @Override
    public void loop() {
        {
            /*Message to future Evan: Don't mess with this
            Everything down here you will not understand
            Let Ben mess with this and get to learning how to use java
            stop scrolling down here
            I said stop!
            No Evan...
            Don't
            This is painful down here
            Stop
            Do you like black holes?
            You will somehow create one if you
            mess with this
            */

            double deflator;

            deflator = gamepad1.right_bumper ? .4 : .7;

            if (gamepad1.left_bumper)
                deflator = 0.9;
                gamepad1.rumble(500);
            if (gamepad1.right_bumper){
                gamepad1.rumble(500);
            }
            //legacy code that runs our mecanum drive wheels

            //this first section creates the variables that will be used later

            if (gamepad1.circle && !bWasPressed)
                inReverse = !inReverse;
            bWasPressed = gamepad1.circle;
            //first we must translate the rectangular values of the joystick into polar coordinates;
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
                angle = Math.PI;
            if (y < 0 && x == 0)
                angle = 3 * Math.PI / 2;

            double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
            double rotation = -gamepad1.right_stick_x;

            if (inReverse)//reverse button
                angle += Math.toRadians(90);
            else
                angle += Math.toRadians(270);

            //equations taking the polar coordinates and turing them into motor powers
            double v1 = velocity * Math.cos(angle + (Math.PI / 4));
            double v2 = velocity * Math.sin(angle + (Math.PI / 4));
            double power1 = v1 - rotation;
            double power2 = v2 + rotation;
            double power3 = v2 - rotation;
            double power4 = v1 + rotation;

            r.frontLeft.setPower(power1 * deflator);
            r.frontRight.setPower(power2 * deflator);
            r.backLeft.setPower(power3 * deflator);
            r.backRight.setPower(power4 * deflator);

        }

        /*==========================================================================================
         * Start editing here!
          ========================================================================================*/

        // Max crane pos = 6276
        r.crane_lift.setPower(-gamepad2.right_stick_y * 0.7);
        r.spin.setPower(gamepad2.left_stick_x * 0.7);

        r.scoop.setPower(gamepad2.right_bumper ? 0.7 : 0);
        r.scoop.setPower(gamepad2.left_bumper ? -0.7 : 0);

        if(gamepad2.right_trigger != 0){
            pos+= 0.05;
            gamepad2.rumble(500);
            if (pos > 1) {
                pos = 1;
            }
        }
        else if (gamepad2.left_trigger != 0){
            pos -= 0.05;
            gamepad2.rumble(500);
            if (pos < 0){
                pos = 0;
            }
        }

        r.cranescoop.setPosition(pos);
        telemetry.update();
    }

    void composeTelemetry(){
        telemetry.addLine("Debug - ")
                .addData("X", new Func<String>() {
                    @Override
                    public String value() {
                        return String.valueOf(gamepad1.left_stick_x);
                    }
                })
                .addData("Y", new Func<String>() {
                    @Override
                    public String value() {
                        return String.valueOf(gamepad1.left_stick_y);
                    }
                })
                .addData("X", new Func<String>() {
                    @Override
                    public String value() {
                        return String.valueOf(gamepad1.right_stick_x);
                    }
                });
        telemetry.addLine("Gamepad")
                .addData("RT", new Func<String>() {
                    @Override
                    public String value() {
                        return String.valueOf(gamepad2.right_trigger);
                    }
                })
                .addData("TP F", new Func<String>() {
                    @Override
                    public String value() {
                        return String.valueOf(gamepad2.left_trigger);
                    }
                });
    }

}
