package org.usfirst.frc.team3574.robot.commands.climber;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberVariable extends Command {

    public ClimberVariable() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Climber.set(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Robot.oi.getCoTriggers()) > 0.2) {
    		// to the power of 5 for reactivity, could be clearer code!
    		Robot.Climber.set(Math.pow(Robot.oi.getCoTriggers(), 5.0));
    	} else {
    		Robot.Climber.set(0);
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
