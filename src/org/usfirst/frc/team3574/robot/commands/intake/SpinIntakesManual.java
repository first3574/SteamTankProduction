package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class SpinIntakesManual extends Command {
	double power;
	
    public SpinIntakesManual() {
    	requires(Robot.Intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	power = deadzone(Robot.oi.intakeStickYAxis());
    	Robot.Intake.intakeRun(power);
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
    	Robot.Intake.intakeStop();
    }
    
    private double deadzone(double axis) {
    	if(Math.abs(axis) > 0.3) {
    		return axis;
    	} else {
    	return 0.0;
    	}
    }
}
