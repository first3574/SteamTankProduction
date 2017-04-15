package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceDOESNOTSTOP;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.DropGearHooksRunIntakes;
import org.usfirst.frc.team3574.robot.commands.intake.RunIntakes;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FrontGear extends CommandGroup {

    public FrontGear() {
    	addSequential(new DriveForDistanceDOESNOTSTOP(3.0, 0.75, 0.0));
    	addSequential(new DriveForDistanceDOESNOTSTOP(0.0, 0.0, 0.0));
    	addSequential(new DropGearHooksRunIntakes());
    	addSequential(new NoDrive(), .5);
    	addSequential(new DriveForDistanceDOESNOTSTOP(2.0, -1.0, 0.0));
    	
    	
    	
    	
    	
    	
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
