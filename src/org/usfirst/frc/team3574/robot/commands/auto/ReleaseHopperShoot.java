package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.hopper.SpinHopperIndex;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinShooterSystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReleaseHopperShoot extends CommandGroup {

    public ReleaseHopperShoot() {	
//    	Drive forward 15ft.
    	addSequential(new DriveForDistanceManual(15, 1, 0));
//    	Turn 90 degrees
    	addSequential(new DriveForDistanceManual(0, 1, 90));
//    	Drive 1 foot forward
    	addSequential(new DriveForDistanceManual(1, 1, 0));
//    	Turn 90 degrees
    	addSequential(new DriveForDistanceManual(0, 1, 90));
//    	Spins up shooter system (Flywheels and Transfer Belts
    	addSequential(new SpinShooterSystem());
//    	Spins Indexers to fire
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
