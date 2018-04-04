package org.usfirst.frc.team4565.robot.subsystems;

import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ScaleClaw extends Claw {

	private DoubleSolenoid m_positionCylinder;
	  
	public ScaleClaw(MotorControllerInterface pitchMotor, DoubleSolenoid clawCylinder, DoubleSolenoid pitchPiston) {
		super(pitchMotor, clawCylinder);
		
		m_positionCylinder = pitchPiston;
	}
	
	public void extendPitchPiston() {
		m_positionCylinder.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void retractPitchPiston() {
		m_positionCylinder.set(DoubleSolenoid.Value.kForward);
	}
	
	public boolean isPitchPistonExtended() {
		return m_positionCylinder.get() == DoubleSolenoid.Value.kReverse;
	}
}
