package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class TestCamera extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
////        initialize camera and pipeline
//        Camera cv = new Camera(this);
////      call the function to startStreaming
//        cv.observeStick("RED");
//
//        waitForStart();
//        while (opModeIsActive()) {
//            telemetry.addData("Item: ", cv.getItemStatus());
//            telemetry.addData("Debug", cv.colorDetectionPipeline.max);
//            telemetry.addData("Avg1", cv.colorDetectionPipeline.getAvg1());
//            telemetry.addData("Avg2", cv.colorDetectionPipeline.getAvg2());
//            telemetry.addData("Avg3", cv.colorDetectionPipeline.getAvg3());
//            telemetry.update();
//            sleep(100);
//        }
////        stopStreaming
//        cv.stopCamera();

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .build();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {



            for (AprilTagDetection tag : tagProcessor.getDetections())  {



//
                telemetry.addData("id", tag.id);
                telemetry.addData("x", tag.ftcPose.x);
                telemetry.addData("y", tag.ftcPose.y);
                telemetry.addData("z", tag.ftcPose.z);
                telemetry.addData("roll", tag.ftcPose.roll);
                telemetry.addData("pitch", tag.ftcPose.pitch);
                telemetry.addData("yaw", tag.ftcPose.yaw);


//                telemetry.update();

            }

            telemetry.update();

        }
    }
}