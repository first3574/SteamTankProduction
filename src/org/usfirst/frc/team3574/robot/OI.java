package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.auto.DriveForDistanceWithNavx;
import org.usfirst.frc.team3574.robot.commands.climber.Climber;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveOtherWay;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftOff;
import org.usfirst.frc.team3574.robot.commands.hopper.spinHopperBelts;
import org.usfirst.frc.team3574.robot.commands.intake.SpinIntakes;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.subsystems.Hopper;
import org.usfirst.frc.team3574.robot.util.ResetYaw;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick = new Joystick(0);
	Joystick joy = new Joystick(1);
	
	public boolean isLast20 = false;
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public OI() { /* please make sure to place each button under it's respective function.
		/**
		 * DRIVETRAIN FUNCTIONS
		 */

		JoystickButton highGear = new JoystickButton(stick, 1);
		highGear.whenPressed(new ShiftHighGear());
		
		JoystickButton lowGear = new JoystickButton(stick, 2);
		lowGear.whenPressed(new ShiftLowGear());
		
		JoystickButton offGear = new JoystickButton(stick, 3);
		offGear.whenPressed(new ShiftOff());
		
		
		JoystickButton resetYaw = new JoystickButton(stick, 8);
		resetYaw.whenPressed(new ResetYaw());
		
		JoystickButton runDriveNavx  = new JoystickButton(stick, 5);
		runDriveNavx.whenPressed(new DriveForDistanceWithNavx(42, .42));
		

		JoystickButton invertDrive = new JoystickButton(stick, 7);
		invertDrive.whenPressed(new DriveOtherWay());
		
		
		/**
		 * SHOOTING FUNCTIONS
		 */

		JoystickButton spinFlywheels = new JoystickButton(stick, 6);
		spinFlywheels.whenPressed(new SpinFlys());
		
		
		/**
		 * CLIMBING FUNCTIONS
		 */
		JoystickButton climbUp = new JoystickButton(stick, 10);
		climbUp.whileHeld(new Climber());
		
		
		/**
		 * INTAKE FUNCTIONS
		 */
		JoystickButton spintakeWheels = new JoystickButton(stick, 9);
		spintakeWheels.whileHeld(new SpinIntakes());
		
		
		/**
		 * HOPPER FUNCTIONS
		 */
		JoystickButton spinHopperBelts  = new JoystickButton(stick, 4);
		spinHopperBelts.whileHeld(new spinHopperBelts());		
		
		/**
		 * MISC FUNCTIONS
		 */
		
		
	}
	
	public double stickYAxis() {
		return stick.getRawAxis(4);
	}

	public double stickXAxis() {
		return stick.getRawAxis(1);
	}
	
	public void rumble() {
		if(isLast20) {
			stick.setRumble(RumbleType.kRightRumble, 0.5);
		}
	}
}
