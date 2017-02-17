package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {
	CANTalon hopperIndex = RobotMap.motorHopIndexer;
	CANTalon hopperBelt = RobotMap.motorHopBelt;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void indexerRun(){
		hopperIndex.set(.5);
	}
	public void beltRun(){
		hopperBelt.set(.5);
	}	
	
	public void indexerStop(){
		hopperIndex.set(0);
	}
	
	public void beltStop(){
		hopperBelt.set(0);
	}
	
	public void log(){
		
	}
}

