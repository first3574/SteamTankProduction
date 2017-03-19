package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelector extends Command {
	Timer time = new Timer();
	Command command;
	int step;
	boolean isFinished = false;
	Object autoStart;

	public AutonomousSelector(Object autoStart) {
		this.autoStart = autoStart;
		
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isFinished = false;
		step = 0;
		time.reset();
		time.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch(step) {
			case 0:
				if (autoStart.equals(0/*Integer.valueOf(0)*/)) {
					command = (new NoDrive());
				} else if (autoStart.equals(1)) {
					command = (new AutoCrossBaselinePrepHopperRed());
				} else if(autoStart.equals(2)) {
					command = (new AutoCrossBaselinePrepHopperBlue());
				} else if(autoStart.equals(3)) {
					command = (new OLDCrossBaseline());
				} else if(autoStart.equals(4)) {
					command = (new AutoDraftHopperShootBlue());
				}
				
				command.start();
				step++;
				break;
				
			case 1:
		}


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
