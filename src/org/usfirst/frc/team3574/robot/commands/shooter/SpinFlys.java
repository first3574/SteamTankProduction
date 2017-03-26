package org.usfirst.frc.team3574.robot.commands.shooter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinFlys extends Command {
	public double speed = Robot.Shooter.shooterSpeed;
//	    	spinUp = 2250, angle = 105, distance = inches
//	    	spinUp = 3200ish, angle = 105, distance = several feet.
	    

    public SpinFlys() {
    	requires(Robot.Shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public SpinFlys(double shooterSpeed) {
    	this();
    	this.speed = shooterSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogCmdInit(this);
    	Robot.Shooter.spinUp(speed); //1547
    	Robot.HopperBelt.beltRun();
    	
//    
//    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (Robot.Shooter.getLeftSpeed() > 3400.0) {
//    		Robot.Shooter.clearI();
//    	}
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
