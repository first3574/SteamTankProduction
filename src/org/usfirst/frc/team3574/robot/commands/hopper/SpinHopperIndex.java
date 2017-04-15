package org.usfirst.frc.team3574.robot.commands.hopper;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinHopperIndex extends Command {
	boolean slowedDown;
	
    public SpinHopperIndex() {
        requires(Robot.HopperIndex);
        requires(Robot.Intake);
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	slowedDown = false;
    	Robot.HopperIndex.indexerRun();
    	L.ogCmdInit(this);
    }
    
    int timeOffset = 0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeSinceInitialized() < timeOffset){
    		Robot.Intake.intakeRun(-1);
    	} else {
    		Robot.Intake.intakeRun(1);
    		if(timeSinceInitialized() > timeOffset + 1) {
    			timeOffset += 1;
    		}
    	}
    	
    	if (!slowedDown && timeSinceInitialized() > 0.2) {
        	Robot.Shooter.setSetpoint(Robot.Shooter.DROPPED_SPEED_AGAINST_WALL);
        	slowedDown = true;
        	L.ogSD("Shooter Speed", 3900.0);
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
    	Robot.HopperIndex.indexerStop();
    	L.og("Shoot Ended " + timeSinceInitialized());
    }
}
