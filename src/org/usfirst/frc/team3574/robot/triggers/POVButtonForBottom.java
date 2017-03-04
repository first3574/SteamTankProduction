package org.usfirst.frc.team3574.robot.triggers;

import org.usfirst.frc.team3574.robot.util.L;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButtonForBottom extends Button{
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVButtonForBottom(GenericHID joystick, int port) {
        m_joystick = joystick;
        m_POV = port;
	}
	public boolean get() {
        
//		L.og("			POV VALUE: " + m_joystick.getPOV(m_POV));
		
        if (m_joystick.getPOV(m_POV) == 180 || m_joystick.getPOV(m_POV) == 135 || m_joystick.getPOV(m_POV) == 225) {
//        	L.og("LOW true");
			return true;
		} else {
//			L.og("LOW flase");
			return false;
		}
	}
	public static void main(String[] args) {
	}
}