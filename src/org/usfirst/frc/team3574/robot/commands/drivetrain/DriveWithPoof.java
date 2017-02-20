package org.usfirst.frc.team3574.robot.commands.drivetrain;
import org.usfirst.frc.team3574.robot.commands.drivetrain.UtilQueso;
import org.usfirst.frc.team3574.robot.util.L;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveSignalQueso;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithPoof extends Command {
    double mQuickStopAccumulator;
    public static final double kThrottleDeadband = 0.02;
    private static final double kWheelDeadband = 0.1;
    private static final double kTurnSensitivity = 1.0;	
	
    public DriveWithPoof() {
        requires(Robot.DriveTrain);
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
            double wheel = handleDeadband(Robot.oi.driveStickXAxis(), kWheelDeadband);
            double throttle = handleDeadband(Robot.oi.driveStickYAxis(), kThrottleDeadband);
            boolean isQuickTurn = !Robot.oi.getQuickTurnButton();
            double overPower;

            double angularPower;

            if (isQuickTurn) {
                if (Math.abs(throttle) < 0.2) {
                    double alpha = 0.1;
                    mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * UtilQueso.limit(wheel, 1.0) * 2;
                }
                overPower = 1.0;
                angularPower = wheel;
            } else {
                overPower = 0.0;
                angularPower = Math.abs(throttle) * wheel * kTurnSensitivity - mQuickStopAccumulator;
                if (mQuickStopAccumulator > 1) {
                    mQuickStopAccumulator--;
                } else if (mQuickStopAccumulator < -1) {
                    mQuickStopAccumulator++;
                } else {
                    mQuickStopAccumulator = 0.0;
                }
            }

            double rightPwm = throttle - angularPower;
            double leftPwm = throttle + angularPower;
            if (leftPwm > 1.0) {
                rightPwm -= overPower * (leftPwm - 1.0);
                leftPwm = 1.0;
            } else if (rightPwm > 1.0) {
                leftPwm -= overPower * (rightPwm - 1.0);
                rightPwm = 1.0;
            } else if (leftPwm < -1.0) {
                rightPwm += overPower * (-1.0 - leftPwm);
                leftPwm = -1.0;
            } else if (rightPwm < -1.0) {
                leftPwm += overPower * (-1.0 - rightPwm);
                rightPwm = -1.0;
            }
            L.ogSD("RightPWM", rightPwm);
            L.ogSD("LeftPWM", leftPwm);
            Robot.DriveTrain.driveCheesy(rightPwm, leftPwm);
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
