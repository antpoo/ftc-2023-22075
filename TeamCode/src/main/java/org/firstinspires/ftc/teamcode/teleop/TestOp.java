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
        DcMotor lift1 = hardwareMap.get(DcMotor.class, "lift1");
        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor lift2 = hardwareMap.get(DcMotor.class, "lift2");
        lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotorEx arm2 = hardwareMap.get(DcMotorEx.class, "arm2");
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Servo servo = hardwareMap.get(Servo.class, "claw1");
        servo.setDirection(Servo.Direction.REVERSE);

        Servo servo2 = hardwareMap.get(Servo.class, "claw2");
        servo2.setDirection(Servo.Direction.REVERSE);

        Servo plane = hardwareMap.get(Servo.class, "plane");
        plane.setDirection(Servo.Direction.FORWARD);

        Servo wrist = hardwareMap.get(Servo.class, "wrist");
        wrist.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){
//            lift1.setTargetPosition(500);
//            lift1.setPower(1);
//            lift2.setTargetPosition(-500);
//            lift2.setPower(-1);
//            lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//            lift1.setPower(-gamepad1.left_stick_y);
//            lift2.setPower(gamepad1.left_stick_y);

//            plane.setPosition(0.5);
//            telemetry.addData("Position: ", plane.getPosition());


//            wrist.setPosition(0);
//            servo.setPosition(0.25);
//            servo2.setPosition(0.25);
//            telemetry.addData("Angle: ", servo.getPosition());
//            telemetry.addData("Angle: ", servo2.getPosition());
//            telemetry.update();
            arm.setTargetPosition(50);
            arm.setPower(0.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            arm2.setTargetPosition(50);
            arm2.setPower(0.5);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            arm.setVelocity(arm2.getVelocity());

//            telemetry.addData("ticks_in_degree ", arm.getMotorType().getTicksPerRev() / 180.0);

//            arm.setPower(-gamepad1.left_stick_y);
//            arm2.setPower(-gamepad1.left_stick_y);
//
//            telemetry.addData("left_Stick_y", -gamepad1.left_stick_y);
//            telemetry.addData("Arm1: ", arm.getPower());
//            telemetry.addData("Arm2 :", arm2.getPower());

            telemetry.addData("Current Position 1: ", arm.getCurrentPosition());
            telemetry.addData("Target Position 1: ", 50);
            telemetry.addData("Arm Power 1: ", arm.getPower());
            telemetry.addData("", "");
            telemetry.addData("Current Position 2: ", arm2.getCurrentPosition());
            telemetry.addData("Target Position 2: ", 50);
            telemetry.addData("Arm Power 2: ", arm2.getVelocity());
            telemetry.update();
        }
    }
}
