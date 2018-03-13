package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {

	private Claw m_claw;
	
    public OpenClaw(Claw claw) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_claw = claw;
    	
    	requires(claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("OPENING CLAW!");
    	m_claw.openClaw();
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
