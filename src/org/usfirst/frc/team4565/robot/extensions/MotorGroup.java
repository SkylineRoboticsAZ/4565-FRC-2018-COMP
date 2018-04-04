package org.usfirst.frc.team4565.robot.extensions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MotorGroup implements MotorControllerInterface {

	private List<MotorControllerInterface> m_motors;
	
	public MotorGroup() {
		m_motors = new LinkedList<MotorControllerInterface>();
	}
	
	public void addMotor(MotorControllerInterface motor) {
		m_motors.add(motor);
	}
	
	@Override
	public void setPower(double power) {
    	//Go through the list of motors and set the power on each one
    	Iterator<MotorControllerInterface> i = m_motors.iterator();
    	while (i.hasNext()) {
    		MotorControllerInterface motor = i.next();
    		motor.setPower(power);
    	}
	}

	@Override
	public void setInverted(boolean value) {
    	Iterator<MotorControllerInterface> i = m_motors.iterator();
    	while (i.hasNext()) {
    		MotorControllerInterface motor = i.next();
    		motor.setInverted(value);
    	}
	}
}
