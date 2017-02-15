package org.usfirst.frc.team3574.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Left
	public static CANTalon motorLeftDrive1 = new CANTalon(1);
	public static CANTalon motorleftDrive2 = new CANTalon(2);
	//right
	public static CANTalon motorRightDrive1 = new CANTalon(3);
	public static CANTalon motorRightDrive2 = new CANTalon(4);
	
	
	public static DoubleSolenoid shifterDrive = new DoubleSolenoid(0, 1);
	
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
}
