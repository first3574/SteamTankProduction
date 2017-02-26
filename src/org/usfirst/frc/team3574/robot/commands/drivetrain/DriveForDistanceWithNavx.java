package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistanceWithNavx extends Command {
	int targetTicks;
	double speed;
	double rotationalOffset;
	double yaw;
	int leftEnc;
	int rightEnc;

	double inc = 0.001;
	boolean runOnce = true;
	
	/**
	 * this is supposed to do interesting things, but we're not sure what, yet.
	 * @param ticks
	 * @param speed
	 */
	public DriveForDistanceWithNavx(int ticks, double speed) {
		requires(Robot.DriveTrain);
		this.targetTicks = ticks;
		this.speed = speed;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.DriveTrain.resetYaw();
		Robot.DriveTrain.resetEncoders();
		Robot.DriveTrain.driveArcade(speed, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.DriveTrain.getYaw();
		leftEnc = Robot.DriveTrain.getLeftEnc();
		rightEnc = Robot.DriveTrain.getRightEnc();

		if (yaw > 1 || yaw < -1) {
			while(yaw > 0) {
				if(runOnce) {
					inc = 0;
					runOnce = false;
				}
				inc += inc;
				Robot.DriveTrain.driveArcade(speed, inc);    		
			}
			while(yaw > 0) {
				if(runOnce) {
					inc = 0;
					runOnce = false;
				}
				inc -= inc;
				Robot.DriveTrain.driveArcade(speed, inc);    		
			}
		} else {
			Robot.DriveTrain.driveArcade(speed, 0);
			runOnce = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
