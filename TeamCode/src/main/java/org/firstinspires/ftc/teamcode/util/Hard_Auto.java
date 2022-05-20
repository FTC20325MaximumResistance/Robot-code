package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Hard_Auto extends Hardware{

    @Override
    public void initAuto(){
        super.initAuto();

    }
    LinearOpMode l;

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
                frontLeft.setTargetPosition((int) -ticks_for_dist*2);
                frontRight.setTargetPosition((int) ticks_for_dist*2);
                backLeft.setTargetPosition((int)   ticks_for_dist*2);
                backRight.setTargetPosition((int) -ticks_for_dist*2);
                break;
            case RIGHT:
                frontLeft.setTargetPosition((int)   ticks_for_dist*2);
                frontRight.setTargetPosition((int) -ticks_for_dist*2);
                backLeft.setTargetPosition((int)   -ticks_for_dist*2);
                backRight.setTargetPosition((int)   ticks_for_dist*2);
                break;
        }


        if (frontLeft.getTargetPosition() < 0){
            while(frontLeft.getCurrentPosition() > frontLeft.getTargetPosition()) {
                // Run your motors
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
            }
        }else if (frontLeft.getTargetPosition() > 0){
            while(frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()){
                frontLeft.setPower(power*(frontLeft.getTargetPosition()/Math.abs(frontLeft.getTargetPosition())));
                frontRight.setPower(power*(frontRight.getTargetPosition()/Math.abs(frontRight.getTargetPosition())));
                backLeft.setPower(power*(backLeft.getTargetPosition()/Math.abs(backLeft.getTargetPosition())));
                backRight.setPower(power*(backRight.getTargetPosition()/Math.abs(backRight.getTargetPosition())));
            }
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
            case OUT:
                scoop.setPower(-0.7);
                break;
            case IN:
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
        waiter(50);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        degrees = (degrees * ticks_per_revolution)/49.75;
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

        switch(dir){
            case TOP:
                crane_lift.setTargetPosition(-(8500));
                break;
            case MIDDLE:
                crane_lift.setTargetPosition(-(7800));
                break;
            case BOTTOM:
                crane_lift.setTargetPosition(-(5300));
                break;
            case DOWN:
                crane_lift.setTargetPosition(0);
                break;
        }

        if (crane_lift.getTargetPosition() > crane_lift.getCurrentPosition()){
            while (crane_lift.getCurrentPosition() < crane_lift.getTargetPosition()){
                crane_lift.setPower(power);
                opMode.telemetry.addData("Encoder", crane_lift.getCurrentPosition())
                        .addData("Target", crane_lift.getTargetPosition());
            }
        } else if (crane_lift.getTargetPosition() < crane_lift.getCurrentPosition()){
            while (crane_lift.getCurrentPosition() > crane_lift.getTargetPosition()){
                crane_lift.setPower(-(power));
                opMode.telemetry.addData("Encoder", crane_lift.getCurrentPosition())
                        .addData("Target", crane_lift.getTargetPosition());
            }
        }
        crane_lift.setPower(0.0);
    }
    public void scoop(direction dir){
        switch (dir){
            case FORWARD:
                cranescoop.setPosition(0.0);
                break;
            case BACKWARD:
                cranescoop.setPosition(1.0);
                break;
        }
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
        DOWN,
        TOP,
        MIDDLE,
        BOTTOM
    }

    String whereDuck;
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            //"Marker"
    };

    private static final String VUFORIA_KEY =
            "AbPlqTr/////AAABmdW9GwFZ8E3Kt/btYVgy+hBMLd/G/emPF/6jLYzmo9hMgEldkQ4YzCzrs7Rqz5Pnh7RwdT+VFIPS8czES3UnsLv33hPsr/DhZ73T7r+0MJPx43fx4PP5ncgoqEToOaK04iZr6kDjQLZMSxaGqz2jx4WrQpO7F6su9GQ4H/qr3ZydWXVgv1YYEZny93GubLpD59a33+NPedV+HgLkSOH7/ksG3hRelkD3f1yjTGNjAK8C9wd30njnjN7QmLmCUghe/5Wj8coc3eCHB5InNR3VJk7NNd2r+MnxYfB8N1vznX0OHYA7NydPvlwnrZ8Zi7nXC38oiEWRABDN4sFEwzc58TloW4uTA9SLA5EJSxjZC1kJ";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    boolean isDuckDetected;

    public String findDuck() {
        try {

            int oh_no = 0;
            opMode.telemetry.addData("Vision Software:", "Started");
            opMode.telemetry.update();
            while (!(isDuckDetected)) {
                if (tfod != null) {
                    oh_no += 1;
                /*
                if (oh_no > 18000 && !(isDuckDetected)){
                    isDuckDetected = true;
                    return "right";
                }*/

                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                    /*
                    if (oh_no > 18000 && !(isDuckDetected)){
                        isDuckDetected = true;
                        return "right";
                    }
                    */

                        int i = 0;

                        for (Recognition recognition : updatedRecognitions) {
                            opMode.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            opMode.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            opMode.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;
                        /*
                        if (oh_no > 18000 && !(isDuckDetected)){
                            isDuckDetected = true;
                            return "right";
                        }
                        */
                            if (recognition.getLabel().equals("Duck")) {
                                //oh_no = 0;
                                isDuckDetected = true;
                                opMode.telemetry.addData("Object Detected", "Duck");
                                if (recognition.getRight() < 640) {
                                    whereDuck = "left";

                                } else if (recognition.getLeft() < 1280) {
                                    whereDuck = "center";
                                }
                            } else {
                                isDuckDetected = true;
                                whereDuck = "right";
                            }
                            if (!isDuckDetected)
                                isDuckDetected = true;
                            whereDuck = "right";
                        }
                        if (!isDuckDetected) {
                            isDuckDetected = true;
                            whereDuck = "right";
                        }
                        opMode.telemetry.update();
                    }
                }

            }

            isDuckDetected = false;
            if (tfod != null)
                tfod.shutdown();

            return whereDuck;
        }
        catch(Exception e){
            opMode.telemetry.addLine("No camera found");
            opMode.telemetry.update();
            return "right";
        }

    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        try {

            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        }
        catch(Exception e){
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Fuck You!");
            opMode.telemetry.update();
        }
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        try {
            int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minResultConfidence = 0.4f;
            tfodParameters.isModelTensorFlow2 = true;
            tfodParameters.inputSize = 320;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        }
        catch(Exception e){

        }
    }
    public void end_vision(){
        try {
            tfod.shutdown();
            vuforia.close();
        }
        catch(Exception e){
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Fuck You!");
            opMode.telemetry.update();
        }
    }
    public void ready_vision(){
        try {
            initVuforia();
            initTfod();

            int oh_no = 0;
            if (tfod != null) {
                tfod.activate();
                tfod.setZoom(1.0, 16.0 / 9.0);

            }
            opMode.telemetry.addLine("Ready To Start");
            opMode.telemetry.update();
        }
        catch(Exception e){
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Fuck You!");
            opMode.telemetry.update();
        }
    }

}
