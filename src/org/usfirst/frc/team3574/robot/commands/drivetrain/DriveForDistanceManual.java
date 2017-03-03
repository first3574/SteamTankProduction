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
	
    public DriveForDistanceManual(int ticks, double speed, double rotation) {
        requires(Robot.DriveTrain);
        this.targetTicks = ticks;
        this.speed = speed;
        this.rotation = rotation;
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public DriveForDistanceManual(double feet, double speed, double rotation) {
    	this((int)(Math.round(feet * 4)), speed, rotation);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.DriveTrain.resetEncoders();
    	Robot.DriveTrain.driveArcade(speed, rotation);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(Robot.DriveTrain.getLeftEnc() < 200 && Robot.DriveTrain.getRightEnc() < 200) {
			Robot.DriveTrain.driveArcade( 0.5, rotation * 0.5);
		} else if(Robot.DriveTrain.getLeftEnc() > (targetTicks - 500) || Robot.DriveTrain.getRightEnc() > (targetTicks - 500)) {
			Robot.DriveTrain.driveArcade(speed * 0.5, rotation * 0.5);	
		} else {
			Robot.DriveTrain.driveArcade(speed, rotation);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(targetTicks < Robot.DriveTrain.getLeftEnc() || targetTicks < Robot.DriveTrain.getRightEnc() && this.timeSinceInitialized() > 1) {
    		
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
