package org.firstinspires.ftc.teamcode.util.pidfController;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

//to do can use the MotorEX from FTCLib instead of the DcMotorEx from SDK

//TODO do I need to create two separate PID controllers one for each linear slide?

public class PIDFLift {
    //The 'f' from the video = 'Kcos' from CTRL ALT FTC documentation
    private PIDController controller;

    private double p = 0, i = 0, d = 0, f = 0;

    private DcMotorEx lift1, lift2;

    public PIDFLift(HardwareMap hardwareMap, int tolerance) {
        controller.setPID(p, i, d);
        controller.setTolerance(tolerance);

        lift1 = hardwareMap.get(DcMotorEx.class, "lift1");
        lift2 = hardwareMap.get(DcMotorEx.class, "lift2");
    }

    public void move(int target) {
        controller.setSetPoint(target);
        while (!controller.atSetPoint()) {
            int liftPos = lift1.getCurrentPosition();
            double pid = controller.calculate(liftPos);
            double ff = pid * f;

            double power = pid + ff;
            lift1.setPower(power);
            lift2.setPower(power);
        }
        //TODO do I need to stop the motor after it reaches position
    }

    public void tune(int target, double p, double i, double d, double f) {
        controller.setPID(p, i, d);
        int armPos = lift1.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = pid * f;

        double power = pid + ff;
        lift1.setPower(power);
        lift2.setPower(power);
    }
}
