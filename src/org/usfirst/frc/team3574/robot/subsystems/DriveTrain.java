package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithPoof;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands
	CANTalon left1 = RobotMap.motorDriveLeft1;
	CANTalon left2 = RobotMap.motorDriveleft2;
	CANTalon right1 = RobotMap.motorDriveRight1;
	CANTalon right2 = RobotMap.motorDriveRight2;
	DoubleSolenoid shifter = RobotMap.driveShifter;
	AHRS ahrs;
	
	/**
	 * A command/system can multiply this number by -1 and reverse the output of the drive motors.
	 */
	public int driveOtherWay = 1;
	public boolean isQuickTurn = false;
	public boolean isHighGear = true;

	
	public DriveTrain () {
		
//		left1.changeControlMode(CANTalon.TalonControlMode.Speed);
//		left1.configEncoderCodesPerRev(codesPerRev);
//		left1.setPID(5, 0, 0);
		left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

//		left2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);		
		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(left1.getDeviceID());
		
//		right1.changeControlMode(CANTalon.TalonControlMode.Speed);
//		right1.configEncoderCodesPerRev(codesPerRev);
//		right1.setPID(5, 0, 0);
		right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

//		right2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);		
    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.set(right1.getDeviceID());
		
		
		ahrs = new AHRS(I2C.Port.kOnboard);
		
		if (ahrs != null) {
			LiveWindow.addSensor("AHRS", "Gyro", ahrs);
		}
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithPoof());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	//NAVX FUNCTIONS
	public void resetYaw() {
		ahrs.zeroYaw();
	}
	
	public double getYaw() {
		if (ahrs != null) {
			return ahrs.getYaw()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	//SHIFTER FUNCTIONS
	public void highGear() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void lowGear() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void resetShifter() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void toggleShift() {
		if(isHighGear) {
			lowGear();
			isHighGear = false;
		} else if(!isHighGear) {
			highGear();
			isHighGear = true;
		} else {
			/**
			 * If the code has gotten here, the robot has already blown up.
			 */
			resetShifter();
		}
	}
	
	
	//DRIVE SYSTEMS
	public void driveCheesy(double rightWheelValue, double leftWheelValue) {
		left1.set(leftWheelValue);
//		left2.set(leftWheelValue);
		right1.set(rightWheelValue);
//		right2.set(rightWheelValue);
	}
	
	public void driveArcade(double throttle, double turnValue) {
		left1.set(throttle + turnValue);
		right1.set(throttle - turnValue);
	
	}
	
	public void driveTank(double left, double right) {
		left1.set(left * driveOtherWay);
		right1.set(right * driveOtherWay);
	}
	
//	public void drivePID(double speed) {
//		left1.set(speed);
//		right1.set(speed);
//	}
	
	//ENCODERS
	public void resetEncoders() {
		left1.setEncPosition(0);
		right1.setEncPosition(0);
	}
	public int getLeftEnc() {
		return left1.getEncPosition();
	}
	public int getRightEnc() {
		return right1.getEncPosition();
	}
	
	public double getLeftVolt(){
		return left1.getOutputVoltage();
	}
	public double getRightVolt(){
		return right1.getOutputVoltage();
	}
	
	public void log() {
		L.ogSD("Left Encoder", getLeftEnc());
		L.ogSD("Right Encoder", getRightEnc());
		L.ogSD("Yaw", getYaw());
//		L.og("Yaw " + getYaw());
		//L.og(ahrs.isMoving());
		//L.og("FWV " + ahrs.getFirmwareVersion());
		L.ogSD("leftCurrent1", left1.getOutputCurrent());
		L.ogSD("leftCurrent2", left2.getOutputCurrent());
		L.ogSD("rightCurrent1", right1.getOutputCurrent());
		L.ogSD("rightCurrent2", right2.getOutputCurrent());
//		L.ogSD("leftV", left1.getOutputVoltage());
		}
	
}
