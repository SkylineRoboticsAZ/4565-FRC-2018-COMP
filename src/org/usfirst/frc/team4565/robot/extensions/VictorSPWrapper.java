package org.usfirst.frc.team4565.robot.extensions;

import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Wraps a VictorSP object to make it conform to the MotorControllerInterface
 * @author robotics
 *
 */
public class VictorSPWrapper extends VictorSP implements MotorControllerInterface {
	
	/**
	 * Pass-through constructor
	 * @param deviceNumber The port number for the TalonSRX
	 */
	public VictorSPWrapper(int channel) {
		super(channel);
	}

	/**
	 * Set the power of the Victor
	 * @param power The power of the motor controller
	 */
	@Override
	public void setPower(double power) {
		super.set(power);
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
