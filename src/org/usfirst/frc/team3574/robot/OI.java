package org.usfirst.frc.team3574.robot;

import java.nio.file.attribute.PosixFileAttributeView;

import org.usfirst.frc.team3574.robot.commands.shoot;
import org.usfirst.frc.team3574.robot.commands.climber.ClimberGo;
import org.usfirst.frc.team3574.robot.commands.drivetrain.AlternateShifter;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveOtherWay;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearFlapIn;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearFlapOut;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearHookDown;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearHookUp;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearHookUpFlapOut;
import org.usfirst.frc.team3574.robot.commands.hopper.SpinHopperIndex;
import org.usfirst.frc.team3574.robot.commands.intake.SpinIntakesManual;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinBelts;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinShooterSystem;
import org.usfirst.frc.team3574.robot.commands.shooter.StopShooterSystem;
import org.usfirst.frc.team3574.robot.triggers.TriggerButton;
import org.usfirst.frc.team3574.robot.util.ResetYaw;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team3574.robot.triggers.POVButton;
import org.usfirst.frc.team3574.robot.triggers.POVButtonForTop;
import org.usfirst.frc.team3574.robot.triggers.POVButtonForBottom;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
//	Declaring the Joysticks we use
	Joystick stick0 = new Joystick(0);
	Joystick stick1 = new Joystick(1);
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

		Button highShiftWithPOV = new POVButtonForTop(stick0, 0);
		highShiftWithPOV.whenPressed(new ShiftHighGear());
		
		Button lowShiftWithPOV = new POVButtonForBottom(stick0, 0);
		lowShiftWithPOV.whenPressed(new ShiftLowGear());

		//		To reset yaw [START BUTTON]
		JoystickButton resetYaw = new JoystickButton(stick0, 8);
		resetYaw.whenPressed(new ResetYaw());
		
//		Inverts drive controls [BACK BUTTON]
		JoystickButton invertDrive = new JoystickButton(stick0, 7);
		invertDrive.whenPressed(new DriveOtherWay());
		
		
		/**
		 * SHOOTING FUNTIONS
		 */
		JoystickButton spinShooterSystem = new JoystickButton(stick1, 1);
		spinShooterSystem.whenPressed(new SpinShooterSystem());
		
		
		JoystickButton stopShooterSystem = new JoystickButton(stick1, 2);
		stopShooterSystem.whenPressed(new StopShooterSystem());
		
		TriggerButton launchFuel = new TriggerButton(stick0, 3);
		launchFuel.whileHeld(new shoot());
//		launchFuel.whenPressed(new SpinHopperIndex());
		/**
		 * HOPPER FUNTIONS
		 */
		
		/**
		 * INTAKE FUNTIONS
		 */


		/**
		 * CLIMBING FUNTIONS
		 */	
		
		/**
		 * GEAR MANIPULATOR FUNTIONS
		 */
		
		TriggerButton hookDown = new TriggerButton(stick1, 3);
		hookDown.whenPressed(new GearHookDown());
		
		JoystickButton hookUp = new JoystickButton(stick1, 4);
		hookUp.whenPressed(new GearHookUp());

		JoystickButton gearDone = new JoystickButton(stick0, 1);
		gearDone.whenPressed(new GearHookUpFlapOut());
		
		Button gearFlapOut = new POVButtonForTop(stick1, 0);
		gearFlapOut.whenPressed(new GearFlapOut());
		
		Button gearFlapIn = new POVButtonForBottom(stick1, 0);
		gearFlapIn.whenPressed(new GearFlapIn());
		/**
		 * MISC FUNTIONS
		 */
		
		JoystickButton climb = new JoystickButton(stick1, 6);
		climb.whileHeld(new ClimberGo());
		JoystickButton manualIntake = new JoystickButton(stick1, 5);
		manualIntake.whileHeld(new SpinIntakesManual());
		
		JoystickButton beltSpin = new JoystickButton(stick0, 4);
		beltSpin.whileHeld(new SpinBelts());
		
	}
	public double driveStickXAxis() {
		return stick0.getRawAxis(0);
	}
	public double driveStickYAxis() {
		return stick0.getRawAxis(5);
	}
	
	public double intakeStickYAxis() {
		return stick1.getRawAxis(5);
	}
	public double climbAxis() {
		return -stick1.getRawAxis(2);
	}

	
	public boolean getQuickTurnButton(){
//		Checks the boolean value of button 6 (Right Bumper)
		return stick0.getRawButton(6);
	}
	
	public void rumble() {
		stick0.setRumble(RumbleType.kRightRumble, 0.5);
		stick1.setRumble(RumbleType.kRightRumble, 0.5);
	}
}
