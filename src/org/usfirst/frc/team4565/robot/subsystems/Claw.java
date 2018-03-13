package org.usfirst.frc.team4565.robot.subsystems;

import org.usfirst.frc.team4565.robot.commands.claw.TeleopClawPitchControl;
import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Controls a claw consisting of a motor for pitch control
 * and a double solenoid for controlling the claw itself
 * @author Skyline Robotics - Ian McDonough
 *
 */
public class Claw extends Subsystem {

	private MotorControllerInterface m_pitchMotor;
	private DoubleSolenoid m_clawCylinder;
	private int m_controllerAxis;

	/**
	 * Constructs a new Claw subsystem object
	 * @param pitchMotor the motor to use for pitch control
	 * @param clawCylinder the solenoid to use for the claw
	 */
	public Claw(MotorControllerInterface pitchMotor, DoubleSolenoid clawCylinder, int controllerAxis) {
		m_pitchMotor = pitchMotor;
		m_clawCylinder = clawCylinder;
		m_controllerAxis = controllerAxis;
		
		//Default solenoid position
		closeClaw();
	}

	/**
	 * Open the claw
	 */
	public void openClaw() {
		m_clawCylinder.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Close the claw
	 */
	public void closeClaw() {
		m_clawCylinder.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * Get the status of the claw
	 * @return true if the claw is open, false otherwise
	 */
	public boolean isClawOpen() {
		return m_clawCylinder.get() == DoubleSolenoid.Value.kReverse;
	}

	public void setPitchMotorPower(double power) {
		m_pitchMotor.setPower(power);
	}

	/**
	 * Initialize the default command for the subsystem
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopClawPitchControl(this, m_controllerAxis));
    }
}

