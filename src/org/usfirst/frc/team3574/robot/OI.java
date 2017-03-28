package org.usfirst.frc.team3574.robot;

import java.nio.file.attribute.PosixFileAttributeView;

import org.usfirst.frc.team3574.robot.commands.climber.ClimberGo;
import org.usfirst.frc.team3574.robot.commands.drivetrain.AlternateShifter;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveOtherWay;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.DropGearHooks;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.DropGearHooksRunIntakes;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearFlapOut;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.CloseGearDoor;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearHookUp;
import org.usfirst.frc.team3574.robot.commands.gearmanipulator.GearHookUpFlapOut;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndexAgainstWall;
import org.usfirst.frc.team3574.robot.commands.intake.SpinIntakesManual;
import org.usfirst.frc.team3574.robot.commands.shooter.IdleShooter;
import org.usfirst.frc.team3574.robot.commands.shooter.RaiseShooterSpeed;
import org.usfirst.frc.team3574.robot.commands.shooter.ResetShooter;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinBelts;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinShooterSystem;
import org.usfirst.frc.team3574.robot.commands.shooter.StopShooterSystem;
import org.usfirst.frc.team3574.robot.commands.shooter.shoot;
import org.usfirst.frc.team3574.robot.triggers.TriggerButton;
import org.usfirst.frc.team3574.robot.util.ResetYaw;
import org.usfirst.frc.team3574.robot.util.RunClimberStopShooter;

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
	Joystick driverWoodpecker = new Joystick(0);
	Joystick gunner = new Joystick(1);
//	Boolean to show if the match is 20 seconds to end
	public boolean isLast20 = false;
	
	/**
	 * Buttons
	 */
	static final int A_BUTTON = 1;
	static final int B_BUTTON = 2;
	static final int X_BUTTON = 3;
	static final int Y_BUTTON = 4;
	static final int LEFT_BUMPER = 5;
	static final int RIGHT_BUMPER = 6;
	static final int BACK = 7;
	static final int START = 8;
	static final int RIGHT_THUMBSTICK_BUTTON = 9;
	static final int LEFT_THUMBSTICK_BUTTON = 10;
	
	/**
	 *  Axis
	 */
	static final int LEFT_STICK_X = 0;
	static final int LEFT_STICK_Y = 1;
	static final int RIGHT_STICK_X = 4;
	static final int RIGHT_STICK_Y = 5;
	
	static final int LEFT_TRIGGER = 2;
	static final int RIGHT_TRIGGER = 3;	
	
	/**
	 * Misc
	 */
	int POV = 0;
	
	
	
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

	public OI() { /* please make sure to place each button under it's respective function.*/
	
	/**				Driver-Free/Used Buttons
	 
	 * A-----------Shooter: SpinUp.
	 * B-----------free
	 * X-----------free
	 * Y-----------free
	 * LBumper-----cheesyDrive: QuickTurn.
	 * RBumper-----free
	 * Back--------DriveTrain: ReverseDirection
	 * Start-------free
	 * RThumbstick-free
	 * LThumbstick-free
	  
	 * 				Driver-Free/Used Axis
	 
	 *LXStick------used
	 *LYStick------used
	 *RXStick------used
	 *RYStick------used
	 *LTrigger-----free
	 *RTrigger-----Shooter: shoot
	 
	 *POV----------DriveTrain: ShiftUp & ShiftDown
	 */
		
	/**				Gunner-Free/Used Buttons
	 
	 * A-----------NotYet
	 * B-----------free
	 * X-----------free
	 * Y-----------Shooter: IdleShooter
	 * LBumper-----Intake: SetIntakeManual
	 * RBumper-----free
	 * Back--------free
	 * Start-------free
	 * RThumbstick-free
	 * LThumbstick-free
	  
	 * 				Driver-Free/Used Axis
	 
	 *LXStick------free
	 *LYStick------free
	 *RXStick------NotYet
	 *RYStick------Intake: UseIntakeManual
	 *LTrigger-----Climber: ClimbUp
	 *RTrigger-----GearManipulator: DropGear
	 
	 *POV----------GearManipulator: FlapOutHookUp & FlapIn
	 */
	
		/**
		 * DRIVETRAIN FUNTIONS
		 */

		Button highShiftWithPOV = new POVButtonForTop(driverWoodpecker, POV);
		highShiftWithPOV.whenPressed(new ShiftHighGear());
		
		Button lowShiftWithPOV = new POVButtonForBottom(driverWoodpecker, POV);
		lowShiftWithPOV.whenPressed(new ShiftLowGear());

//		Inverts drive controls [BACK BUTTON]
//		JoystickButton invertDrive = new JoystickButton(driverWoodpecker, BACK);
//		invertDrive.whenPressed(new DriveOtherWay());
		
		
		/**
		 * SHOOTING FUNTIONS
		 */
		
		JoystickButton spinShooterSystem = new JoystickButton(gunner, A_BUTTON);
//		spinShooterSystem.whenPressed(new SpinShooterSystem());
		spinShooterSystem.whenPressed(new SpinFlys());
		
		TriggerButton launchFuel = new TriggerButton(driverWoodpecker, RIGHT_TRIGGER);
		launchFuel.whileHeld(new shoot());
//		launchFuel.whenPressed(new SpinHopperIndex());		
		
		
		JoystickButton raiseShot = new JoystickButton(gunner, BACK);
		raiseShot.whenPressed(new RaiseShooterSpeed());
		
		JoystickButton stopShooter = new JoystickButton(gunner, Y_BUTTON);
		stopShooter.whenPressed(new ResetShooter());
		
		
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
		TriggerButton hookDown = new TriggerButton(gunner, RIGHT_TRIGGER);
		hookDown.whenPressed(new DropGearHooksRunIntakes());
		
//		JoystickButton hookUp = new JoystickButton(gunner, Y_BUTTON);
//		hookUp.whenPressed(new GearHookUp());
		
		Button gearFlapOut = new POVButtonForTop(gunner, POV);
		gearFlapOut.whenPressed(new GearHookUpFlapOut());
		
		Button gearFlapIn = new POVButtonForBottom(gunner, POV);
		gearFlapIn.whenPressed(new CloseGearDoor());
		
		/**
		 * MISC FUNTIONS
		 */
		
		
	}
	public double driveStickThrottleAxis() {
		return driverWoodpecker.getRawAxis(RIGHT_STICK_Y);
	}
	
	public double driveStickTurnAxis() {
		return -driverWoodpecker.getRawAxis(LEFT_STICK_X);
	}
	
	public double intakeStickYAxis() {
		return gunner.getRawAxis(RIGHT_STICK_Y);
	}
	
	public double climbAxis() {
		return gunner.getRawAxis(LEFT_TRIGGER);
	}
	
	public boolean getQuickTurnButton(){
//		Checks the boolean value of button 6 (Right Bumper)
		return driverWoodpecker.getRawButton(6);
	}
	
	public void startRumble() {
//		driverWoodpecker.setRumble(RumbleType.kLeftRumble, 1);
	}
	
	public void stopRumble(){
		driverWoodpecker.setRumble(RumbleType.kLeftRumble, 1);
	}
	
}
