package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleDeviceControl extends Command {

	private OI m_oi;
	private String m_device1, m_device2;
	
    public ToggleDeviceControl(OI oi, String device1, String device2) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_oi = oi;
    	m_device1 = device1;
    	m_device2 = device2;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_oi.isDeviceEnabled(m_device1)) {
    		m_oi.setDeviceEnabled(m_device1, false);
    		m_oi.setDeviceEnabled(m_device2, true);
    	} else {
    		m_oi.setDeviceEnabled(m_device1, true);
    		m_oi.setDeviceEnabled(m_device2, false);
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
