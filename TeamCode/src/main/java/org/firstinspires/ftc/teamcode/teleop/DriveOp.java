package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ArmDrive;
import org.firstinspires.ftc.teamcode.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.commands.IntakeDirection;
import org.firstinspires.ftc.teamcode.commands.IntakeOnOff;
import org.firstinspires.ftc.teamcode.commands.LiftDrive;
import org.firstinspires.ftc.teamcode.subsystem.Drivebase;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.pidfController.PIDFArm;
import org.firstinspires.ftc.teamcode.subsystem.pidfController.PIDFLift;

@TeleOp
public class DriveOp extends CommandOpMode {
    //TODO somehow implement Command System into the robot control for minimizing iteration-by-iteration robot logic
    private GamepadEx drivePad;
    private GamepadEx toolPad;

    private Drivebase drivebase;
    private DefaultDrive driveCommand;

    private PIDFArm armSubsystem;
    private ArmDrive armCommand;

    private PIDFLift liftSubsystem;
    private LiftDrive liftCommand;

    private Intake intakeSubsystem;
    private IntakeDirection intakeDirectionCommand;
    private IntakeOnOff intakeOnOffCommand;
    @Override
    public void initialize() {
        drivePad = new GamepadEx(gamepad1);
        toolPad = new GamepadEx(gamepad2);

        Button x = drivePad.getGamepadButton(GamepadKeys.Button.X);
        Button y = drivePad.getGamepadButton(GamepadKeys.Button.Y);

        drivebase = new Drivebase(hardwareMap);
        driveCommand = new DefaultDrive(drivebase, () -> drivePad.getLeftX(),
                ()-> drivePad.getLeftY(), ()-> drivePad.getRightX());

        //TODO change tolerance if needed
        armSubsystem = new PIDFArm(hardwareMap, 0);
        armCommand = new ArmDrive(armSubsystem, ()-> toolPad.getRightY());
        //TODO change tolerance if needed
        liftSubsystem = new PIDFLift(hardwareMap, 0);
        liftCommand = new LiftDrive(liftSubsystem, ()-> toolPad.getLeftY());

        intakeSubsystem = new Intake(hardwareMap);
        intakeDirectionCommand = new IntakeDirection(intakeSubsystem);
        x.whenActive(intakeDirectionCommand);
        intakeOnOffCommand = new IntakeOnOff(intakeSubsystem);
        y.whenActive(intakeOnOffCommand);

        //TODO do I need to register the drive subsystem even though I don't have a periodic setup?
        drivebase.setDefaultCommand(driveCommand);
        armSubsystem.setDefaultCommand(armCommand);
    }
}
