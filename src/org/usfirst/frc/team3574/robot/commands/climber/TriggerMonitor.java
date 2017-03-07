package org.usfirst.frc.team3574.robot.commands.climber;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.Climber;
import org.usfirst.frc.team3574.robot.util.L;
import org.usfirst.frc.team3574.robot.util.RunClimberStopShooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class TriggerMonitor extends Command {

    public TriggerMonitor() {
    	requires(Robot.Climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.oi.climbAxis() > .02){
    		Scheduler.getInstance().add(new RunClimberStopShooter());
    		
    		L.og("got here");
    		return true;
    	}
    	else{
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
