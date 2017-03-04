package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.intake.SpIntakesWhenMoving;
import org.usfirst.frc.team3574.robot.util.L;

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
    	setDefaultCommand(new SpIntakesWhenMoving());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * negative is pulling into the robot
     * @param speed
     */
    public void intakeRun(double speed) {
    	intake.set(speed);
    }
    
    public void agitateHopper() {
    	intake.set(.5);
    }

    public void intakeStop() {
    	intake.set(0);
    }
    
    public void log() {
    	L.ogSDTalonBasics("Intake", intake);
    }
}