
package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3574.robot.commands.auto.AutonomousSelector;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyArcade;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.subsystems.Climber;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3574.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3574.robot.subsystems.HopperBelt;
import org.usfirst.frc.team3574.robot.subsystems.HopperIndex;
import org.usfirst.frc.team3574.robot.subsystems.Intake;
import org.usfirst.frc.team3574.robot.subsystems.Shooter;
import org.usfirst.frc.team3574.robot.util.RumbleReminder;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//	Declaring systems
	public static final HopperIndex HopperIndex = new HopperIndex();
	public static final HopperBelt HopperBelt = new HopperBelt();
	public static final Climber Climber = new Climber();
	public static final DriveTrain DriveTrain = new DriveTrain();
	public static final Intake Intake = new Intake();
	public static final GearManipulator GearManipulator = new GearManipulator();
	public static final Shooter Shooter = new Shooter();
	public static OI oi;
//	Declaring some Commands
	Command autonomousCommand;
	Command rumbleRemindee;
	//SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Object> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Do Nothing", 0);
		chooser.addObject("Cross Baseline", 1);
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(Scheduler.getInstance());
		
		SmartDashboard.putData("Spin Right Round Baby Right Round", new DriveForDistanceManual(10, .20, 0));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		this.log();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		this.log();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutonomousSelector(chooser.getSelected());

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		this.log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	
		rumbleRemindee = (new RumbleReminder());
		if (rumbleRemindee != null)
			rumbleRemindee.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		this.log();
	}
	
	/**
	 * This function is to make sure the log methods in each of the subsystems run.
	 */
	public void log() {
		DriveTrain.log();
		Climber.log();
		HopperIndex.log();
		Shooter.log();
		Intake.log();
		HopperBelt.log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
