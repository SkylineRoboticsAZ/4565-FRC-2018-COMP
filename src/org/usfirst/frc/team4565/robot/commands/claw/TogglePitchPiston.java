package org.usfirst.frc.team4565.robot.commands.claw;

import org.usfirst.frc.team4565.robot.Robot;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles the opened/closed state of a Claw object
 * @author Skyline Robotics - Ian McDonough
 *
 */
public class TogglePitchPiston extends Command {

	private ScaleClaw m_scaleClaw;
	
	/**
	 * Constructs a new ToggleClaw command object
	 * @param claw The Claw object that will be modified
	 */
    public TogglePitchPiston(ScaleClaw claw) {
    	m_scaleClaw = claw;
    	
    	requires(claw);
    }

    /**
     * Toggles the state of the claw
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.kOi.isDeviceEnabled(m_scaleClaw.getName())) {
        	if (m_scaleClaw.isPitchPistonExtended())
        		m_scaleClaw.retractPitchPiston();
        	else
        		m_scaleClaw.extendPitchPiston();
    	}
    }

    /**
     * Does nothing
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    /**
     * Return true when the command is done being executed
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Check if we are done toggling or not
        return true;
    }

    /**
     * Do nothing
     */
    // Called once after isFinished returns true
    protected void end() {
    }

    /**
     * Do nothing
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
