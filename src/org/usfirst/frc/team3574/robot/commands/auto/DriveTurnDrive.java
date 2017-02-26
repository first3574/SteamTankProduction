package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTurnDrive extends CommandGroup {

    public DriveTurnDrive() {
    	addSequential(new ResetYaw());
    	addSequential(new DriveForDistanceManual(1.0, 1.0, 0.0));
    	addSequential(new RotateToADegreeClockwiseOnly(59));

    }
}
