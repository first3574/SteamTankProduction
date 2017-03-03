package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.hopper.BeltStop;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperBelt extends Subsystem {
	CANTalon hopperBelt = RobotMap.motorHopBelt;
	
	public HopperBelt(){
    	hopperBelt.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BeltStop());
    }
    
	public void beltRun(){
		hopperBelt.set(1);
	}	
	
	public void beltStop(){
		hopperBelt.set(0);
	}

	public void log() {
		L.ogSDTalonBasics("Hopper Belt", hopperBelt);
		L.ogSD("BOB", hopperBelt.getFaultUnderVoltage());
	}
}

