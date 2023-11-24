package org.firstinspires.ftc.teamcode.subsystem.pidfController;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class PIDFLift extends SubsystemBase {
    //set the PID controller to one lift and mirror hte result to the other
    private PIDController controller;

    //The 'f' from the video = 'Kcos' from CTRL ALT FTC documentation
    private double p = 0, i = 0, d = 0, f = 0;

    private int target = 0;

    private DcMotorEx lift1, lift2;

    public PIDFLift(HardwareMap hardwareMap, int tolerance) {
        controller.setPID(p, i, d);
        controller.setTolerance(tolerance);

        lift1 = hardwareMap.get(DcMotorEx.class, "lift1");
        lift2 = hardwareMap.get(DcMotorEx.class, "lift2");
    }

    public void move(int posChange) {
        target += posChange;

        setPosition(target);
    }

    public void setPosition(int t) {
        target = t;

        int liftPos = lift1.getCurrentPosition();
        double pid = controller.calculate(liftPos, target);

        double power = pid + f;
        lift1.setPower(power);
        lift2.setPower(power);
    }

    //TODO temperay lift moving code
    public void tmpMove(int t){
        target += t;
        tmpSetPost(target);
    }

    public void tmpAutonPos(int t){
        while(lift1.isBusy()){
            tmpSetPost(t);
        }
    }

    public void tmpSetPost(int t){
        lift1.setTargetPosition(t);
        lift1.setPower(0.25);
        lift2.setTargetPosition(t);
        lift2.setPower(0.25);
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

    public int lift1Pos(){
        return lift1.getCurrentPosition();
    }

    public int lift2Pos(){
        return lift2.getCurrentPosition();
    }
}
