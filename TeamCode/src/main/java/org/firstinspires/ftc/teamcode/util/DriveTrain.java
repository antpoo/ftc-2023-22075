package org.firstinspires.ftc.teamcode.util;

import static java.lang.Math.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private final String FL = "frontLeftMotor", FR = "frontRightMotor";
    private final String BL = "backLeftMotor", BR = "backRightMotor";
    private final double DEADZONE = 0.1;

    public DriveTrain(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, FL);
        frontRight = hardwareMap.get(DcMotor.class, FR);
        backLeft = hardwareMap.get(DcMotor.class, BL);
        backRight = hardwareMap.get(DcMotor.class, BR);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void drive(double x, double y) {
        if (abs(x) > DEADZONE && abs(y) < DEADZONE) {
            frontLeft.setPower(x);
            frontRight.setPower(x);
            backLeft.setPower(x);
            backRight.setPower(x);
        }
        else if (abs(x) < DEADZONE && abs(y) > DEADZONE) {
            frontLeft.setPower(y);
            backLeft.setPower(-y);
            frontRight.setPower(y);
            backRight.setPower(-y);
        }
        else if (abs(x) > DEADZONE && abs(y) > DEADZONE) {
            if (x / abs(x) == y / abs(y)) {
                frontLeft.setPower(p(x, y) * x / abs(x));
                backRight.setPower(p(x, y) * x / abs(x));
            }
            else {
                frontRight.setPower(p(x, y) * x / abs(x));
                backLeft.setPower(p(x, y) * x / abs(x));
            }
        }
        else {
            frontLeft.setPower(0.0);
            frontRight.setPower(0.0);
            backLeft.setPower(0.0);
            backRight.setPower(0.0);
        }
    }

    private double p(double x, double y) {
        return sqrt(x * x + y * y);
    }


}
