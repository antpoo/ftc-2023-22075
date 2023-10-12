package org.firstinspires.ftc.teamcode.util;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

public class Camera {
    private OpenCvWebcam webcam;


    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    private final double FX = 578.272;
    private final double FY = 578.272;
    private final double CX = 402.145;
    private final double CY = 221.506;

    // UNITS ARE METERS
    private final double TAGSIZE = 0.166;

    private int itemStatus;
    public ColorDetectionPipeline colorDetectionPipeline;
    public AprilTagDetectionPipeline aprilTagDetectionPipeline;
    private LinearOpMode op;
    public Camera(LinearOpMode p_op){
        //you can input  a hardwareMap instead of linearOpMode if you want
        op = p_op;
        //initialize webcam
        webcam = OpenCvCameraFactory.getInstance().createWebcam(op.hardwareMap.get(WebcamName.class, "Webcam 1"));
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(TAGSIZE, FX, FY, CX, CY);

    }
    public int getItemStatus() {
        return itemStatus;
    }
    public void observeStick(String color){
        //create the pipeline
        colorDetectionPipeline = new ColorDetectionPipeline(color);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.setPipeline(colorDetectionPipeline);
                //start streaming the camera
                webcam.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);

                while (!colorDetectionPipeline.hasProcessedFrame) op.sleep(50);

                itemStatus = colorDetectionPipeline.getSide();

                //if you are using dashboard, update dashboard camera view
                /*FtcDashboard.getInstance().startCameraStream(webcam, 5);*/

            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

//    public List<AprilTagDetection> getAprilTags(HardwareMap hardwareMap) {
//
//
//
//        return tagProcessor.getDetections();
//
//    }

    //stop streaming
    public void stopCamera(){
        webcam.stopStreaming();
    }
}