package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.intake.SpinIntakes;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	CANTalon intake = RobotMap.motorIntake;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake() {
		intake.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new SpinIntakes());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intakeRun() {
    	intake.set(0.5);
    }
    
    public void agitateHopper() {
    	intake.set(-0.25);
    }

    public void intakeStop() {
    	intake.set(0.0);
    }
    
    public void log() {
    	
    }
}