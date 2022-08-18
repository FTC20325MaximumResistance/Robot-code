package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@SuppressWarnings(value = "unused")
public class Hardware {

    BNO055IMU imu;

    OpMode opMode;

    Orientation angles;

    public DcMotor frontLeft, frontRight, backLeft, backRight, spin, scoop, crane_lift;
    public DcMotor[] drive;
    public Servo cranescoop;
    Servo[] servo;

    double wheel_diameter = 2.95276;
    int ticks_per_revolution = 560;
    double ticks_per_inch = ticks_per_revolution / (9.42);

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

        //Put reversed motors here

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        spin = opMode.hardwareMap.dcMotor.get("spin");

        scoop = opMode.hardwareMap.dcMotor.get("scoop");

        crane_lift = opMode.hardwareMap.dcMotor.get("crane_lift");
        crane_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        crane_lift.setDirection(DcMotorSimple.Direction.REVERSE);
        cranescoop = opMode.hardwareMap.servo.get("cranescoop");
        servo = new Servo[]{cranescoop};

//        capper = opMode.hardwareMap.servo.get("capper");

        for(DcMotor motor : drive)
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void initAuto(){
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        for(Servo servo: servo) servo.setPosition(0);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "imu";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        waiter(500);
    }

    public void setDriverMotorMode(DcMotor.RunMode mode){
        for(DcMotor dcMotor: drive) dcMotor.setMode(mode);
    }

    /**
     * This is meant to make the robot wait x amount of time
     * @param time int length of wait time in milliseconds
     */
    public void waiter(int time){
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while(true) if(!(Time.milliseconds() < time)) break;
    }


    /**
     * Set all drive motors to zero
     */
    public void setToStill(){
        for(DcMotor dcMotor : drive){
            dcMotor.setPower(0);
        }
    }

}
