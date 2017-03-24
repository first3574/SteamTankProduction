package org.usfirst.frc.team3574.robot.commands.hopper;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NEWSpinHopperIndex extends Command {
	double timerOffset = 0.0;
	double timeToRun;
	double timeToRunOffset = 0.05;
	boolean runOnce = false;
	
    public NEWSpinHopperIndex() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.HopperIndex);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.HopperIndex.indexerRun();
    	L.og("Shoot Started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timeSinceInitialized() > timerOffset && Robot.Shooter.acceptableShooterRange()) {
    		if(!runOnce) {
    			timeToRun = timeSinceInitialized() + timeToRunOffset;
    			runOnce = true;
    		}
        	if(timeSinceInitialized() > timeToRun) {
        		timerOffset += timeSinceInitialized() + 0.25;
        		runOnce = false;
        		Robot.HopperIndex.indexerStop();
        	} else {
        		Robot.HopperIndex.indexerRun();
        	}
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
