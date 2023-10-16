package org.firstinspires.ftc.teamcode.autonomous.subsystem;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivebase {
    private Motor frontLeft, frontRight, backLeft, backRight;

    private MecanumDrive drive;

    private final String FL = "frontLeftMotor", FR = "frontRightMotor";

    private final String BL = "backLeftMotor", BR = "backRightMotor";

    private final double DEADZONE = 0.1; //TODO implement dead zone if needed

    public Drivebase(HardwareMap hardwareMap){
        //By default the motors are in RawPower mode
        frontLeft = new Motor(hardwareMap, FL, Motor.GoBILDA.RPM_312);
        frontRight = new Motor(hardwareMap, FR, Motor.GoBILDA.RPM_312);
        backLeft = new Motor(hardwareMap, BL, Motor.GoBILDA.RPM_312);
        backRight = new Motor(hardwareMap, BR, Motor.GoBILDA.RPM_312);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void move(GamepadEx gamepad){
        drive.driveRobotCentric(
                gamepad.getLeftX(),
                gamepad.getLeftY(),
                gamepad.getRightY()
        );
    }
}
