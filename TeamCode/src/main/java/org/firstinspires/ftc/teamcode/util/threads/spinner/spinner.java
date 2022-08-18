package org.firstinspires.ftc.teamcode.util.threads.spinner;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class spinner extends Thread{
    OpMode opMode;
    DcMotor spinner;
    Hardware r = new Hardware();
    public spinner(OpMode opMode, DcMotor spinner){
        this.opMode = opMode;
        this.spinner = spinner;
    }
    @Override
    public void run(){
        super.run();
        if (spinner.getPower() == 0){
            spinner.setPower(0.9);
        }else{
            spinner.setPower(0.0);
        }
    }

}
