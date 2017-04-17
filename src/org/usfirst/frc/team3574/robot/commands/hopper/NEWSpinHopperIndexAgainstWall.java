package org.usfirst.frc.team3574.robot.commands.hopper;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NEWSpinHopperIndexAgainstWall extends Command {
	double startTimeOfLoop = 0.0;
	double timeToRun;
	boolean isIndexing = false;
	boolean slowedDown = false;
	Timer time = new Timer();
	
    public NEWSpinHopperIndexAgainstWall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.HopperIndex);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.reset();
    	time.start();
    	startTimeOfLoop = -loopLength;
//    	Robot.HopperIndex.indexerRun();
    	L.ogCmdInit(this);
//    	Robot.Shooter.spinUp(2925);
    	slowedDown = false;
    	L.ogSD("Shooter Speed", 3900.0);
    }
    
    double loopLength = 0.08;
	double timeToRunLength = 0.05;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeSinceInitialized() > 1.0){
    		Robot.Intake.intakeRun(1);
    	} else {
    		Robot.Intake.intakeRun(-1);
    	}
   
    	
//    	if (Robot.Shooter.acceptableShooterRange()) {
//        	Robot.HopperIndex.indexerRun();
//    	} else {
//    		Robot.HopperIndex.indexerStop();
//    	}
    	
    	if (!slowedDown && timeSinceInitialized() > 0.2) {
        	Robot.Shooter.setSetpoint(Robot.Shooter.DROPPED_SPEED_AGAINST_WALL);
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
//    	    	L.og(time.get());
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
    	L.ogCmdEnd(this);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.HopperIndex.indexerStop();
    	L.ogCmdInterrupted(this);
    }
}
