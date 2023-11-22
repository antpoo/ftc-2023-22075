package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.autonomous.roadrunner.essentials.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.pidfController.PIDFArm;
import org.firstinspires.ftc.teamcode.util.OpenCVMaster;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous
public class RedClose extends LinearOpMode {

    final int LEFT = 1, CENTER = 2, RIGHT = 3;
    final boolean RED = false, BLUE = true;

    @Override
    public void runOpMode() {


        OpenCVMaster cv = new OpenCVMaster(this);
        cv.observeStick(RED);

        //PIDFArm arm = new PIDFArm(hardwareMap, 10);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();
        int target = LEFT; // change based on team prop

        int cnt = 0;

        // may have to do some twisting and turning to find the team prop

        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("Side: ", cv.opencv.whichSide);
            telemetry.addData("Debug", cv.opencv.max);
            telemetry.addData("Avg1", cv.opencv.avg1);
            telemetry.addData("Avg2", cv.opencv.avg2);
            telemetry.addData("Avg3", cv.opencv.avg3);
            telemetry.addData("Count", cnt);
            cnt++;
            if (cnt >= 30) {
                if (cv.opencv.whichSide.equals("left")) target = LEFT;
                else if (cv.opencv.whichSide.equals("center")) target = CENTER;
                else if (cv.opencv.whichSide.equals("right")) target = RIGHT;
                break;
            }
            telemetry.update();
            sleep(100);
        }


        cv.stopCamera();

        // MOVE AND PLACE THE GROUND PIXEL IN THE CORRECT SPOT


        Trajectory purpleTraj;

        if (target == LEFT) {
            purpleTraj = drive.trajectoryBuilder(new Pose2d())
                    .splineTo(new Vector2d(0.0, 19.0), Math.toRadians(-28)) // FIX THESE NUMBERS IF NEEDED
                    .addDisplacementMarker(() -> {
                        //arm.setPosition(some number); MAKE THE ARM RELEASE THE PURPLE PIXEL HERE
                    })
                    .build();
        }
        else if (target == CENTER) {
            purpleTraj = drive.trajectoryBuilder(new Pose2d())
                    .splineTo(new Vector2d(0.0, 20.38), 0)
                    .addDisplacementMarker(() -> {
                        //arm.setPosition(some number); MAKE THE ARM RELEASE THE PURPLE PIXEL HERE
                    })
                    .build();
        }
        else {
            purpleTraj = drive.trajectoryBuilder(new Pose2d())
                    .splineTo(new Vector2d(0.0, 19.0), Math.toRadians(28)) // FIX THESE NUMBERS IF NEEDED
                    .addDisplacementMarker(() -> {
                        //arm.setPosition(some number); MAKE THE ARM RELEASE THE PURPLE PIXEL HERE
                    })
                    .build();
        }

        drive.followTrajectory(purpleTraj);

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

        // MOVE TOWARDS THE BACKDROP HERE

        while (!isStopRequested() && opModeIsActive()) {



            // once you are at the correct location
            AprilTagDetection tag = null;
            for (AprilTagDetection atag : tagProcessor.getDetections())  {
                if (atag.id % 3 == target % 3) {
                    tag = atag;
                }
            }
            if (tag != null && tag.ftcPose != null) {
                double x = tag.ftcPose.x, y = tag.ftcPose.y, z = tag.ftcPose.z;
                telemetry.addData("id", tag.id);
                telemetry.addData("x", x);
                telemetry.addData("y", y);
                telemetry.addData("z", z);
                telemetry.update();
            }

            // move accordingly


        }
    }


}
