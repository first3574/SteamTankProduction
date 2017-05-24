package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceDOESNOTSTOP;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndex5InchesBack;
import org.usfirst.frc.team3574.robot.commands.hopper.SpinHopperIndex;
import org.usfirst.frc.team3574.robot.commands.intake.RunIntakes;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NEWTestHopperShootRed extends CommandGroup {

    public NEWTestHopperShootRed() {
    	addSequential(new ResetYaw());
    	addSequential(new ShiftLowGear());
    	//Angle towards wall
    	addSequential(new RotateToADegreeClockwiseOnly(20, 0.8, 0.225));
    	//drive into wall
    	addSequential(new DriveForDistanceManual(5.5, -0.8, 0.0));
    	
    	/**
    	 * Start of hit hopper
    	 */
    	addSequential(new RotateToADegreeCounterClockwiseOnly(15, 0.6, 0.4));
    	addSequential(new DriveForDistanceManual(0.2, -0.6, 0.0), 1);
    	addSequential(new RotateToADegreeCounterClockwiseOnly(10, 0.6, 0.4));
    	addSequential(new DriveForDistanceManual(0.2, -0.6, 0.0), 1);
    	addSequential(new RotateToADegreeCounterClockwiseOnly(6, 0.6, 0.4));
    	addSequential(new DriveForDistanceManual(0.2, -0.6, 0.0), 1);
    	addSequential(new RotateToADegreeCounterClockwiseOnly(4, 0.6, 0.4));
    	addSequential(new DriveForDistanceManual(0.2, -0.6, 0.0), 1);
    	addSequential(new RotateToADegreeCounterClockwiseOnly(2, 0.6, 0.4));
    	addSequential(new DriveForDistanceManual(0.2, -0.6, 0.0), 1);

    	addSequential(new RotateToADegreeClockwiseOnly(7, 1.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(2, 0.6));
    	
    	/**
    	 * End of hit hopper
    	 */
    	
    	addSequential(new NoDrive(), 1.5);
    	addParallel(new RunIntakes());
    	addSequential(new DriveForDistanceManual(7.0, 1.0, 0.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(-26, 1.0, 1.0));
    	addParallel(new SpinFlys(3125));
    	addSequential(new NoDrive(), 2.0);
    	addSequential(new SpinHopperIndex());
    	
    	
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