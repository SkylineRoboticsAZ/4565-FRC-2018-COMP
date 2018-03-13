package org.usfirst.frc.team4565.robot.extensions;

/**
 * Defines an interface that can be implemented by all motor controllers
 * @author Skyline Robotics - Ian McDonough
 *
 */
public interface MotorControllerInterface {
	
	/**
	 * Set the power of the motor controller
	 * @param power The power of the motor controller
	 */
	void setPower(double power);
	
	/**
	 * Set rather the motor controller is inverted or not
	 * @param value Rather or not the motor controller is inverted
	 */
	void setInverted(boolean value);
}
