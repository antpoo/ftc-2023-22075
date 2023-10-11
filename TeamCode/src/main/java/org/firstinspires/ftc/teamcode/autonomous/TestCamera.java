package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Camera;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class TestCamera extends LinearOpMode {

    @Override
    public void runOpMode() {
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


        Camera cv = new Camera(this);

        waitForStart();

        while (!opModeIsActive() && opModeIsActive()) {
            List<AprilTagDetection> arr = cv.getAprilTags(hardwareMap);
            if (!arr.isEmpty()) {
                AprilTagDetection tag = arr.get(0);

                telemetry.addData("x", tag.ftcPose.x);
                telemetry.addData("y", tag.ftcPose.y);
                telemetry.addData("z", tag.ftcPose.z);
                telemetry.addData("roll", tag.ftcPose.roll);
                telemetry.addData("pitch", tag.ftcPose.pitch);
                telemetry.addData("yaw", tag.ftcPose.yaw);
            }
        }
    }
}