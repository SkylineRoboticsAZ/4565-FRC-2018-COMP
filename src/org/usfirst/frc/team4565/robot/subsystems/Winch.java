package org.usfirst.frc.team4565.robot.subsystems;

import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {

    private MotorControllerInterface m_winchMotor;

	public Winch(MotorControllerInterface winchMotor) {
	    m_winchMotor = winchMotor;
	}

	public void setWinchPower(double power) {
	    m_winchMotor.setPower(power);
    }

	/**
	 * Initialize the default command for the subsystem
	 */
    public void initDefaultCommand() {
    }
}

