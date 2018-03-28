package org.usfirst.frc.team4565.robot.commands.drive;

import org.usfirst.frc.team4565.robot.Robot;
import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows the drive train to be controlled using an XboxController object
 * @author Skyline Robotics - Ian McDonough
 *
 */
public class TeleopDrive extends Command {

	private DriveTrain m_driveTrain;
	
	/**
	 * Constructs a new TeleopDrive command
	 */
    public TeleopDrive(DriveTrain driveTrain) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_driveTrain = driveTrain;
    	
    	requires(driveTrain);
    }

    /**
     * Does nothing
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.kOi.isDeviceEnabled(m_driveTrain.getName())) {
        	XboxController controller = Robot.kOi.getPrimaryController();
        	
            //Read values from controller
            double accelerator = controller.getRawAxis(3);
            double decelerator = controller.getRawAxis(2);
            double turnFactor = controller.getRawAxis(4) + controller.getRawAxis(0);
            
            //Determine if the values are within margin of error
            boolean useAccelerator, useDecelerator, useTurnFactor;
            useAccelerator = checkDeadband(accelerator, RobotMap.driveDeadband);
            useDecelerator = checkDeadband(decelerator, RobotMap.driveDeadband);
            useTurnFactor = checkDeadband(turnFactor, RobotMap.driverTurnDeadband);

            //Zero values that are within margin of error &
            // give backing up priority over going forward
            double newMotorValue;

            if (useDecelerator)
                newMotorValue = -decelerator;
            else if (useAccelerator)
                newMotorValue = accelerator;
            else
                newMotorValue = 0;

            if (!useTurnFactor)
                turnFactor = 0;

            //Y = A(1-|X|) if x < 1 (Y,A) if x > 0 (A,Y)
            //Drive if we have any values that are actually reasonable
            if (useAccelerator || useDecelerator || useTurnFactor) {
                //Boost if the boost button is pressed
                double boostMultiplier = (controller.getRawButton(10) ?
                        RobotMap.boostEnabledMultiplier : RobotMap.boostDisabledMultiplier);
                newMotorValue *= boostMultiplier;

                m_driveTrain.setLeftDrive(newMotorValue + turnFactor * RobotMap.driverTurnMultiplier);
                m_driveTrain.setRightDrive(newMotorValue - turnFactor * RobotMap.driverTurnMultiplier);
            } else {
            	m_driveTrain.setLeftDrive(0);
            	m_driveTrain.setRightDrive(0);
            }
    	} else {
    		m_driveTrain.setLeftDrive(0);
        	m_driveTrain.setRightDrive(0);
    	}
    }
    
    private boolean checkDeadband(double value, double deadband) {
    	//Check if the value is within the deadband
        return (value > deadband || value < -deadband);
    }

    /**
     * This command is never finished, so return false
     * NOTE: This command can be interrupted, allowing for other commands to run
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
