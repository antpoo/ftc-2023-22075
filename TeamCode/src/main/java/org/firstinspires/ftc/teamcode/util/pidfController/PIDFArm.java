package org.firstinspires.ftc.teamcode.util.pidfController;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
//TODO can use the MotorEX from FTCLib instead of the DcMotorEx from SDK
public class PIDFArm {
    //The 'f' from the video = 'Kcos' from CTRL ALT FTC documentation
    private PIDController controller;

    private double p = 0, i = 0, d = 0, f = 0;

    private double ticks_in_degree; //Total number of ticks in a degree

    private DcMotorEx arm;

    public PIDFArm(HardwareMap hardwareMap, int tolerance) {
        controller.setPID(p, i, d);
        controller.setTolerance(tolerance);

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        ticks_in_degree = arm.getMotorType().getTicksPerRev() / 180.0;
    }

    public void move(int target){
        controller.setSetPoint(target);
        while(!controller.atSetPoint()){
            int armPos  = arm.getCurrentPosition();
            double pid = controller.calculate(armPos);
            double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

            double power = pid + ff;
            arm.setPower(power);
        }
        //TODO do I need to stop the motor after it reaches position
    }

    public void tune(int target, double  p, double i, double d, double f){
        controller.setPID(p, i, d);
        int armPos  = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;
        arm.setPower(power);
    }

    public int armPos(){
        return arm.getCurrentPosition();
    }
}
