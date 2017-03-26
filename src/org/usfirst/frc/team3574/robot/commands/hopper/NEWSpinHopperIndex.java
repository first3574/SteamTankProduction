package org.usfirst.frc.team3574.robot.commands.hopper;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NEWSpinHopperIndex extends Command {
	double startTimeOfLoop = 0.0;
	double timeToRun;
	boolean isIndexing = false;
	boolean slowedDown = false;
	
    public NEWSpinHopperIndex() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.HopperIndex);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTimeOfLoop = -loopLength;
//    	Robot.HopperIndex.indexerRun();
    	L.og("Shoot Started");
//    	Robot.Shooter.spinUp(2925);
    	slowedDown = false;
    	L.ogSD("Shooter Speed", 3900.0);
    }
    
    double loopLength = 0.08;
	double timeToRunLength = 0.05;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (Robot.Shooter.acceptableShooterRange()) {
//        	Robot.HopperIndex.indexerRun();
//    	} else {
//    		Robot.HopperIndex.indexerStop();
//    	}
    	
    	if (!slowedDown && timeSinceInitialized() > 0.2) {
        	Robot.Shooter.spinUp(2925);
        	slowedDown = true;
        	L.ogSD("Shooter Speed", 3900.0);
    	}
    	
    	if(!slowedDown) {
    		L.ogSD("Shooter Speed", 3900.0);	
    	}
    	
    	if (startTimeOfLoop + loopLength < timeSinceInitialized() && Robot.Shooter.acceptableShooterRange()) {
    		startTimeOfLoop = timeSinceInitialized();    		
    		isIndexing = true;
    	}
    	if (isIndexing) {
    		if (startTimeOfLoop + timeToRunLength < timeSinceInitialized()) {
    			Robot.HopperIndex.indexerStop();
    			isIndexing = false;
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
