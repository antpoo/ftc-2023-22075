package org.firstinspires.ftc.teamcode.commands.clawWrist;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ClawWrist;

public class MoveWrist extends CommandBase {
    private ClawWrist clawWristSubsystem;
    public MoveWrist(ClawWrist subsystem) {
        clawWristSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        clawWristSubsystem.twistWrist();
    }
}
