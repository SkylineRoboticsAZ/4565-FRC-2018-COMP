package org.usfirst.frc.team4565.robot.commands.winch;

import org.usfirst.frc.team4565.robot.Robot;
import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopWinchControl extends Command {

    private Winch m_winch;
    private boolean m_inverted;

    public TeleopWinchControl(Winch winch, boolean invert) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_winch = winch;
        m_inverted = invert;

        requires(winch);
    }
    
    public TeleopWinchControl(Winch winch) {
    	this(winch, false);
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.kOi.isDeviceEnabled(m_winch.getName()))
    		m_winch.setWinchPower(m_inverted ? -RobotMap.winchPower : RobotMap.winchPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        m_winch.setWinchPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
