package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.climber.Climber;
import org.usfirst.frc.team3574.robot.commands.drivetrain.AlternateShifter;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceWithNavx;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveOtherWay;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftOff;
import org.usfirst.frc.team3574.robot.commands.drivetrain.makeQuickTurnTrue;
import org.usfirst.frc.team3574.robot.commands.hopper.spinHopperBelts;
import org.usfirst.frc.team3574.robot.commands.intake.SpIntakesManual;
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
//	Declaring the Joysticks we use
	Joystick stick = new Joystick(0);
	Joystick joy = new Joystick(1);
//	Boolean to show if the match is 20 seconds to end
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
		 * DRIVETRAIN FUNTIONS
		 */

		
//		to shift to high gear [A BUTTON]
		JoystickButton alternateGear = new JoystickButton(stick, 1);
		alternateGear.whenPressed(new AlternateShifter());
		
//		To reset yaw [START BUTTON]
		JoystickButton resetYaw = new JoystickButton(stick, 8);
		resetYaw.whenPressed(new ResetYaw());
		
//		Inverts drive controls [BACK BUTTON]
		JoystickButton invertDrive = new JoystickButton(stick, 7);
		invertDrive.whenPressed(new DriveOtherWay());
		
		
		/**
		 * SHOOTING FUNTIONS
		 */
//		
		JoystickButton spinFlywheels = new JoystickButton(joy, 6);
		spinFlywheels.whileHeld(new SpinFlys());
		
		
		
		/**
		 * CLIMBING FUNTIONS
		 */
		JoystickButton climbUp = new JoystickButton(joy, 10);
		climbUp.whileHeld(new Climber());
		
		
		/**
		 * INTAKE FUNTIONS
		 */
		JoystickButton spintakeWheels = new JoystickButton(joy, 9);
		spintakeWheels.whileHeld(new SpIntakesManual());
		
		
		/**
		 * HOPPER FUNTIONS
		 */
		JoystickButton spinHopperBelts  = new JoystickButton(joy, 7);
		spinHopperBelts.whileHeld(new spinHopperBelts());
		
		/**
		 * GEAR MANIPULATOR FUNTIONS
		 */
		
		
		
		/**
		 * MISC FUNTIONS
		 */
		
		
	}
	
	public double stickYAxis() {
		return stick.getRawAxis(4);
	}

	public double stickXAxis() {
		return stick.getRawAxis(1);
	}
	
	public boolean getQuickTurnButton(){
//		Checks the boolean value of button 6 (Right Bumper)
		return stick.getRawButton(6);
	}
	
	public void rumble() {
//		If the match is 20 seconds from the end, set the remote to rumble
		if(isLast20) {
			stick.setRumble(RumbleType.kRightRumble, 0.5);
			joy.setRumble(RumbleType.kRightRumble, 0.5);
		}
	}
}
