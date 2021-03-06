package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToADegreeClockwiseOnly extends Command {
	double yaw;
	int reverse;
	int targetYaw;
	double speed;
	double slowedSpeed = 0.2;
	/**
	 * 
	 * @param angle
	 * please use a positive number : )
	 * @param speed
	 */
	public RotateToADegreeClockwiseOnly(int angle, double speed) {
		requires(Robot.DriveTrain);
		this.targetYaw = angle;
		this.speed = speed;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	public RotateToADegreeClockwiseOnly(int angle, double speed, double slowSpeed) {
		this(angle, speed);
		this.slowedSpeed = slowSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		reverse = -1;
	
//		if(targetYaw < 0) {
//			reverse *= -1;
//		}
		Robot.DriveTrain.driveTekerz(speed * reverse, 0.0);
    	L.ogCmdInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.DriveTrain.getYaw();
//		System.out.println(targetYaw);
//		System.out.println(yaw);
	}

	boolean runOnce = false;
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		L.ogSD("Target Yaw", targetYaw);
		if(yaw >= targetYaw - 22) {
			if(!runOnce) {
				L.og("Rotating Slowed " + this.timeSinceInitialized());
				runOnce = true;
			}
			Robot.DriveTrain.driveTekerz(slowedSpeed * reverse, 0.0);
		}
		
		if(yaw > targetYaw) {
			Robot.DriveTrain.driveTekerz(0.0, 0.0);
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
//		L.og("Rotate Clockwise " + this.timeSinceInitialized());
		L.ogCmdEnd(this);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogCmdInterrupted(this);
	}
}
