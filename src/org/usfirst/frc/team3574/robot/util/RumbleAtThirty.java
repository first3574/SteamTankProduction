package org.usfirst.frc.team3574.robot.util;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleAtThirty extends Command {

    public RumbleAtThirty() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double matchTime = DriverStation.getInstance().getMatchTime();
    	if(matchTime <= 30 && matchTime >= 28) {
    		Robot.oi.startRumble();
    		L.ogSD("Match Time", matchTime);
    	} else{
    		Robot.oi.stopRumble();
    		L.ogSD("Match Time", matchTime);
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
