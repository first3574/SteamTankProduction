package org.usfirst.frc.team3574.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * Drive System
	 */
	//Left
	public static CANTalon motorDriveLeft1 = new CANTalon(1);
	public static CANTalon motorDriveleft2 = new CANTalon(2);
	//right
	public static CANTalon motorDriveRight1 = new CANTalon(13);
	public static CANTalon motorDriveRight2 = new CANTalon(12);
	
	public static DoubleSolenoid driveShifter = new DoubleSolenoid(0, 1);
	
	/**
	 * Gear System
	 */
	public static Solenoid gearFlap1 = new Solenoid(2);
	public static Solenoid gearHook = new Solenoid(3);
	/**
	 * Intake System
	 */
	public static CANTalon motorIntake = new CANTalon(11);
	
	
	
	/**
	 * Hopper
	 */
	public static CANTalon motorHopBelt = new CANTalon(4);
	public static CANTalon motorHopIndexer = new CANTalon(3);
	
	
	
	/**
	 * Shooting System
	 */
	//Left
	public static CANTalon motorFlyLeft1 = new CANTalon(6);
	public static CANTalon motorFlyLeft2 = new CANTalon(7);

	//Right
	public static CANTalon motorFlyRight1 = new CANTalon(9);
	public static CANTalon motorFlyRight2 = new CANTalon(8);
	
	
	
	/**
	 * Climber
	 */
	public static CANTalon motorClimbLeft = new CANTalon(5);
	public static CANTalon motorClimbRight = new CANTalon(10);
	
	
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
}
