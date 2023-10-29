package org.firstinspires.ftc.teamcode.subsystem.pidfController;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class PIDFTuner extends OpMode {
    public static double p = 0, i= 0, d= 0, f= 0;

    public static int target = 0;

    private PIDFArm pidfArm;
    @Override
    public void init() {
        pidfArm = new PIDFArm(hardwareMap, 0);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        pidfArm.tune(target, p, i, d, f);

        telemetry.addData("pos ", pidfArm.armPos());
        telemetry.addData("target ", target);
        telemetry.update();
    }
}
