package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.OI;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCorrectTopClaw extends Command {

	private OI m_oi;
	private ScaleClaw m_scaleClaw;
	
    public AutoCorrectTopClaw(OI oi, ScaleClaw scaleClaw) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_oi = oi;
    	m_scaleClaw = scaleClaw;
    	
    	requires(scaleClaw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_oi.isDeviceEnabled(m_scaleClaw.getName())) {
    		m_scaleClaw.extendPitchPiston();
    		m_scaleClaw.openClaw();
    	} else {
    		m_scaleClaw.retractPitchPiston();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
