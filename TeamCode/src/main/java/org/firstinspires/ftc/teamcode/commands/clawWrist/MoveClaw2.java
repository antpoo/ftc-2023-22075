package org.firstinspires.ftc.teamcode.commands.clawWrist;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ClawWrist;

public class MoveClaw2 extends CommandBase {
    private ClawWrist clawWristSubsystem;
    public MoveClaw2(ClawWrist subsystem) {
        clawWristSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        clawWristSubsystem.openClose(ClawWrist.CLAW2);
    }
}
