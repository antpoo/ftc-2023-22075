package org.firstinspires.ftc.teamcode.subsystem.pidfController;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PIDFArm extends SubsystemBase {
    private PIDController controller;

    //The 'f' from the video = 'Kcos' from CTRL ALT FTC documentation
    private double p = 0, i = 0, d = 0, f = 0;

    private double ticks_in_degree; //Total number of ticks in a degree

    private int target = 0; //target position

    private DcMotorEx arm;

    public PIDFArm(HardwareMap hardwareMap, int tolerance) {
        controller.setPID(p, i, d);
        controller.setTolerance(tolerance);

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        ticks_in_degree = arm.getMotorType().getTicksPerRev() / 180.0;
    }

    public void move(int posChange){
        target += posChange;

        setPosition(target);
    }

    public void autonPos(int t){
        while(!controller.atSetPoint()){
            setPosition(t);
        }
    }

    public void setPosition(int t){
        target = t;

        int armPos  = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;
        arm.setPower(power);
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
