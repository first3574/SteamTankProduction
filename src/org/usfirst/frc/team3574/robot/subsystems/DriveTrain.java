package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.DriveWithJoy;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon left1 = RobotMap.motorLeftDrive1;
	CANTalon left2 = RobotMap.motorleftDrive2;
	CANTalon right1 = RobotMap.motorRightDrive1;
	CANTalon right2 = RobotMap.motorRightDrive2;
	DoubleSolenoid shifter = RobotMap.shifterDrive;
	
	public DriveTrain (){
		left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(0);
		
		right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.set(2);
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoy());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void cheesyDrive(double throttle, double turnValue) {
		left1.set((throttle + turnValue));
		right1.set((throttle - turnValue));
//		System.out.println(ahrs.getAngle());
	}
	
	
	public void highGear() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void lowGear() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void resetShifter() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
}
