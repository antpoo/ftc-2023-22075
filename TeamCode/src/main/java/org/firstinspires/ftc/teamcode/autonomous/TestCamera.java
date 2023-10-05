package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Camera;

@Autonomous
public class TestCamera extends LinearOpMode {

    @Override
    public void runOpMode() {
//        initialize camera and pipeline
        Camera cv = new Camera(this);
//      call the function to startStreaming
        cv.observeStick("RED");

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Item: ", cv.getItemStatus());
            telemetry.addData("Debug", cv.colorDetectionPipeline.max);
            telemetry.addData("Avg1", cv.colorDetectionPipeline.getAvg1());
            telemetry.addData("Avg2", cv.colorDetectionPipeline.getAvg2());
            telemetry.addData("Avg3", cv.colorDetectionPipeline.getAvg3());
            telemetry.update();
            sleep(100);
        }
//        stopStreaming
        cv.stopCamera();
    }
}