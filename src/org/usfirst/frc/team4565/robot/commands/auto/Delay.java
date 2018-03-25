package org.usfirst.frc.team4565.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class Delay extends Command {

	private Timer m_timer;
	private double m_period;
	
    public Delay(double period) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_timer = new Timer();
    	m_period = period;
    	
    	setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_timer.hasPeriodPassed(m_period);
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
