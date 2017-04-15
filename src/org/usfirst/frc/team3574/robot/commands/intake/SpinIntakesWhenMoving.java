package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinIntakesWhenMoving extends Command {
	double getLeftValue;
	double getRightValue;
	double deadZoneHalf = 2;
	Timer time = new Timer();
	boolean runOnce;
	
    public SpinIntakesWhenMoving() {
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
    	getLeftValue = Robot.DriveTrain.getLeftVolt();
    	getRightValue = Robot.DriveTrain.getRightVolt();
    	if (getLeftValue < deadZoneHalf && getLeftValue > -deadZoneHalf){
    		getLeftValue = 0;
    	}
    	
    	if (getRightValue < deadZoneHalf && getRightValue > -deadZoneHalf){
    		getRightValue = 0;
    	}
    	
    	if (getLeftValue != 0 || getRightValue != 0){
    		Robot.Intake.intakeRun(200);
    	}
    	else if(runOnce){
    		time.reset();
    		time.start();
    		runOnce = false;
    	}
    //the intake keeps running for about 3 seconds after stopping	
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
