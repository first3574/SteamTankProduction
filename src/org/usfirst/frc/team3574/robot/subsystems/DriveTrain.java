package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyArcade;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyTekerz;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithPoof;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




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
	public double driveOtherWay = -1.0;

	public boolean isQuickTurn = false;
	private TalonControlMode controlMode;

	static final int nativeUnitsPerRotation = 48;
	static final int nativeUnitsPerMeasurementRate = 17000/6000 * nativeUnitsPerRotation;
	static final double P_GAIN = (.1 * nativeUnitsPerRotation) / 1832;
	static final double I_GAIN = .003;
	static final double D_GAIN = 0.0;
	static final double F_GAIN = nativeUnitsPerRotation/nativeUnitsPerMeasurementRate;
	static final double SHIFT_SPEED_TOP = 150;
	static final double SHIFT_SPEED_BOTTOM = 190;
	static final int TICKS_PER_FOOT = 3099;
	
	public DriveTrain () {
		
        try {
			/***********************************************************************
			 * navX-MXP:
			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro:
			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
//            ahrs = new AHRS(I2C.Port.kOnboard);
        	ahrs = new AHRS(Port.kUSB);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

		if (ahrs != null) {
			LiveWindow.addSensor("NavX", "Gyro", ahrs);
		}
		
		LiveWindow.addActuator("Drive Train", "left1", left1);
		LiveWindow.addActuator("Drive Train", "left2", left2);
		LiveWindow.addActuator("Drive Train", "right1", right1);
		LiveWindow.addActuator("Drive Train", "right2", right2);
		LiveWindow.addActuator("Drive Train", "shifter", shifter);
		
		left1.reverseOutput(false);
		left1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		left1.reverseSensor(false);
		
//		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		left2.set(left1.getDeviceID());
		left2.reverseOutput(false);
//		left2.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		left2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		left2.reverseSensor(false);
		
		right1.reverseOutput(true);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		right1.reverseSensor(false);
		
//		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
//		right2.set(right1.getDeviceID());
		right2.reverseOutput(true);
//		right2.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		right2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		right2.reverseSensor(false);
		
	}
	
	public void setDriveMode(TalonControlMode DRIVE_MODE){
		this.controlMode = DRIVE_MODE;
		if(DRIVE_MODE == TalonControlMode.Speed){
			
			left1.changeControlMode(CANTalon.TalonControlMode.Speed);
			left1.configNominalOutputVoltage(0, -0);
			left1.configPeakOutputVoltage(12, -12);
	        left1.setP(P_GAIN);
	        left1.setI(I_GAIN); 
	        left1.setD(D_GAIN);
	        left1.setF(F_GAIN);


			right1.changeControlMode(CANTalon.TalonControlMode.Speed);
			right1.configNominalOutputVoltage(0, -0);
			right1.configPeakOutputVoltage(12, -12);
			right1.setP(P_GAIN);
			right1.setI(I_GAIN); 
			right1.setD(D_GAIN);
			right1.setF(F_GAIN);

		} else if (DRIVE_MODE == TalonControlMode.Position){
			
		} else{ //default to VBus
			left1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			left1.enableBrakeMode(true);
			right1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			right1.enableBrakeMode(true);
			left2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			left2.enableBrakeMode(true);
			right2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			right2.enableBrakeMode(true);
		}
	}
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoyTekerz());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	//NAVX FUNCTIONS
	public void resetYaw() {
		ahrs.zeroYaw();
	}
	public void resetAHRS() {
		ahrs.reset();
	}
	
	public double getYaw() {
		if (ahrs != null) {
			return ahrs.getYaw()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	public double getAngle() {
		return (getYaw() + 360) % 360;
	}
	
	public void isStopping() {
		if((left1.getOutputCurrent() > 20.0 && right1.getOutputCurrent() > 20.0) && (ahrs.getVelocityX() >= -0.1 && ahrs.getVelocityX() <= 0.1)) {
			setGearLow();
		}
	}
	
	//SHIFTER FUNCTIONS
	public void setGearHigh() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}

	public boolean getGearIsLow() {
		return (shifter.get() == DoubleSolenoid.Value.kReverse);
	}
	public boolean getGearIsHigh() {
		return (shifter.get() == DoubleSolenoid.Value.kForward);
	}
	
	public void setGearLow() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void resetShifter() {
		this.setGearLow();
	}
	
	private void automaticShifter() {
		
//		if(left1.getSpeed() > SHIFT_SPEED_TOP && right1.getSpeed() < -SHIFT_SPEED_TOP) {
//		
//		L.ogSD("Shifters Running High", true);
//		if(getGearIsLow()) {
//			L.og("Setting To High Gear");
//			setGearHigh();
//		} else if(left1.getSpeed() < -SHIFT_SPEED_TOP && right1.getSpeed() > SHIFT_SPEED_TOP) {
//		
//	L.ogSD("Shifters Running High", true);
//	if(getGearIsLow()) {
//		L.og("Setting To High Gear");
//		setGearHigh();
//	}
//	
//	} else {
//		L.ogSD("Shifters Running High", false);
//		if(getGearIsHigh())	{
//			L.og("Setting To Low Gear");
//			setGearLow();
//		}
//		
//	}
		
		if(Math.abs(left1.getSpeed()) > SHIFT_SPEED_TOP && Math.abs(right1.getSpeed()) > SHIFT_SPEED_TOP) {
			
			L.ogSD("Shifters Running High", true);
			if(getGearIsLow()) {
				L.og("Setting To High Gear");
				setGearHigh();
			}
			
		} else //if(Math.abs(left1.getSpeed()) < SHIFT_SPEED_BOTTOM || Math.abs(right1.getSpeed()) < SHIFT_SPEED_BOTTOM) 
			{
			L.ogSD("Shifters Running High", false);
			if(getGearIsHigh())	{
				L.og("Setting To Low Gear");
				setGearLow();
			}
			
		}
		// if in low gear and going high speed, shift up
		// if in high gear and going low speed, shift down
	}
	
	public void toggleShift() {
		if(getGearIsLow()) {
			setGearHigh();
		} else if(getGearIsLow()) {
			setGearLow();
		} else {
			/**
			 * If the code has gotten here, the robot has already blown up.
			 */
			resetShifter();
		}
	}
	
	//DRIVE SYSTEMS
	@Deprecated
	public void driveArcade(double throttle, double turnValue) {
		left1.set((throttle + turnValue) * driveOtherWay);
		left2.set((throttle + turnValue) * driveOtherWay);
		right1.set((throttle - turnValue) * driveOtherWay);
		right2.set((throttle - turnValue) * driveOtherWay);

	}
	
	public void driveTekerz(double turn, double throttle) {
		double turnMod = Math.max(Math.abs(left1.getSpeed()), Math.abs(right1.getSpeed()));
		turnMod  *= (.00035 * 2);
		turn *= (1 - turnMod);
		
		L.ogSD("Turn Mod", turnMod);
		L.ogSD("Turn", turn);
		L.ogSD("Throttle", throttle);
		
//		turn *= 12.5;
//		throttle *= 12.5;
		
		left1.set((turn + throttle) * driveOtherWay);
		left2.set((turn + throttle) * driveOtherWay);
		right1.set((turn - throttle) * driveOtherWay);
		right2.set((turn - throttle) * driveOtherWay);
//		automaticShifter();
//		isStopping();
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
		return -right1.getEncPosition();
	}

	public double getLeftVolt(){
		return left1.getOutputVoltage();
//		Meme
	}
	
	public double getRightVolt(){
		return right1.getOutputVoltage();
	}
	
	public void enableBrakeMode() {
		left1.enableBrakeMode(true);
		left2.enableBrakeMode(true);
		right1.enableBrakeMode(true);
		right2.enableBrakeMode(true);
	}

	public void disableBrakeMode() {
		left1.enableBrakeMode(false);
		left2.enableBrakeMode(false);
		right1.enableBrakeMode(false);
		right2.enableBrakeMode(false);
	}
	
	public void log() {		
//		L.ogSD("Compressor Switch" ,Boolean.toString(( new Compressor()).getPressureSwitchValue()));
//		L.ogSDTalonBasics("Drive Left", left1);
//		L.ogSDTalonBasics("Drive Right", right1);
//		L.ogSDTalonPID("Drive Left", left1);
//		L.ogSDTalonPID("Drive Right", right1);
//        SmartDashboard.putBoolean("IMU IsCalibrating", ahrs.isCalibrating());
//		L.ogSD("Drive Left Enc", getLeftEnc());
//		L.ogSD("Drive Right Enc", getRightEnc());
		
//		L.ogSD("AHRS rate", ahrs.getRate());
//		L.ogSD("Robot Velocity X", ahrs.);
//		L.ogSD("Robot Velocity Y", ahrs.getVelocityY());
//		L.ogSD("Robot Velocity Z", ahrs.getVelocityZ());
		
		
		L.ogSD("Yaw", this.getYaw());
		L.ogSD("Left Current", left1.getOutputCurrent());
		L.ogSD("Right Current", right1.getOutputCurrent());
		L.ogSD("left drive enc", left1.getEncPosition());
		L.ogSD("right drive enc", left2.getEncPosition());
//        L.ogSD("Angle", getAngle());
////		SmartDashboard.putBoolean("I AM YOU!?_IsConnected", ahrs.isConnected());
//        
//        L.ogSD("Left Enc Speed", left1.getSpeed());
//        L.ogSD("Right Enc Speed", right1.getSpeed());
//        
//        
//        L.ogSD("Drive left current1", left1.getOutputCurrent());
//        L.ogSD("Drive left current2", left2.getOutputCurrent());
//        L.ogSD("Drive right current1", right1.getOutputCurrent());
//        L.ogSD("Drive right current2", right2.getOutputCurrent());
		
	}
}