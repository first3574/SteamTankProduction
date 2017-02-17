package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;
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
	// here. Call these from Commands.
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
	
	public DriveTrain () {
		
//		left1.changeControlMode(CANTalon.TalonControlMode.Speed);
//		left1.configEncoderCodesPerRev(codesPerRev);
//		left1.setPID(5, 0, 0);
		left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(0);
		
//		right1.changeControlMode(CANTalon.TalonControlMode.Speed);
//		right1.configEncoderCodesPerRev(codesPerRev);
//		right1.setPID(5, 0, 0);
		right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.set(2);
		
		
		ahrs = new AHRS(I2C.Port.kOnboard);
		
		if (ahrs != null) {
			LiveWindow.addSensor("AHRS", "Gyro", ahrs);
		}
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoy());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	//NAVX FUNCTIONS  HI!
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
	
	
	//DRIVE SYSTEMS
	public void driveCheesy(double throttle, double turnValue) {
		left1.set((throttle + turnValue) * driveOtherWay);
		right1.set((throttle - turnValue) * driveOtherWay);
	}
	
	public void driveTank(double left, double right) {
		left1.set(left * driveOtherWay);
		right1.set(right * driveOtherWay);
	}
	
	public void drivePID(double speed) {
		left1.set(speed);
		right1.set(speed);
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
	
	public void log() {
		L.ogSD("Left Encoder", getLeftEnc());
		L.ogSD("Right Encoder", getRightEnc());
		//L.ogSD("Yaw", getYaw());
		//L.og("Yaw" + getYaw());
		//L.og(ahrs.isMoving());
		//L.og("FWV " + ahrs.getFirmwareVersion());
	}
	
}
