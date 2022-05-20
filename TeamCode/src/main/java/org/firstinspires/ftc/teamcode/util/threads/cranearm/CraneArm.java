package org.firstinspires.ftc.teamcode.util.threads.cranearm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class CraneArm extends Thread{

    OpMode opMode;
    DcMotor arm;
    Hardware r = new Hardware();
    int dir;

    public CraneArm(OpMode opMode, DcMotor arm){
        this.opMode=opMode;
        this.arm=arm;
    }
    public void get_dir(int die){
        dir = die;
    }



    @Override
    public void run() {
        super.run();
        // This is what will run when you run the thread
        if(dir == 0){
            // Move this direction
            arm.setTargetPosition(0);
        }else if(dir == 1){
            arm.setTargetPosition(-(5300));
            // Move another way
        }else if (dir == 2) {
            arm.setTargetPosition(-(7800));
        }else if (dir == 3){
            arm.setTargetPosition(-(8500));
        }
        if (arm.getTargetPosition() > arm.getCurrentPosition()){
            while (arm.getCurrentPosition() < arm.getTargetPosition()){
                arm.setPower(0.9);
                opMode.telemetry.addData("Encoder", arm.getCurrentPosition())
                        .addData("Target", arm.getTargetPosition());
            }
        } else if (arm.getTargetPosition() < arm.getCurrentPosition()){
            while (arm.getCurrentPosition() > arm.getTargetPosition()){
                arm.setPower(-(0.9));
                opMode.telemetry.addData("Encoder", arm.getCurrentPosition())
                        .addData("Target", arm.getTargetPosition());
            }
        }
        opMode.telemetry.addLine("Done");
        opMode.telemetry.update();
        arm.setPower(0);

    }

    public void move(int dir){
        this.dir=dir;
        this.start();
    }
}
