package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipulator extends Subsystem {
	DoubleSolenoid gearFlap1 = RobotMap.gearFlap1;
	DoubleSolenoid gearFlap2 = RobotMap.gearFlap2;
	DoubleSolenoid gearHook = RobotMap.gearHook;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    	
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void flapUp(){
    	gearFlap1.set(DoubleSolenoid.Value.kReverse);
    	gearFlap2.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void flapDown(){   	
    	gearFlap1.set(DoubleSolenoid.Value.kForward);
    	gearFlap2.set(DoubleSolenoid.Value.kForward);
    }
    
    public void hookOut(){    	
    	gearHook.set(DoubleSolenoid.Value.kForward);
    }

    public void hookIn(){    	
    	gearHook.set(DoubleSolenoid.Value.kReverse);
    }
}

