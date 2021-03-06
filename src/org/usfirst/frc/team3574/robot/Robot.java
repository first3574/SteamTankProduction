
package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3574.robot.commands.auto.AutonomousSelector;
import org.usfirst.frc.team3574.robot.commands.auto.AutoFrontGear;
import org.usfirst.frc.team3574.robot.commands.auto.AutoHopperShootBlue;
import org.usfirst.frc.team3574.robot.commands.auto.AutoHopperShootRed;
import org.usfirst.frc.team3574.robot.commands.auto.NEWTestHopperShootBlue;
import org.usfirst.frc.team3574.robot.commands.auto.NEWTestHopperShootRed;
import org.usfirst.frc.team3574.robot.commands.auto.SideGearShootBlue;
import org.usfirst.frc.team3574.robot.commands.auto.SideGearShootRed;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceManual;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyArcade;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegree;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseThrough180;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc.team3574.robot.commands.hopper.NEWSpinHopperIndex5InchesBack;
import org.usfirst.frc.team3574.robot.commands.shooter.LowerShooterSpeed;
import org.usfirst.frc.team3574.robot.commands.shooter.RaiseShooterSpeed;
import org.usfirst.frc.team3574.robot.commands.shooter.SpinFlys;
import org.usfirst.frc.team3574.robot.commands.shooter.StopFlys;
import org.usfirst.frc.team3574.robot.subsystems.Climber;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3574.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3574.robot.subsystems.HopperBelt;
import org.usfirst.frc.team3574.robot.subsystems.HopperIndex;
import org.usfirst.frc.team3574.robot.subsystems.Intake;
import org.usfirst.frc.team3574.robot.subsystems.Shooter;
import org.usfirst.frc.team3574.robot.util.BesterTester;
import org.usfirst.frc.team3574.robot.util.BesterTesterTester;
import org.usfirst.frc.team3574.robot.util.L;
import org.usfirst.frc.team3574.robot.util.RumbleAtThirty;
import org.usfirst.frc.team3574.robot.util.StartRumble;
import org.usfirst.frc.team3574.robot.util.StopRumble;


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
	Timer time = new Timer();
	public Timer autoTimer = new Timer();
	double lastTime = 0.0;
	int autoIteration = 0;
	
	
//	private Timer time = new Timer();
	
//	Declaring some Commands
	Command autonomousCommand;
	Command rumbleRemindee;
	//SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Object> chooser = new SendableChooser<>();
	SendableChooser<Object> alliance = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		time.start();
		oi = new OI();
		chooser.addDefault("Do Nothing", 0);
//		chooser.addObject("Cross Baseline get Hopper Red", 1);
//		chooser.addObject("Cross Baseline get hopper blue", 2);
		chooser.addObject("OLD cross baseline", 3);
//		chooser.addObject("Hopper and Shoot", 8);
		chooser.addObject("New Hopper & Boiler Shoot", 5);
		chooser.addObject("Front Gear", 6);
		chooser.addObject("Boiler Side Gear Shoot", 7);
		
		
		alliance.addObject("Blue", 0);
		alliance.addObject("Red", 1);
		
		Shooter.shooterSpeed = 1937.5; //RPM
		DriveTrain.resetYaw();
		DriveTrain.resetEncoders();
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("Alliance", alliance);
		SmartDashboard.putData("Scheduler (named = Bob)", Scheduler.getInstance());
//		L.ogSD("Shoot Lower", new LowerShooterSpeed());
//		L.ogSD("Shoot Higher", new RaiseShooterSpeed());
		
		L.ogSD("Reset Yaw", new ResetYaw());
		
//		L.ogSD("PULL THE LEVER!!", new StartRumble(Robot.oi.driverWoodpecker));
//		L.ogSD("UN-PULL THE LEVER!!", new StopRumble(Robot.oi.driverWoodpecker));
		
//		L.ogSD("Rotate 90 left", new RotateToADegreeCounterClockwiseOnly(90, 0.6));
//		L.ogSD("Rotate 180 left", new RotateToADegree(180, 0.4));
//		L.ogSD("Rotate 90 right", new RotateToADegree(-90, 0.4));
//		L.ogSD("Rotate 180 right", new RotateToADegree(-180, 0.4));

//		L.ogSD("hopper shoot first step", new DriveForDistanceManual(10.167, 1.0, 0.0));
//		L.ogSD("Drive .5", new DriveForDistanceManual(0.5, .4, 0));
//		L.ogSD("Drive 10 feet 0.6speed", new DriveForDistanceManual(10.0, 0.60, 0));
//		L.ogSD("Drive 10 feet 1speed", new DriveForDistanceManual(10.0, 1.0, 0));
//		L.ogSD("Spin Flys Auto", new SpinFlys((3125)));
//		L.ogSD("Spin Hopper Index (5' back)", new NEWSpinHopperIndex5InchesBack());
//		L.ogSD("Drive -.5", new DriveForDistanceManual(0.5, -.4, 0));
				
//		L.ogSD("low gear", new ShiftLowGear());
	
//		L.ogSD("", new RotateToADegreeCounterClockwiseThrough180(angle, speed));
	
		
		
		/***
		 * AUTONOMOUSES
		 */
		L.ogSD("AUTO RED: Side Gear Shoot", new SideGearShootRed());
		L.ogSD("AUTO BLUE: Side Gear Shoot", new SideGearShootBlue());
		L.ogSD("AUTO RED: Hopper Shoot", new AutoHopperShootRed());
		L.ogSD("AUTO BLUE: Hopper Shoot", new AutoHopperShootBlue());

		L.ogSD("AUTO BLUE: NEW Hopper Shoot", new NEWTestHopperShootBlue());
		L.ogSD("AUTO RED: NEW Hopper Shoot", new NEWTestHopperShootRed());
		L.ogSD("AUTO: Front gear", new AutoFrontGear());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		this.log();
		
//		Command stopFlywheels = new StopFlys();
//		Scheduler.getInstance().add(stopFlywheels);
//		stopFlywheels.start();

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
		autonomousCommand = new AutonomousSelector(chooser.getSelected(), alliance.getSelected());

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		autoTimer.reset();
		autoTimer.start();
		autoIteration = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (autoIteration % 7 == 0) { 
			L.og(autoTimer.get() + " || " + this.DriveTrain.getYaw());
		}
		this.log();
		autoIteration++;
	}

	@Override
	public void teleopInit() {
//		Command rumbleAtThirty = new RumbleAtThirty();
//		rumbleAtThirty.start();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	
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
//		Shooter.log();
		Intake.log();
		HopperBelt.log();
		double t = time.get();
		L.ogSD("loop time", t - lastTime);
		lastTime = t;

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
