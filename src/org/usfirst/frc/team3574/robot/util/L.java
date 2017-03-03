package org.usfirst.frc.team3574.robot.util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class L {
	/**
	 * Print to the Console.
	 * @param s
	 */
	public static void og(String s) {
		System.out.println(s);
	}
	/**
	 * Print to the Console.
	 * @param d
	 */
	public static void og(double d) {
		System.out.println(d);
	}
	/**
	 * Print to the Console.
	 * @param o
	 */
	public static void og(Object o) {
		System.out.println(o);
	}
	
	
	/**
	 * For debugging purposes, place in 'initialize' within a command. 
	 * @param c
	 */
	public static void ogCmdInit(Command c) {
		System.out.println("- INIT ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'initialize' within a command.
	 * @param c
	 * @param s
	 */
	public static void ogCmdInit(Command c, String s) {
		System.out.println("- INIT ------- " + c.getName() + ", s");
	}
	/**
	 * For debugging purposes, place in 'execute' within a command.
	 * @param c
	 */
	public static void ogCmdExec(Command c) {
		System.out.println("- Exec ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'end' within a command.
	 * @param c
	 */
	public static void ogCmdEnd(Command c) {
		System.out.println("-- END ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'interrupted' within a command.
	 * @param c
	 */
	public static void ogCmdInterrupted(Command c) {
		System.out.println("-- INTERRUPT - " + c.getName());
	}
	
	/**
	 * Put something on the SmartDashboard. (both integers and doubles.)
	 * @param key
	 * @param value
	 */
	public static void ogSD(String key, double value) {
		SmartDashboard.putNumber(key, value);
	}
	/**
	 * Put something on the SmartDashboard.
	 * @param key
	 * @param value
	 */
	public static void ogSD(String key, String value) {
		SmartDashboard.putString(key, value);
	}
	/**
	 * Put something on the SmartDashboard.
	 * @param key
	 * @param data
	 */
	public static void ogSD(String key, Sendable data) {
		SmartDashboard.putData(key, data);
	}
	
	public static void ogSDTalonBasics(String baseKey, CANTalon talon) {
		SmartDashboard.putNumber(baseKey + " Current", talon.getOutputCurrent());
		SmartDashboard.putNumber(baseKey + " Voltage", talon.getOutputVoltage());
		SmartDashboard.putNumber(baseKey + " Enc Pos", talon.getEncPosition());
	}
	
	public static void ogSDTalonPID(String baseKey, CANTalon talon) {
		SmartDashboard.putNumber(baseKey + " Enc Vel", talon.getEncVelocity());
		SmartDashboard.putNumber(baseKey + " Error", talon.getError());
		SmartDashboard.putNumber(baseKey + " Setpoint", talon.getSetpoint());
		SmartDashboard.putNumber(baseKey + " Speed", talon.getSpeed());
		SmartDashboard.putNumber(baseKey + " Position", talon.getPosition());
		SmartDashboard.putNumber(baseKey + " Closed Loop Error", talon.getClosedLoopError());
		SmartDashboard.putNumber(baseKey + " I Accum", talon.GetIaccum());
	}
}