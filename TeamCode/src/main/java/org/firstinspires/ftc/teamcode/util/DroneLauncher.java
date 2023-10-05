package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DroneLauncher {

    private final String NAME = "";
    private Servo servo;


    public DroneLauncher(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, NAME);
    }

    public void launch() {
        servo.setPosition(0.0);
    }




}
