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

import edu.wpi.first.wpilibj.Compressor;
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
	public static final int DRIVE_MODE_VBAT = 0;
	public static final int DRIVE_MODE_PID_SPEED = 1;
	public static final int DRIVE_MODE_PID_POSITION = 2;
	
	
	static final int nativeUnitsPerRotation = 48;
	static final int nativeUnitsPerMeasurementRate = 17000/6000 * nativeUnitsPerRotation; // = 136 
	static final double FEED_FORWARD_GAIN = nativeUnitsPerRotation/nativeUnitsPerMeasurementRate; // 0.35294117647
	static final double PROPORTIONAL_GAIN = (.1 * nativeUnitsPerRotation) / 1832;
	static final double INTEGRAL_GAIN = .003;
	
	public DriveTrain () {
		
		this.changeMode(DRIVE_MODE_VBAT);
		
		ahrs = new AHRS(I2C.Port.kOnboard);
		
		if (ahrs != null) {
			LiveWindow.addSensor("AHRS", "Gyro", ahrs);
		}
		LiveWindow.addActuator("Drive Train", "left1", left1);
		LiveWindow.addActuator("Drive Train", "left2", left2);
		LiveWindow.addActuator("Drive Train", "right1", right1);
		LiveWindow.addActuator("Drive Train", "right2", right2);
	}
	
	public void changeMode(int DRIVE_MODE_CONSTANT){
		if(DRIVE_MODE_CONSTANT == this.DRIVE_MODE_PID_SPEED){
			
			left1.reverseOutput(false);
			left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
			left1.reverseSensor(false);
			left1.configEncoderCodesPerRev(12); //Needs to change
			
			left1.changeControlMode(CANTalon.TalonControlMode.Speed);
			left1.configNominalOutputVoltage(0, -0);
			left1.configPeakOutputVoltage(12, -12);
	        left1.setF(1.7);
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
			right1.setF(1.7);
			right1.setP(PROPORTIONAL_GAIN);
			right1.setI(INTEGRAL_GAIN); 
			right1.setD(0);

			right2.changeControlMode(CANTalon.TalonControlMode.Follower);
			right2.set(right1.getDeviceID());
			
		} else if (DRIVE_MODE_CONSTANT == this.DRIVE_MODE_PID_POSITION){
			
		} else{ //default to VBus
			left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			left2.changeControlMode(CANTalon.TalonControlMode.Follower);
			left2.set(left1.getDeviceID());
			
			right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
			right2.set(right1.getDeviceID());}
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
		right1.set(rightWheelValue);
	}
	
	public void driveArcade(double throttle, double turnValue) {
		left1.set(throttle + turnValue);
		right1.set(throttle - turnValue);
	
	}
	
	public void driveTank(double left, double right) {
		left1.set(left * driveOtherWay);
		right1.set(right * driveOtherWay);
	}
	
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
		L.ogSD("Drive Left Encoder", getLeftEnc());
		L.ogSD("Drive Right Encoder", getRightEnc());

//		L.ogSD("Drive Yaw", getYaw());
		L.ogSD("Yaw" , getYaw());

//		L.ogSD("Drive left set", left1.get());
//		L.ogSD("Drive right set", right1.get());

//		L.ogSD("Drive leftCurrent1", left1.getOutputCurrent());
//		L.ogSD("Drive leftCurrent2", left2.getOutputCurrent());
//		L.ogSD("Drive rightCurrent1", right1.getOutputCurrent());
//		L.ogSD("Drive rightCurrent2", right2.getOutputCurrent());
		
		L.ogSD("Compresser Switch" ,Boolean.toString(( new Compressor()).getPressureSwitchValue()));
	}
}
