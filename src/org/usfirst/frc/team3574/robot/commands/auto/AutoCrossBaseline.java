package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossBaseline extends CommandGroup {

    public AutoCrossBaseline() {
    	addSequential(new DriveForDistanceManual(7, 0.5, 0.0));
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
