package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Func;

public class Hard_Auto extends Hardware{


    @Override
    public void initAuto(){
        super.initAuto();
    }

    @Override
    public void initRobot(OpMode opMode){
        super.initRobot(opMode);

        // TODO: Home the lift


    }

    /**
     * Move the robot in a specific way
     * @param dir Direction you want to move
     * @param power The power being sent to the motors
     */
    public void move(direction dir, double power){
        switch (dir){
            case FORWARD:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(power);
                break;
            case BACKWARD:
                frontLeft.setPower(-power);
                frontRight.setPower(-power);
                backLeft.setPower(-power);
                backRight.setPower(-power);
                break;
            case LEFT:
                frontLeft.setPower(-power);
                backLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(-power);
                break;
            case RIGHT:
                frontLeft.setPower(power);
                backLeft.setPower(-power);
                frontRight.setPower(-power);
                backRight.setPower(power);
                break;
            case CWISE:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backLeft.setPower(-power);
                backRight.setPower(-power);
                break;
            case CCWISE:
                frontLeft.setPower(-power);
                frontRight.setPower(-power);
                backLeft.setPower(power);
                backRight.setPower(power);
                break;
        }
    }

    /****************************************************************
     * Move the robot a specific distance
     * @param dir The distance you want to move
     * @param power The power you want to send to the motors
     * @param distance The distance you want to move in inches
     ****************************************************************/
    public void movedist(direction dir, double power, double distance){
        opMode.telemetry.addData("Started","True");
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waiter(500);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double ticks_for_dist = ticks_per_inch * distance;
        switch (dir) {
            case FORWARD:
                frontLeft.setTargetPosition((int) ticks_for_dist);
                frontRight.setTargetPosition((int) ticks_for_dist);
                backLeft.setTargetPosition((int) ticks_for_dist);
                backRight.setTargetPosition((int) ticks_for_dist);
                break;
            case BACKWARD:
                frontLeft.setTargetPosition((int) -ticks_for_dist);
                frontRight.setTargetPosition((int) -ticks_for_dist);
                backLeft.setTargetPosition((int) -ticks_for_dist);
                backRight.setTargetPosition((int) -ticks_for_dist);
                break;
            case LEFT:
                opMode.telemetry.addData("In switch", "True");
                frontLeft.setTargetPosition((int) -ticks_for_dist);
                opMode.telemetry.addData("Past FLM", "True");
                frontRight.setTargetPosition((int) ticks_for_dist);
                opMode.telemetry.addData("Past FRM", "True");
                backLeft.setTargetPosition((int)   ticks_for_dist);
                opMode.telemetry.addData("Past BLM", "True");
                backRight.setTargetPosition((int) -ticks_for_dist);
                opMode.telemetry.addData("BRM", "True");
                break;
            case RIGHT:
                frontLeft.setTargetPosition((int)   ticks_for_dist);
                frontRight.setTargetPosition((int) -ticks_for_dist);
                backLeft.setTargetPosition((int)   -ticks_for_dist);
                backRight.setTargetPosition((int)   ticks_for_dist);
                break;
        }

          opMode.telemetry.addData("Past switch", "True");


        if (frontLeft.getTargetPosition() < 0){
            while(frontLeft.getCurrentPosition() > frontLeft.getTargetPosition()) {
                // Run your motors
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
                opMode.telemetry.addData("Encoder", frontLeft.getCurrentPosition())
                        .addData("Target", frontLeft.getTargetPosition());
                opMode.telemetry.update();
            }
        }else if (frontLeft.getTargetPosition() > 0){
            while(frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()){
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
                opMode.telemetry.addData("Encoder", frontLeft.getCurrentPosition())
                        .addData("Target", frontLeft.getTargetPosition());
                opMode.telemetry.update();
            }
        }

        setToStill();
    }

    public void move_left(int distance, double power){
        double ticks_for_dist = ticks_per_inch * distance;
        frontLeft.setTargetPosition((int) -ticks_for_dist);
        frontRight.setTargetPosition((int) ticks_for_dist);
        backLeft.setTargetPosition((int)   ticks_for_dist);
        backRight.setTargetPosition((int) -ticks_for_dist);
        while(frontLeft.getCurrentPosition() >= frontLeft.getTargetPosition() + 100 && frontLeft.getCurrentPosition() <= frontLeft.getTargetPosition() - 100) {
            // Run your motors
            frontLeft.setPower(power * (frontLeft.getTargetPosition() / Math.abs(frontLeft.getTargetPosition())));
            frontRight.setPower(power * (frontRight.getTargetPosition() / Math.abs(frontRight.getTargetPosition())));
            backLeft.setPower(power * (backLeft.getTargetPosition() / Math.abs(backLeft.getTargetPosition())));
            backRight.setPower(power * (backRight.getTargetPosition() / Math.abs(backRight.getTargetPosition())));
        }
        setToStill();
    }

