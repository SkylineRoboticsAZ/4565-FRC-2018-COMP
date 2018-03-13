package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleTopClawWinchArm extends Command {

	private OI m_oi;
	private String m_topClawName, m_winchArmName;
	
    public ToggleTopClawWinchArm(OI oi, String topClawName, String winchArmName) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_oi = oi;
    	m_topClawName = topClawName;
    	m_winchArmName = winchArmName;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_oi.isDeviceEnabled(m_topClawName)) {
    		m_oi.setDeviceEnabled(m_topClawName, false);
    		m_oi.setDeviceEnabled(m_winchArmName, true);
    	} else {
    		m_oi.setDeviceEnabled(m_topClawName, true);
    		m_oi.setDeviceEnabled(m_winchArmName, false);
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
