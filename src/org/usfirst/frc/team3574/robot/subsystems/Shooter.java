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
	
	/*
	 * 1700 rpm, 600 minute to ms conversion, 48 4*cpr
	 */
	static final int nativeUnitsPerRotation = 48;
	static final int nativeUnitsPerMeasurementRate = 1700/600 * nativeUnitsPerRotation; // = 136 
	static final double feedForwardGain = nativeUnitsPerRotation/nativeUnitsPerMeasurementRate; // 0.35294117647
	static final double proportionalGain = (.1 * nativeUnitsPerRotation) / 1832;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Shooter() {
//		LiveWindow.;
		
		left1.reverseOutput(false);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		left1.configEncoderCodesPerRev(12); //Needs to change
		
		left1.changeControlMode(CANTalon.TalonControlMode.Speed);
		left1.configNominalOutputVoltage(0, -0);
		left1.configPeakOutputVoltage(12, -12);
        left1.setF(feedForwardGain);
        left1.setP(proportionalGain);
        left1.setI(0.003); 
        left1.setD(0);

//		left1.changeControlMode(CANTalon.TalonControlMode.Speed);

//		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		left2.set(8);


//		right1.reverseOutput(true);
//		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//		right1.changeControlMode(CANTalon.TalonControlMode.Speed);
//		right1.setPID(0, 0, 0);
//		right1.setF(1);
//		right1.configEncoderCodesPerRev(3); //Needs to change
//
//		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		right2.set(10);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void spinUp(double revsPerMinute) {
		left1.set(revsPerMinute);
//		right1.set(revsPerMinute);
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

//		L.ogSD("Shooter Left Velocity", getLeftVelocity());
//		L.ogSD("Shooter Right Velocity", getRightVelocity());
		
//		L.og("Enc: " + left1.getEncPosition());
//		L.og("Speed: " + left1.getSpeed());
//		L.ogSD("output voltage", left1.getOutputVoltage());
//		L.ogSD("left bus", left1.getBusVoltage());
		
//		L.ogSD("left closed error", left1.getClosedLoopError());
	

        System.out.printf("Encoder Speed: %f ,:: Output Voltage: %f \n", left1.getSpeed(), left1.getOutputVoltage());
        
	}
}