    /**
     * A simple rotate that uses time
     * @param power Speed that the robot rotates
     * @param dir Direction that the robot rotates (left/right)
     * @param time How long the robot rotates
     */
    public void simple_rotate(double power, direction dir, int time){
        switch(dir){
            case LEFT:
                frontLeft.setPower(-(power));
                frontRight.setPower((power));
                backLeft.setPower(-power);
                backRight.setPower(power);
                break;
            case RIGHT:
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backLeft.setPower((power));
                backRight.setPower(-(power));
                break;
        }
        waiter(time);
        setToStill();
    }

    /**
     * Moves the carousel
     * @param dir The direction the motor goes (left/right)
     * @param power The speed that the motor goes
     * @param time The amount of time that the motor goes
     */
    public void carousel(direction dir, double power, int time){
        switch(dir) {
            case CWISE:
                spin.setPower(power);
                break;
            case CCWISE:
                spin.setPower(-power);
                break;
        }
        waiter(time);
        spin.setPower(0.0);

    }

    /**
     * Starts intake
     * @param dir The direction the intake goes (in/out)
     */
    public void start_intake(direction dir){
        switch(dir){
            case IN:
                scoop.setPower(-0.7);
                break;
            case OUT:
                scoop.setPower(0.7);
                break;
        }

    }

    /**
     * Stops the intake
     */
    public void stop_intake(){
        scoop.setPower(0.0);
    }
    public void degree_rotate(double degrees, double power, direction dir){
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waiter(500);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        degrees = (degrees * ticks_per_revolution)/45;
        switch(dir){
            case RIGHT:
                frontLeft.setTargetPosition((int)(degrees));
                frontRight.setTargetPosition((int)-(degrees));
                backLeft.setTargetPosition(((int)(degrees)));
                backRight.setTargetPosition((int)-(degrees));
                break;
            case LEFT:
                frontLeft.setTargetPosition((int)-(degrees));
                frontRight.setTargetPosition((int)(degrees));
                backLeft.setTargetPosition(((int)-(degrees)));
                backRight.setTargetPosition((int)(degrees));
        }

        if (frontLeft.getTargetPosition() < 0){
            while(frontLeft.getCurrentPosition() > frontLeft.getTargetPosition()) {
                // Run your motors
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
                opMode.telemetry.addData("Encoder", frontLeft.getCurrentPosition())
                        .addData("Target", frontLeft.getTargetPosition());
                opMode.telemetry.update();
            }
        }else if (frontLeft.getTargetPosition() > 0){
            while(frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()){
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
                opMode.telemetry.addData("Encoder", frontLeft.getCurrentPosition())
                        .addData("Target", frontLeft.getTargetPosition());
                opMode.telemetry.update();
            }
        }
        setToStill();
    }
    public void crane_lift(direction dir, double power){
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waiter(500);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        switch(dir){
            case UP:
                crane_lift.setTargetPosition(6576);
                break;
            case DOWN:
                crane_lift.setTargetPosition(-6576);
                break;
        }

        if (crane_lift.getTargetPosition() > 0){
            while (crane_lift.getCurrentPosition() < crane_lift.getTargetPosition()){
                crane_lift.setPower(power);
                opMode.telemetry.addData("Encoder", crane_lift.getCurrentPosition())
                        .addData("Target", crane_lift.getTargetPosition());
            }
        } else if (crane_lift.getTargetPosition() < 0){
            while (crane_lift.getCurrentPosition() > crane_lift.getTargetPosition()){
                crane_lift.setPower(-(power));
                opMode.telemetry.addData("Encoder", crane_lift.getCurrentPosition())
                        .addData("Target", crane_lift.getTargetPosition());
            }
        }
        crane_lift.setPower(0.0);
    }


    public enum direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT,
        CWISE,
        CCWISE,
        IN,
        OUT,
        UP,
        DOWN
    }

}
