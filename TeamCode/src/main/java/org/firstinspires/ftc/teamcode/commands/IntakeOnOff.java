package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeOnOff extends CommandBase {

    private final Intake intakeSubsystem;

    public IntakeOnOff(Intake subsystem) {
        intakeSubsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.start();
    }
}
