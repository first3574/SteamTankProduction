package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.hopper.BeltStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperBelt extends Subsystem {
	CANTalon hopperBelt = RobotMap.motorHopBelt;
	
	public HopperBelt(){
    	hopperBelt.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BeltStop());
    }
    
	public void beltRun(){
		hopperBelt.set(6);
	}	
	
	public void beltStop(){
		hopperBelt.set(0);
	}
	
}

