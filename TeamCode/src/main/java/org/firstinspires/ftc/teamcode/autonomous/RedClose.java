package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.util.Camera;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous
public class RedClose extends LinearOpMode {

    Camera camera = new Camera(this);
    @Override
    public void runOpMode() {

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
        while (!isStopRequested() && opModeIsActive()) {

            if (!reached) {

            }
            else {
                // once you are at the correct location
                AprilTagDetection tag = null;
                for (AprilTagDetection atag : tagProcessor.getDetections())  {
                    if (atag.id % 3 == target % 3) {
                        tag = atag;
                    }
                }
                if (tag != null) {
                    double x = tag.ftcPose.x, y = tag.ftcPose.y, z = tag.ftcPose.z;


                }
            }

        }
    }


}
