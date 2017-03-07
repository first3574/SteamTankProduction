package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.climber.ClimberVariable;
import org.usfirst.frc.team3574.robot.commands.climber.TriggerMonitor;
import org.usfirst.frc.team3574.robot.triggers.TriggerButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	CANTalon climberLeft = RobotMap.motorClimbLeft;
	CANTalon climberRight = RobotMap.motorClimbRight;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TriggerMonitor());
    }
    /**
     * Reversing the output of these motors will not work.  If they spin the wrong direction consistantly, correct the code..
     */
    public Climber(){
    	climberLeft.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	climberRight.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	climberRight.reverseOutput(false);
    	climberLeft.reverseOutput(false);
    }
    public void climbStart(){
    	climberLeft.set(8);
    	climberRight.set(-8);
    }
    
    public void climbStop(){
    	climberLeft.set(0);
    	climberRight.set(0);
    }
    
    public void set(double speed) {
    	climberLeft.set(-13.0 * speed);
    	climberRight.set(13.0 * speed);
    }
    
    public void log(){
    }

}

