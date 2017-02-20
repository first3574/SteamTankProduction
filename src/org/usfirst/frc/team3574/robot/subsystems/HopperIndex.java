package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.hopper.IndexStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperIndex extends Subsystem {
	CANTalon hopperIndex = RobotMap.motorHopIndexer;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public HopperIndex(){
    	hopperIndex.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IndexStop());
    }
    
	public void indexerRun(){
		hopperIndex.set(6);
	}
	
	public void indexerStop(){
		hopperIndex.set(0);
	}
	

	public void log(){
		
	}
}

