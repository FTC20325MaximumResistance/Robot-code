package org.firstinspires.ftc.teamcode.extra_crap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Tank_Drive", group = "TeleOp")
public class Tank_Drive extends OpMode {

    boolean inReverse = false;//reverse button is a
    boolean bWasPressed;
    double pos = 0.0;
    String posi;
    Hardware r = new Hardware();


    @Override
    public void init() {

        r.initRobot(this);

        composeTelemetry();

    }

    @Override
    public void loop() {
        double deflator;

        deflator = gamepad1.right_bumper ? .4 : .7;
        double power1 = -gamepad1.left_stick_y * deflator;
        double power2 = -gamepad1.right_stick_y * deflator;
        r.frontRight.setPower(power2);
        r.backRight.setPower(power2);
        r.frontLeft.setPower(power1);
        r.backLeft.setPower(power1);
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