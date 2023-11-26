package org.firstinspires.ftc.teamcode.subsystem.pidfController;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.io.PipedOutputStream;

public class PIDFArm extends SubsystemBase {
    private PIDController controller;

    //The 'f' from the video = 'Kcos' from CTRL ALT FTC documentation
    private double p = 0, i = 0, d = 0, f = 0;

    private double ticks_in_degree; //Total number of ticks in a degree

    private int target = 0; //target position

    private DcMotor arm;
    private DcMotor arm2;

    public PIDFArm(HardwareMap hardwareMap, int tolerance) {
//        controller = new PIDController(p, i, d);
//        controller.setTolerance(tolerance);

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        ticks_in_degree = arm.getMotorType().getTicksPerRev() / 180.0;

        arm2 = hardwareMap.get(DcMotorEx.class, "arm2");
        arm2.setDirection(DcMotorSimple.Direction.FORWARD);

        //TODO these can be remove once PIDF is setup
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        arm2.setPower(power);
    }

    //TODO temporary arm movement code
    public void tmpMove(double pos){
        if(pos > 0){
            target += 1;
            arm.setTargetPosition(target);
            arm2.setTargetPosition(target);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(0.15);
            arm2.setPower(0.15);
        }else if(pos < 0){
            if(target != 0){
                target -= 1;
            }
            arm.setTargetPosition(target);
            arm2.setTargetPosition(target);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(-0.15);
            arm2.setPower(-0.15);
        }
    }

    public void tmpAutonPos(int t){
        while(arm.isBusy() && arm2.isBusy()){
            tmpSetPost(t);
        }
    }

    public void tmpSetPost(int t){
        target = t;
        arm.setTargetPosition(target);
        arm.setPower(0.25);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setTargetPosition(target);
        arm2.setPower(0.25);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void tune(int target, double  p, double i, double d, double f){
        controller.setPID(p, i, d);
        int armPos  = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;
        arm.setPower(power);
        arm2.setPower(power);
    }

    public int armPos(){
        return arm.getCurrentPosition();
    }
}
