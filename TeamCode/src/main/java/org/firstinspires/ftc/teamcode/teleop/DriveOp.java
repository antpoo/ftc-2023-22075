package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ArmDrive;
import org.firstinspires.ftc.teamcode.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.commands.LiftDrive;
import org.firstinspires.ftc.teamcode.commands.clawWrist.MoveClaw1;
import org.firstinspires.ftc.teamcode.commands.clawWrist.MoveClaw2;
import org.firstinspires.ftc.teamcode.commands.clawWrist.MoveWrist;
import org.firstinspires.ftc.teamcode.commands.clawWrist.ReleasePlane;
import org.firstinspires.ftc.teamcode.subsystem.ClawWrist;
import org.firstinspires.ftc.teamcode.subsystem.Drivebase;
import org.firstinspires.ftc.teamcode.subsystem.Plane;
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

    private ClawWrist clawWristSubsystem;
    private MoveWrist moveWristCommand;
    private MoveClaw1 moveClaw1Command;
    private MoveClaw2 moveClaw2Command;

    private Plane planeSubsystem;
    private ReleasePlane releaseCommand;


    @Override
    public void initialize() {
        drivePad = new GamepadEx(gamepad1);
        toolPad = new GamepadEx(gamepad2);


        //TODO add presets for arm positions and lift positions with new commands
        GamepadButton a = toolPad.getGamepadButton(GamepadKeys.Button.A);
        GamepadButton b = toolPad.getGamepadButton(GamepadKeys.Button.B);
        GamepadButton x = toolPad.getGamepadButton(GamepadKeys.Button.X);

        GamepadButton y = drivePad.getGamepadButton(GamepadKeys.Button.Y);


        drivebase = new Drivebase(hardwareMap);
        driveCommand = new DefaultDrive(drivebase, () -> drivePad.getLeftX(),
                ()-> drivePad.getLeftY(), ()-> drivePad.getRightX());

        //TODO change tolerance if needed
        armSubsystem = new PIDFArm(hardwareMap, 0);
        armCommand = new ArmDrive(armSubsystem, ()-> toolPad.getRightY());
        //TODO change tolerance if needed
        liftSubsystem = new PIDFLift(hardwareMap, 0);
        liftCommand = new LiftDrive(liftSubsystem, ()-> toolPad.getLeftY());

        clawWristSubsystem = new ClawWrist(hardwareMap);
        moveClaw1Command = new MoveClaw1(clawWristSubsystem);
        a.whenActive(moveClaw1Command);
        moveClaw2Command = new MoveClaw2(clawWristSubsystem);
        b.whenActive(moveClaw2Command);
        moveWristCommand = new MoveWrist(clawWristSubsystem);
        x.whenActive(moveWristCommand);

        planeSubsystem = new Plane(hardwareMap);
        releaseCommand = new ReleasePlane(planeSubsystem);
        y.whenActive(releaseCommand);


        //TODO do I need to register the drive subsystem even though I don't have a periodic setup?
        drivebase.setDefaultCommand(driveCommand);
        armSubsystem.setDefaultCommand(armCommand);
        liftSubsystem.setDefaultCommand(liftCommand);
    }
}
