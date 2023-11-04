package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ClawWrist extends SubsystemBase {
    private ServoEx[] servos = new ServoEx[3];
    /*
        defaulted already to false
        false is open or not twisted
        true is close or twisted
     */
    private boolean[] state = new boolean[3];
    public final double CLOSE = 0; //degrees to close the claw //TODO set the degrees
    public final double OPEN = 0;
    public final double TWIST = 0; //Required amount of turn from hte wrist for pixel placement on board
    public final double UNTWIST = 0;

    public static final int CLAW1 = 0, CLAW2  = 1, WRIST  = 2;
    public ClawWrist(HardwareMap hardwareMap) {
        servos[0] = new SimpleServo(hardwareMap, "claw1", 0, 360);

        servos[1] = new SimpleServo(hardwareMap, "claw2", 0, 360);

        servos[2] = new SimpleServo(hardwareMap, "wrist", 0, 360);
    }

    public void openClose(int name){
        ServoEx claw = servos[name];
        if(state[name]){
            claw.turnToAngle(CLOSE);
            state[name] = true;
        }else {
            claw.turnToAngle(OPEN);
            state[name] = false
        }
    }

    public void twistWrist(){
        ServoEx wrist = servos[WRIST];

        if(state[WRIST]){
            wrist.turnToAngle(TWIST);
            state[WRIST] = true;
        }else{
            wrist.turnToAngle(UNTWIST);
            state[WRIST] = false;
        }
    }
}
