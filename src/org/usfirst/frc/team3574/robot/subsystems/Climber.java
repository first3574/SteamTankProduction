package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

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
    }
    public Climber(){
    	climberLeft.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	climberRight.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    public void climbStart(){
    	climberLeft.set(.50);
    	climberRight.set(.50);
    }
    
    public void climbStop(){
    	climberLeft.set(0);
    	climberRight.set(0);
    }
    public void log(){
    	
    }

}

