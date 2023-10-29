package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.pidfController.PIDFArm;

import java.util.function.DoubleSupplier;

public class ArmDrive extends CommandBase {

    private final PIDFArm armSubsystem;
    private final double multiplier;
    private final double factor = 20;
    public ArmDrive(PIDFArm subsystem, DoubleSupplier m) {
        armSubsystem = subsystem;
        multiplier = m.getAsDouble();

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        armSubsystem.move((int) (factor * multiplier));
    }
}
