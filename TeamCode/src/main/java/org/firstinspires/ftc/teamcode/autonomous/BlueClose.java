package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.util.OpenCVMaster;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous
public class BlueClose extends LinearOpMode {

    final int LEFT = 1, CENTER = 2, RIGHT = 3;

    final boolean RED = false, BLUE = true;

    private final String FL = "frontLeftMotor", FR = "frontRightMotor";

    private final String BL = "backLeftMotor", BR = "backRightMotor";

    final double DIAMETER = 3.5;
    final double TICKS_COUNT = 537.7;
    final double COUNTS_PER_INCH = TICKS_COUNT / (DIAMETER * Math.PI);

    DcMotor fl = hardwareMap.get(DcMotor.class, FL);
    DcMotor fr = hardwareMap.get(DcMotor.class, FR);
    DcMotor bl = hardwareMap.get(DcMotor.class, BL);
    DcMotor br = hardwareMap.get(DcMotor.class, BR);
    @Override
    public void runOpMode() {




        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        OpenCVMaster cv = new OpenCVMaster(this);
        cv.observeStick(BLUE);
        waitForStart();
        int target = CENTER; // change based on team prop

        int cnt = 0;

        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("Side: ", cv.opencv.whichSide);
            telemetry.addData("Debug", cv.opencv.max);
            telemetry.addData("Avg1", cv.opencv.avg1);
            telemetry.addData("Avg2", cv.opencv.avg2);
            telemetry.addData("Avg3", cv.opencv.avg3);
            telemetry.addData("Count", cnt);
            cnt++;
            if (cnt >= 60) {
                if (cv.opencv.whichSide.equals("left")) target = LEFT;
                else if (cv.opencv.whichSide.equals("center")) target = CENTER;
                else if (cv.opencv.whichSide.equals("right")) target = RIGHT;
                break;
            }
            telemetry.update();
            sleep(100);
        }


        cv.stopCamera();

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        if (target == LEFT) {

        }
        else if (target == RIGHT) {

        }
        else {

        }

        // MOVE AND PLACE THE GROUND PIXEL IN THE CORRECT SPOT

//        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
//                .setDrawAxes(true)
//                .setDrawCubeProjection(true)
//                .setDrawTagID(true)
//                .setDrawTagOutline(true)
//                .build();
//
//        VisionPortal visionPortal = new VisionPortal.Builder()
//                .addProcessor(tagProcessor)
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
//                .setCameraResolution(new Size(640, 480))
//                .build();
//
//        // MOVE TOWARDS THE BACKDROP HERE
//
//        while (!isStopRequested() && opModeIsActive()) {
//
//
//
//            // once you are at the correct location
//            AprilTagDetection tag = null;
//            for (AprilTagDetection atag : tagProcessor.getDetections())  {
//                if (atag.id % 3 == target % 3) {
//                    tag = atag;
//                }
//            }
//            if (tag != null && tag.ftcPose != null) {
//                double x = tag.ftcPose.x, y = tag.ftcPose.y, z = tag.ftcPose.z;
//                telemetry.addData("id", tag.id);
//                telemetry.addData("x", x);
//                telemetry.addData("y", y);
//                telemetry.addData("z", z);
//                telemetry.update();
//            }
//
//            // move accordingly
//
//
//        }
    }

    void driveToPos(double left, double right, double speed) {
        right *= -1;
        int leftPos = (int) (fl.getCurrentPosition() + left * COUNTS_PER_INCH);
        int rightPos = (int) (fr.getCurrentPosition() + right * COUNTS_PER_INCH);

        fl.setTargetPosition(leftPos);
        fr.setTargetPosition(rightPos);
        bl.setTargetPosition(leftPos);
        br.setTargetPosition(rightPos);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);

        while (opModeIsActive() && fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy()) {
            idle();
        }

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(400);

    }



}
