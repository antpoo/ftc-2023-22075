package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestOp extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException {
//        DcMotor lift1 = hardwareMap.get(DcMotor.class, "lift1");
//        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        DcMotor lift2 = hardwareMap.get(DcMotor.class, "lift2");
//        lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor arm2 = hardwareMap.get(DcMotorEx.class, "arm2");
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Servo servo = hardwareMap.get(Servo.class, "claw1");
        servo.setDirection(Servo.Direction.REVERSE);

        Servo servo2 = hardwareMap.get(Servo.class, "claw2");
        servo2.setDirection(Servo.Direction.REVERSE);

        Servo plane = hardwareMap.get(Servo.class, "plane");
        plane.setDirection(Servo.Direction.REVERSE);

        Servo wrist = hardwareMap.get(Servo.class, "wrist");
        wrist.setDirection(Servo.Direction.FORWARD);

        waitForStart();

        while(opModeIsActive()){
//            lift1.setTargetPosition(100);
//            lift1.setPower(0.2);
//            lift2.setTargetPosition(-100);
//            lift2.setPower(-0.2);
//            lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//            plane.setPosition(0.5);
//            telemetry.addData("Position: ", plane.getPosition());

//            wrist.setPosition(0.5);

//            servo.setPosition(0.25);
//            servo2.setPosition(0.1);
//            telemetry.addData("Angle: ", servo.getPosition());
//            telemetry.addData("Angle: ", servo2.getPosition());
//            telemetry.update();

            arm.setTargetPosition(170);
            arm.setPower(1);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            arm2.setTargetPosition(170);
            arm2.setPower(1);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Current Position 1: ", arm.getCurrentPosition());
            telemetry.addData("Target Position 1: ", 170);
            telemetry.addData("Arm Power 1: ", arm.getPower());
            telemetry.addData("", "");
            telemetry.addData("Current Position 2: ", arm2.getCurrentPosition());
            telemetry.addData("Target Position 2: ", 170);
            telemetry.addData("Arm Power 2: ", arm2.getPower());
            telemetry.update();
        }
    }
}
