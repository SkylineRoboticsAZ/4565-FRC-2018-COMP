package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisableDevice extends Command {

	private OI m_oi;
	private String m_name;
	
    public DisableDevice(OI oi, String name) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_oi = oi;
    	m_name = name;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_oi.setDeviceEnabled(m_name, false);
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
