package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	CANTalon left1 = RobotMap.motorFlyLeft1;
	CANTalon left2 = RobotMap.motorFlyLeft2;

	CANTalon right1 = RobotMap.motorFlyRight1;
	CANTalon right2 = RobotMap.motorFlyRight2;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Shooter() {
//		LiveWindow.;
		
		left1.reverseOutput(false);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		left1.changeControlMode(CANTalon.TalonControlMode.Speed);
		left1.configEncoderCodesPerRev(100); //Needs to change
		left1.configNominalOutputVoltage(0, 0);
		left1.configPeakOutputVoltage(12, -12);
		
		left1.setPID(0, 0, 0);
		left1.setF(20);
		
//		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		left2.set(8);


		right1.reverseOutput(true);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		right1.changeControlMode(CANTalon.TalonControlMode.Speed);
		right1.setPID(0, 0, 0);
		right1.setF(0);
//		right1.configEncoderCodesPerRev(3); //Needs to change

//		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		right2.set(10);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void spinUp(double revsPerSecond) {
		left1.set(revsPerSecond);
		right1.set(revsPerSecond);
	}

	public void stop() {
		left1.set(0);
		right1.set(0);
	}
	
	public int getLeftEnc() {
		return left1.getEncPosition();
	}
	
	public int getRightEnc() {
		return right1.getEncPosition();
	}

	public int getLeftVelocity() {
		return left1.getEncVelocity();
	}
	
	public int getRightVelocity() {
		return right1.getEncVelocity();
	}
	
	public void log() {
		L.ogSD("Shooter Left Enc", getLeftEnc());
		L.ogSD("Shooter Right Enc", getRightEnc());

		L.ogSD("Shooter Left Velocity", getLeftVelocity());
		L.ogSD("Shooter Right Velocity", getRightVelocity());
	}
}

