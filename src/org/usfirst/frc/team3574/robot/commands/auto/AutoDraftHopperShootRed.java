package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegree;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.intake.RunIntakes;
import org.usfirst.frc.team3574.robot.commands.shooter.AutoShoot;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDraftHopperShootRed extends CommandGroup {
	private static int ANGLE_TOWARDS_HOPPER = 90;
	private static int ANGLE_TOWARDS_BOILER = 90;
	private static int ANGLE_READY_TO_SHOOT = -44;
	
    public AutoDraftHopperShootRed() {
    	addSequential(new ResetYaw());
//    	addSequential(new EnableBrakeMode());
    	addSequential(new ShiftLowGear());
    	
    	// this is longer on purpose even though that makes absolutely zero sense
    	addSequential(new DriveForDistanceManual(10.827, 1.0, 0.0));
    	addSequential(new RotateToADegreeClockwiseOnly(ANGLE_TOWARDS_HOPPER, .6));
    	addSequential(new ResetYaw());
    	addSequential(new DriveForDistanceManual(2.7, 0.5, 0.0, 0.0), 2);
    	addSequential(new NoDrive(), 1.0);
    	addSequential(new DriveForDistanceManual(-2.5, -1, 0.0));
    	addSequential(new RotateToADegreeClockwiseOnly(ANGLE_TOWARDS_BOILER, .6));
    	addSequential(new ResetYaw());
    	addParallel(new RunIntakes());
    	addParallel(new SpinFlys(2375));
    	addSequential(new DriveForDistanceManual(8.18, 1.0, 0.0, 1.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(ANGLE_READY_TO_SHOOT, 0.8));
//    	addSequential(new DriveForDistanceManual(0.8, 0.75, 0.0));
    	addSequential(new AutoShoot());
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
