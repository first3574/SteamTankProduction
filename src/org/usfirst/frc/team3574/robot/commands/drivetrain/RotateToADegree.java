package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class RotateToADegree extends Command {
	double yaw;
	int direction;
	int targetYaw;
	double speed;
	
	public RotateToADegree(int angle, double turnSpeed) {
		requires(Robot.DriveTrain);
		this.targetYaw = angle;
		this.speed = turnSpeed;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		direction = -1;
	
		if(targetYaw < 0) {
			direction *= 1;
		}
		Robot.DriveTrain.driveTekerz(speed * direction, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.DriveTrain.getYaw();
//		System.out.println(targetYaw);
//		System.out.println(yaw);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(yaw >= targetYaw - 5 && yaw <= targetYaw + 5) {
			Robot.DriveTrain.driveTekerz(0.0, 0.0);
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