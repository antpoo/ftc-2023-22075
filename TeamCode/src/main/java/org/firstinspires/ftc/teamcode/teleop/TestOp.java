package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
//        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");
//        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Servo servo = hardwareMap.get(Servo.class, "claw1");
        servo.setDirection(Servo.Direction.REVERSE);

        Servo servo2 = hardwareMap.get(Servo.class, "claw2");
        servo2.setDirection(Servo.Direction.REVERSE);

//        Servo plane = hardwareMap.get(Servo.class, "plane");

        waitForStart();

        while(opModeIsActive()){
//            lift1.setTargetPosition(100);
//            lift1.setPower(0.2);
//            lift2.setTargetPosition(-100);
//            lift2.setPower(-0.2);
//            lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//            plane.setPosition(0.50);

//            servo.setPosition(0.25);
            servo2.setPosition(0.0);
            telemetry.addData("Angle: ", servo.getPosition());
            telemetry.addData("Angle: ", servo2.getPosition());
            telemetry.update();

//            arm.setTargetPosition(-100);
//            arm.setPower(-0.5);
//            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
