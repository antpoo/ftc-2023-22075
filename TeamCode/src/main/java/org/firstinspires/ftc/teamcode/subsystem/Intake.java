package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends SubsystemBase {
    private final Motor motor;
    private final ServoEx servo1, servo2;
    private final String name = "intake";

    private boolean forward = true;

    private boolean on = false;
    private boolean setup = false;

    private final double SPEED = 0.20;
    private final double TURN_DEGREES = 0; //TODO set the turn degrees

    public Intake(HardwareMap hardwareMap) {
        motor = new Motor(hardwareMap, name, 7, 5800); //6000 theoretical maximum
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //TODO if the motor needs to be inverted

        servo1 = new SimpleServo(hardwareMap, "intakeServo1", 0, 360);
        servo2 = new SimpleServo(hardwareMap, "intakeServo2", 0, 360);
    }

    public void spin() {
        if (forward) {
            motor.set(SPEED);
            forward = false;
        }else {
            motor.set(-SPEED);
            forward = true;
        }
    }

    public void start() {
        if(!setup) {
            servo1.turnToAngle(TURN_DEGREES);
            servo2.turnToAngle(TURN_DEGREES);
            motor.set(SPEED);
            forward = false;
            on = true;
            setup = true;
        }

        if(on){
            motor.set(SPEED);
            on = false;
        }else{
            motor.set(0);
            on = true;
        }
    }
}