package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoyTekerz extends Command {
	double throttle = 0;
	double wheel = 0;
    public static final double kThrottleDeadband = 0.02;
    private static final double kWheelDeadband = 0.1;

    public DriveWithJoyTekerz() {
    	requires(Robot.DriveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        wheel = handleDeadband(Robot.oi.driveStickTurnAxis(), kWheelDeadband);
        throttle = handleDeadband(Robot.oi.driveStickThrottleAxis(), kThrottleDeadband);

    	wheel = Math.pow(wheel, 3.0);
        throttle = Math.pow(throttle, 3.0);

    	Robot.DriveTrain.driveArcade(wheel, throttle);
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

    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }

}
