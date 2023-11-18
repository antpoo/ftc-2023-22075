package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Plane extends SubsystemBase {

    ServoEx servo;

    private final int RELEASE = 360;

    public Plane(HardwareMap hardwareMap){
        servo = new SimpleServo(hardwareMap, "plane", 0, 360);
    }

    public void release(){
        servo.turnToAngle(360);
    }
}
