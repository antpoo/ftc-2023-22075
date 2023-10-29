package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeDirection extends CommandBase {

    private final Intake intakeSubsystem;

    public IntakeDirection(Intake subsystem) {
        intakeSubsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        intakeSubsystem.spin();
    }
}
