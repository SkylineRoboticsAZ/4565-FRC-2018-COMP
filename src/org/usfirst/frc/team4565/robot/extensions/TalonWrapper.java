package org.usfirst.frc.team4565.robot.extensions;

import edu.wpi.first.wpilibj.Talon;

public class TalonWrapper extends Talon implements MotorControllerInterface {

	public TalonWrapper(int channel) {
		super(channel);
	}

	@Override
	public void setPower(double power) {
		super.set(power);
	}

	@Override
	public void setInverted(boolean value) {
		super.setInverted(value);
	}
}
