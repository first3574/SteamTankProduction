package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoy extends Command {
	double throttle = 0;
	double turnValue = 0;
	
    public DriveWithJoy() {
    	requires(Robot.DriveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	throttle = Robot.oi.stickYAxis();
    	turnValue = Robot.oi.stickXAxis();
    	
    	
    	Robot.DriveTrain.cheesyDrive(throttle,	turnValue);
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
