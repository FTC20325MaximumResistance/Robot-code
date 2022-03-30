package org.firstinspires.ftc.teamcode.util.threads.tensorflow;

import android.annotation.SuppressLint;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Vision extends Thread{

    OpMode opMode;
    LinearOpMode l;

    int location = 2;

    public Vision(OpMode opMode){
        this.opMode=opMode;
        this.start();


    }

    @Override
    public synchronized void start() {
        super.start();
        // Initalize The things
        initVuforia();
        initTfod();
    }
    @Override
    public void interrupt() {
        super.interrupt();
        // This is where you close Tensorflow and your camera
        tfod.shutdown();
        vuforia.close();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        super.run();
        // Constantly update where the duck is
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.


        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);

        }
        Log.d("Vision", "Started");
        while (!(l.isStarted())) {
            if (tfod != null) {
                opMode.telemetry.update();
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                    // step through the list of recognitions and display boundary info.
                    int i = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        opMode.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        opMode.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        opMode.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                        i++;
                        Log.d("Vision", "run() -->> while -> Object detected: " + recognition.getLabel());
                        // check label to see if the camera now sees a Duck
                        if (recognition.getLabel().equals("Duck")) {
                            isDuckDetected = true;
                            opMode.telemetry.addData("Object Detected", "Duck");
                            if (recognition.getRight() < 640) location=0; else if (recognition.getLeft() < 1280) location=1;
                        } else {
                            isDuckDetected = false;
                            location=2;
                        }

                    }
                    opMode.telemetry.update();
                }
            }

        }
        Log.d("Vision", "run() -> loation of duck is: "+ location);
        isDuckDetected = false;
        vuforia.close();
        if (tfod != null)
            tfod.shutdown();
    }



    public int getLocation(){
        return location;
    }

    /*
     * ADD VUFORIA AFTER THIS! YOU BIG DUMB
     */

    boolean isDuckDetected;
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

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        Log.d("Vision", "initVuforia() -> starting");
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        Log.d("Vision", "initVuforia() -> started!");
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        Log.d("Vision", "initTfod() -> starting");
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.4f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        Log.d("Vision", "initTfod() -> started!");
    }
}
