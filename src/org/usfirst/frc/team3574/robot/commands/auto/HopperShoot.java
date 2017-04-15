package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceDOESNOTSTOP;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.hopper.SpinHopperIndex;
import org.usfirst.frc.team3574.robot.commands.intake.RunIntakes;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperShoot extends CommandGroup {

    public HopperShoot() {
    	addSequential(new DriveForDistanceDOESNOTSTOP(6.15, -1.0, 0.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(-45, 0.75));
    	addParallel(new SpinFlys());
    	addParallel(new RunIntakes());
    	addSequential(new DriveForDistanceDOESNOTSTOP(4.3, 1.0, 0.0));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(-30, 0.75));
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
