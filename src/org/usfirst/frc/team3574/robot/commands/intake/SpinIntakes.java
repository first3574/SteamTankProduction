package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinIntakes extends Command {
	double xAxisValue;
	double yAxisValue;
	double deadZoneHalf = 0.15;
	Timer time = new Timer();
	boolean runOnce;
	
    public SpinIntakes() {
    	requires(Robot.Intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	runOnce = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	xAxisValue = Robot.oi.stickXAxis();
    	yAxisValue = Robot.oi.stickYAxis();
    	if (xAxisValue < deadZoneHalf && xAxisValue > -deadZoneHalf){
    		xAxisValue = 0;
    	}
    	
    	if (yAxisValue < deadZoneHalf && yAxisValue > -deadZoneHalf){
    		yAxisValue = 0;
    	}
    	
    	if (xAxisValue != 0 || yAxisValue != 0){
    		Robot.Intake.intakeRun();
    	}
    	else if(runOnce){
    		time.reset();
    		time.start();
    		runOnce = false;
    	}
    	else if(time.get() > 2){
    		Robot.Intake.intakeStop();
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
