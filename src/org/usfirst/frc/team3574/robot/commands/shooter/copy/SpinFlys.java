package org.usfirst.frc.team3574.robot.commands.shooter.copy;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinFlys extends Command {

    public SpinFlys() {
    	requires(Robot.Shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogCmdInit(this);
    	Robot.Shooter.setSetpoint(2250); //1547
    	
//    	spinUp = 2250, angle = 105, distance = inches
//    	spinUp = 3200ish, angle = 105, distance = several feet.
    
//    
//    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
