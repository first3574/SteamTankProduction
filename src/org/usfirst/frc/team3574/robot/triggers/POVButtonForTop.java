package org.usfirst.frc.team3574.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButtonForTop extends Button{
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVButtonForTop(GenericHID joystick, int port) {
        m_joystick = joystick;
        m_POV = port;
	}
	public boolean get() {
        
//		L.og("			POV VALUE: " + m_joystick.getPOV(m_POV));
		
		if (m_joystick.getPOV(m_POV) == 0 || m_joystick.getPOV(m_POV) == 45 || m_joystick.getPOV(m_POV) == 315) {
//			L.og("HIGH true");
			
			return true;
		} else {
//			L.og("HIGH false");
			
			return false;
		}
	}
	public static void main(String[] args) {
	}
}