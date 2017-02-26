package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToADegreeCounterClockwiseOnly extends Command {
	double yaw;
	int reverse;
	int targetYaw;
	
	
	public RotateToADegreeCounterClockwiseOnly(int positiveIsClockwise) {
		requires(Robot.DriveTrain);
		this.targetYaw = positiveIsClockwise;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		reverse = -1;
		
		Robot.DriveTrain.driveArcade(0.0, -0.5 * reverse);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.DriveTrain.getYaw();
//		System.out.println(targetYaw);
//		System.out.println(yaw);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(yaw <= targetYaw) {
			Robot.DriveTrain.driveArcade(0.0, 0.0);
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
