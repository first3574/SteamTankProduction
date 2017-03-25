package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.shooter.StopBelts;
import org.usfirst.frc.team3574.robot.util.L;

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
    	setDefaultCommand(new StopBelts());
    }
    
	public void beltRun(){
		hopperBelt.set(13);//86% ?
	}
	
	public void beltStop(){
		hopperBelt.set(0);
	}

	public void log() {
//		L.ogSDTalonBasics("Hopper Belt", hopperBelt);
//		L.ogSD("BOB", hopperBelt.getFaultUnderVoltage());
		L.ogSD("Belt Current", hopperBelt.getOutputVoltage());
	}
}

