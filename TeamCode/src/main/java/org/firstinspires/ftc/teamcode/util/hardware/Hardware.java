package org.firstinspires.ftc.teamcode.util.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {

    OpMode opMode;

    public DcMotor frontLeft, frontRight, backLeft, backRight, spin, scoop;
    public DcMotor[] drive;

    Servo[] servo;

    public void initRobot(OpMode opMode){
        this.opMode = opMode;
        initHardware();
    }

    private void initHardware(){
        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");

        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");
        drive = new DcMotor[]{frontLeft, frontRight, backLeft, backRight};


        spin = opMode.hardwareMap.dcMotor.get("spin");
        spin.setDirection(DcMotorSimple.Direction.REVERSE);
        scoop = opMode.hardwareMap.dcMotor.get("scoop");
        for(DcMotor motor : drive){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }




    }

    public void initAuto(){
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        for(Servo servo: servo) servo.setPosition(0);
        waiter(500);
    }

    public void setDriverMotorMode(DcMotor.RunMode mode){
        for(DcMotor dcMotor: drive) dcMotor.setMode(mode);
    }

    public void waiter(int time){
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while(true) if(!(Time.milliseconds() < time)) break;
    }
}
