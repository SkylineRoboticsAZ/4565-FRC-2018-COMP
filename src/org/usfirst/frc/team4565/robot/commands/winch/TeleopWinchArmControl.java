package org.usfirst.frc.team4565.robot.commands.winch;

import org.usfirst.frc.team4565.robot.Robot;
import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopWinchArmControl extends Command {

    private WinchArm m_winchArm;

    public TeleopWinchArmControl(WinchArm winchArm) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_winchArm = winchArm;

        requires(winchArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.kOi.isDeviceEnabled(m_winchArm.getName())) {
        	XboxController controller = Robot.kOi.getSecondaryController();
    		double value = controller.getRawAxis(5);
    		
    		if (checkDeadband(value, RobotMap.winchArmDeadband)) {
    			value *= RobotMap.winchArmMultiplier;
    			m_winchArm.setWinchArmPower(-value);
    		} else {
    			m_winchArm.setWinchArmPower(0);
    		}
    	} else {
    		m_winchArm.setWinchArmPower(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean checkDeadband(double value, double deadband) {
    	//Check if the value is within the deadband
        return (value > deadband || value < -deadband);
    }
}
