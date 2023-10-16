package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.subsystem.Drivebase;

@TeleOp
public class DriveOp extends LinearOpMode {
    //TODO somehow implement Command System into the robot control for minimizing iteration-by-iteration robot logic
    @Override
    public void runOpMode() throws InterruptedException {
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
        Drivebase drivebase = new Drivebase(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            drivebase.move(gamepadEx1);

            //if statements that will check the
        }
    }
}
