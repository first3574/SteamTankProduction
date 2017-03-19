package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegree;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.shooter.AutoShoot;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDraftHopperShootRed extends CommandGroup {
	private static int ANGLE_TOWARDS_HOPPER = 90;
	private static int ANGLE_TOWARDS_BOILER = 180;
	private static int ANGLE_READY_TO_SHOOT = ANGLE_TOWARDS_BOILER - 45;
	
    public AutoDraftHopperShootRed() {
    	addSequential(new ResetYaw());
    	addSequential(new DriveForDistanceManual(8.0, 1, 0.0));
    	addSequential(new RotateToADegreeClockwiseOnly(ANGLE_TOWARDS_HOPPER, 1));
    	addSequential(new DriveForDistanceManual(2.0, 1, 0.0));
//    	addSequential(new NoDrive(), 5);
//    	addSequential(new DriveForDistanceManual(-2.0, -0.4, 0.0));
//    	addSequential(new RotateToADegree(ANGLE_TOWARDS_BOILER, 0.4));
//    	addParallel(new SpinFlys());
//    	addSequential(new DriveForDistanceManual(4.33, 0.6, 0.0));
//    	addSequential(new RotateToADegree(ANGLE_READY_TO_SHOOT, 0.4));
//    	addSequential(new DriveForDistanceManual(0.5, 0.2, 0.0));
//    	addSequential(new AutoShoot());
    	
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