package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;

import static java.lang.Math.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.autonomous.roadrunner.essentials.drive.SampleMecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous
public class RedFar extends LinearOpMode {

    @Override
    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .forward(28)
                .build();

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
        boolean reached = false;
        int target = 1; // change based on team prop


        // scan team prop

        // once the pixel has been dropped
        if (target == 0) drive.turn(Math.toRadians(180));
        else if (target == 1) drive.turn(Math.toRadians(-90));


        while (true) {
            // once you are at the correct location
            AprilTagDetection tag = null;
            for (AprilTagDetection atag : tagProcessor.getDetections()) {
                if (atag.id % 3 == target % 3) {
                    tag = atag;
                }
            }
            if (tag != null) {
                double x = tag.ftcPose.x, y = tag.ftcPose.y, z = tag.ftcPose.z;
                if (x > 0.2) {
                    traj = drive.trajectoryBuilder(new Pose2d())
                            .strafeLeft(0.1)
                            .build();
                    drive.followTrajectory(traj);
                }
                else if (-x > 0.2) {
                    traj = drive.trajectoryBuilder(new Pose2d())
                            .strafeRight(0.1)
                            .build();
                    drive.followTrajectory(traj);
                }
                else break;
            }
        }


    }


}
