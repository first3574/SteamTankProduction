package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.TalonSRX;
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
	
	/*
	 * 1700 rpm, 600 minute to ms conversion, 48 4*cpr
	 */
	static final int nativeUnitsPerRotation = 48;
	static final int nativeUnitsPerMeasurementRate = 18730/6000 * nativeUnitsPerRotation; // = 136 
	static final double FEED_FORWARD_GAIN = nativeUnitsPerRotation/nativeUnitsPerMeasurementRate; // 0.35294117647
	static final double PROPORTIONAL_GAIN = (.1 * nativeUnitsPerRotation) / 1832;
	static final double INTEGRAL_GAIN = .003;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Shooter() {
//		LiveWindow.;
		
		left1.reverseOutput(false);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		left1.reverseSensor(false);
		left1.configEncoderCodesPerRev(12); //Needs to change
		
		left1.changeControlMode(CANTalon.TalonControlMode.Speed);
		left1.configNominalOutputVoltage(0, -0);
		left1.configPeakOutputVoltage(12, -12);
//        left1.setF(FEED_FORWARD_GAIN);
		left1.setF(1023/245);
        left1.setP(PROPORTIONAL_GAIN);
        left1.setI(INTEGRAL_GAIN); 
        left1.setD(0);

		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(left1.getDeviceID());


		right1.reverseOutput(true);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		right1.reverseSensor(true);
		right1.configEncoderCodesPerRev(12); //Needs to change
		
		right1.changeControlMode(CANTalon.TalonControlMode.Speed);
		right1.configNominalOutputVoltage(0, -0);
		right1.configPeakOutputVoltage(12, -12);
//		right1.setF(FEED_FORWARD_GAIN);
		right1.setF(1023/245);
		right1.setP(PROPORTIONAL_GAIN);
		right1.setI(INTEGRAL_GAIN); 
		right1.setD(0);

		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.set(right1.getDeviceID());
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void spinUp(double revsPerMinute) {
		left1.set(revsPerMinute);
		right1.set(revsPerMinute);
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
		L.ogSDTalonBasics("Shooter Left", left1);
		L.ogSDTalonBasics("Shooter Right", right1);
		L.ogSDTalonPID("Shooter Left", left1);
		L.ogSDTalonPID("Shooter Right", right1);
	}
}
