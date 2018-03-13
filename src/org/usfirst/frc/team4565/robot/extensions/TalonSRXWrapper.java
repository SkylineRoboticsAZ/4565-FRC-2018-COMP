package org.usfirst.frc.team4565.robot.extensions;

import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Wraps a TalonSRX object to make it conform to the MotorControllerInterface
 * @author robotics
 *
 */
public class TalonSRXWrapper extends TalonSRX implements MotorControllerInterface  {

	/**
	 * Pass-through constructor
	 * @param deviceNumber The port number for the TalonSRX
	 */
	public TalonSRXWrapper(int deviceNumber) {
		super(deviceNumber);
	}

	/**
	 * Set the power of the TalonSRX
	 * @param power The power of the motor controller
	 */
	@Override
	public void setPower(double power) {
		super.set(ControlMode.PercentOutput, power);
	}
	
	/**
	 * Set rather the motor controller is inverted or not
	 * @param value Rather or not the motor controller is inverted
	 */
	@Override
	public void setInverted(boolean value) {
		super.setInverted(value);
	}
}
