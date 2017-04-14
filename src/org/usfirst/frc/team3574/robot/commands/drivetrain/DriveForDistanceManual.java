package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistanceManual extends Command {
	int targetTicks;
	double speed;
	double rotation;
	double slowDownValue;
	boolean isNegativeSpeed;
	static final double TICKS_PER_FOOT = 3099;

	private DriveForDistanceManual(int ticks, double speed, double rotation) {
		requires(Robot.DriveTrain);
		this.targetTicks = ticks;
		this.speed = speed;
		this.rotation = rotation;

		if(speed < 0) {
			isNegativeSpeed = true;
		} else {
			isNegativeSpeed = false;
		}

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	public DriveForDistanceManual(double feet, double speed, double rotation) {
		this((int)(Math.round(feet * TICKS_PER_FOOT)), speed, rotation);
		this.slowDownValue = 2.5;
	}
	
	
	public DriveForDistanceManual(double feet, double speed, double rotation, double distanceFromTargetToSlowDown) {
		this((int)(Math.round(feet * TICKS_PER_FOOT)), speed, rotation);
		this.slowDownValue = distanceFromTargetToSlowDown;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.DriveTrain.resetEncoders();
    	L.ogCmdInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
//		if(Robot.DriveTrain.getLeftEnc() < 200 && Robot.DriveTrain.getRightEnc() < 200) {
//			Robot.DriveTrain.driveArcade( 0.5, rotation * 0.5);
//			} else if(Robot.DriveTrain.getLeftEnc() > (targetTicks - 500) || Robot.DriveTrain.getRightEnc() > (targetTicks - 500)) {
//			Robot.DriveTrain.driveArcade(rotation * 0.5, -speed * 0.5);	
//		} else {
//			Robot.DriveTrain.driveArcade(rotation, -speed);
//		}
	}
	boolean runOnece = false;
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (leftDistanceTravelled() < 1.0 /*&& rightDistanceTravelled() < 1.0*/) {
			Robot.DriveTrain.driveTekerz(0.0, (speed / Math.abs(speed) * -0.6));			
		}
		
		if(timeSinceInitialized() > .12 && (targetTicks - TICKS_PER_FOOT * this.slowDownValue < Math.abs(Robot.DriveTrain.getLeftEnc()) 
				/*|| targetTicks - TICKS_PER_FOOT * this.slowDownValue < Math.abs(Robot.DriveTrain.getRightEnc())*/
						)) {    			
			
			if(!runOnece) {
				L.og("Driving Slowed " + this.timeSinceInitialized());
				runOnece = true;
			}
			Robot.DriveTrain.driveTekerz(0.0, (speed / Math.abs(speed) * -0.4));
		} else {
			Robot.DriveTrain.driveTekerz(rotation, -speed);
		}
		if(timeSinceInitialized() > .12 && 
				(targetTicks < Math.abs(Robot.DriveTrain.getLeftEnc()) 
//						|| targetTicks < Math.abs(Robot.DriveTrain.getRightEnc())
				)) {
			Robot.DriveTrain.driveTekerz(0.0, 0.0);
			return true;
//					} else if(isNegativeSpeed && (targetTicks > Robot.DriveTrain.getLeftEnc() || targetTicks > Robot.DriveTrain.getRightEnc())) {
//
//					Robot.DriveTrain.driveArcade(0.0, 0.0);
//					return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		L.og("drive for distance " + this.timeSinceInitialized());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	/**
	double rightDistanceTravelled() {
//		L.og("RIGHT:	" + Math.abs(Robot.DriveTrain.getRightEnc()) / TICKS_PER_FOOT);
		return Math.abs(Robot.DriveTrain.getRightEnc()) / TICKS_PER_FOOT;
	}
	**/
	
	double leftDistanceTravelled() {
//		L.og("LEFT:					" + Math.abs(Robot.DriveTrain.getLeftEnc()) / TICKS_PER_FOOT);
		return Math.abs(Robot.DriveTrain.getLeftEnc()) / TICKS_PER_FOOT;
	}
	
}
