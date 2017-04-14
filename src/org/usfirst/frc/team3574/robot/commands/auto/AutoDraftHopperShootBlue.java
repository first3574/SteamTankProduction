package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.DisableBrakeMode;
import org.usfirst.frc.team3574.robot.commands.EnableBrakeMode;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegree;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndex5InchesBack;
import org.usfirst.frc.team3574.robot.commands.intake.RunIntakes;
import org.usfirst.frc.team3574.robot.commands.shooter.AutoShoot;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.shoot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDraftHopperShootBlue extends CommandGroup {
	private static int ANGLE_TOWARDS_HOPPER = -90;
	private static int ANGLE_TOWARDS_BOILER = -89;
	private static int ANGLE_READY_TO_SHOOT = 44;
	
    public AutoDraftHopperShootBlue() {
    	addSequential(new ResetYaw());
//    	addSequential(new EnableBrakeMode());
    	addSequential(new ShiftLowGear());
    	
    	
    	addSequential(new DriveForDistanceManual((9.65 - 0.34), 1.0, 0.0), 8);
    	addSequential(new RotateToADegreeCounterClockwiseOnly(ANGLE_TOWARDS_HOPPER, 0.6));
    	addSequential(new ResetYaw());
    	addSequential(new DriveForDistanceManual(3.363, 0.5, 0.0, 0.0), 4);
    	addSequential(new NoDrive(), 1.0);


    	addParallel(new SpinFlys(3125));
    	
    	// don't bother backing up
    	// addSequential(new DriveForDistanceManual(-4.5, -1, 0.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(ANGLE_TOWARDS_BOILER, 0.6));

    	
    	addSequential(new ResetYaw());
    	addParallel(new RunIntakes());
    	addSequential(new DriveForDistanceManual(8.25, 1.0, 0.0));
    	addSequential(new RotateToADegreeClockwiseOnly(ANGLE_READY_TO_SHOOT, 0.6));
//    	addSequential(new DriveForDistanceManual(0.8, 0.75, 0.0));
    	addSequential(new NEWSpinHopperIndex5InchesBack());
//    	addSequential(new DisableBrakeMode());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
