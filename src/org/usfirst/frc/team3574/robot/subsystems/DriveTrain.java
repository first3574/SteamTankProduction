package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyArcade;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoyTekerz;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithPoof;
import org.usfirst.frc.team3574.robot.util.L;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
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
	public double driveOtherWay = 1.0;

	public boolean isQuickTurn = false;
	private TalonControlMode controlMode;

	static final int nativeUnitsPerRotation = 48;
	static final int nativeUnitsPerMeasurementRate = 17000/6000 * nativeUnitsPerRotation;
	static final double P_GAIN = (.1 * nativeUnitsPerRotation) / 1832;
	static final double I_GAIN = .003;
	static final double D_GAIN = 0.0;
	static final double F_GAIN = nativeUnitsPerRotation/nativeUnitsPerMeasurementRate;
	static final double SHIFT_SPEED = 50;
	
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
            ahrs = new AHRS(I2C.Port.kOnboard);
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
		left1.reverseSensor(false);
		
		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(left1.getDeviceID());

		right1.reverseOutput(true);
		right1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		right1.reverseSensor(true);
		
		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.set(right1.getDeviceID());
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
	
	public double getYaw() {
		if (ahrs != null) {
			return ahrs.getYaw()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	//SHIFTER FUNCTIONS
	public void setGearHigh() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}

	public boolean getGearIsLow() {
		return (shifter.get() == DoubleSolenoid.Value.kReverse);
	}
	
	public void setGearLow() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void resetShifter() {
		this.setGearLow();
	}
	
	private void automaticShifter() {
		if(left1.getSpeed() > SHIFT_SPEED && right1.getSpeed() > SHIFT_SPEED)
		{
			if(getGearIsLow())
			{
				setGearHigh();
			}
		}
		else if(left1.getSpeed() < SHIFT_SPEED && right1.getSpeed() < SHIFT_SPEED)
		{
			if(!getGearIsLow())
			{
				setGearLow();
			}
		}
		// if low gear and going high speed, shift up
		// if high gear and going low speed, shift down
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
	public void driveArcade(double throttle, double turnValue) {
		left1.set((throttle + turnValue) * driveOtherWay);
		right1.set((throttle - turnValue) * driveOtherWay);
	}
	
	public void driveTekerz(double throttle, double turnValue) {
		left1.set((throttle + turnValue) * driveOtherWay);
		right1.set((throttle - turnValue) * driveOtherWay);
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
//		Meme
	}
	
	public double getRightVolt(){
		return right1.getOutputVoltage();
	}

	public void log() {		
		L.ogSD("Compresser Switch" ,Boolean.toString(( new Compressor()).getPressureSwitchValue()));
		L.ogSDTalonBasics("Drive Left", left1);
		L.ogSDTalonBasics("Drive Right", right1);
		L.ogSDTalonPID("Drive Left", left1);
		L.ogSDTalonPID("Drive Right", right1);
        SmartDashboard.putBoolean(  "I AM YOU!?_IsCalibrating",    ahrs.isCalibrating());
        SmartDashboard.putNumber(   "I AM YOU!?_Yaw",              ahrs.getYaw());
		SmartDashboard.putBoolean("I AM YOU!?_IsConnected", ahrs.isConnected());
	}
}