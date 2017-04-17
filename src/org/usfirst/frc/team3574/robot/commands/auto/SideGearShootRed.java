package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceDOESNOTSTOP;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.DropGearHooks;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.DropGearHooksRunIntakes;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndex5InchesBack;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndexAgainstWall;
import org.usfirst.frc.team3574.robot.commands.hopper.SpinHopperIndex;
import org.usfirst.frc.team3574.robot.commands.shooter.AutoShoot;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideGearShootRed extends CommandGroup {
	static final int ROTATE_TOWARDS_AIRSHIP = -60;
	static final int ROTATE_TOWARDS_BOILER = 139;
	
	
    public SideGearShootRed() {
    	addSequential(new ResetYaw());
    	
    	addSequential(new DriveForDistanceManual(7.5766, 0.6, 0.0, 1.5));
    	addSequential(new RotateToADegreeCounterClockwiseOnly(ROTATE_TOWARDS_AIRSHIP, 0.6));
    	addSequential(new DriveForDistanceManual(1.48, 0.2, 0.0));
    	addSequential(new DropGearHooksRunIntakes());
    	addSequential(new NoDrive(), 0.5);
    	addSequential(new DriveForDistanceManual(2.0, -0.2, 0.0));
    	
    	/*
    	 * Place for gear hopper shoot breakoff
    	 */
    	
    	
    	addSequential(new RotateToADegreeClockwiseOnly(ROTATE_TOWARDS_BOILER, 0.8));
    	addParallel(new SpinFlys(3137));
    	addSequential(new NoDrive(), 0.5);
    	addSequential(new DriveForDistanceManual(8.0, 1.0, 0.0, 2.0), 3);
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
