package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulator extends Subsystem {
	Solenoid gearFlap1 = RobotMap.gearFlap1;
	Solenoid gearHook = RobotMap.gearHook;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    	
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void flapGearStore() {
    	gearFlap1.set(true);
    	}
     
    public void flapGearReadyForIntake() {   	
    	gearFlap1.set(false);
//    	gearFlap2.set(DoubleSolenoid.Value.kForward);
    }
    
    public void hookUp() {    	
    	gearHook.set(true);
    }

    public void hookDown() {    	
    	gearHook.set(false);
    }
}

