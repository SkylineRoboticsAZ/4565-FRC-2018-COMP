package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ClawPitchPulse extends Command {

	private Claw m_claw;
	private Timer m_timer;
	private boolean m_reverse;
	
	public ClawPitchPulse(Claw claw) {
		this(claw, false);
	}
	
    public ClawPitchPulse(Claw claw, boolean reverse) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_claw = claw;
    	m_timer = new Timer();
    	m_reverse = reverse;
    	
    	requires(claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_claw.setPitchMotorPower(m_reverse ? RobotMap.autoClawPitchPulsePower : -RobotMap.autoClawPitchPulsePower);
    	m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return m_timer.hasPeriodPassed(RobotMap.autoClawPitchPulseTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_claw.setPitchMotorPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	m_timer.stop();
    	end();
    }
}
