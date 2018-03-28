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
public class SmoothTeleopDrive extends Command {

	private DriveTrain m_driveTrain;
	
	/**
	 * Constructs a new TeleopDrive command
	 */
    public SmoothTeleopDrive(DriveTrain driveTrain) {
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
            double rightTrigger = controller.getRawAxis(3);
            double leftTrigger = -controller.getRawAxis(2);
            double leftJoystick = controller.getRawAxis(0);
            double rightJoystick = controller.getRawAxis(4);
            
            //Merge the values using the deadbands
            double acceleration = mergeValues(rightTrigger, leftTrigger, RobotMap.driveDeadband);
            double turnFactor = mergeValues(leftJoystick, rightJoystick, RobotMap.driverTurnDeadband);
            
            boolean isAccelerationNegative = (acceleration < 0);
            //boolean isTurnFactorNegative = (turnFactor < 0);
            
            //Square drive values to give us a smoother drive
            acceleration *= acceleration;
            //turnFactor *= turnFactor;
            
            if (isAccelerationNegative)
            	acceleration *= -1;
            /*if (isTurnFactorNegative)
            	turnFactor *= -1;*/

            //These will be the values for our motors
            double leftMotorValue = 0;
            double rightMotorValue = 0;

            if (acceleration != 0) {
            	//Straight or curves
        		boolean turningRight = (turnFactor > 0);
        		double turnMotorFactor = calcNeTurnFactor(turnFactor);
        		
        		if (acceleration < 0 && RobotMap.driverTurnInverted)
        			turningRight = !turningRight;
        		
        		if (turningRight) {
        			leftMotorValue = acceleration;
        			rightMotorValue = acceleration * turnMotorFactor;
        		} else {
        			leftMotorValue = acceleration * turnMotorFactor;
        			rightMotorValue = acceleration;
        		}
            } else if (turnFactor != 0) { //Just turning
            	double modifiedTurnFactor = turnFactor * RobotMap.driverTurnMultiplier;
            	leftMotorValue = modifiedTurnFactor;
            	rightMotorValue = -modifiedTurnFactor;
            }
            
            m_driveTrain.setLeftDrive(leftMotorValue);
            m_driveTrain.setRightDrive(rightMotorValue);
    	} else {
    		m_driveTrain.setLeftDrive(0);
        	m_driveTrain.setRightDrive(0);
    	}
    }
    
    private static double calcLinearTurnFactor(double x) {
    	return 1 - (2 * Math.abs(x));
    }
    
    private static final double a = -.395559;
    private static final double b = 1.39556;
    private static final double c = 1.80107;
    
    private static double calcNeTurnFactor(double x) {
    	return a * Math.exp(c * Math.abs(x)) + b;
    }
    
    private static double mergeValues(double value1, double value2, double deadband) {
        double netValue = 0;
        
        if (checkDeadband(value1, deadband))
        	netValue += value1;
        if (checkDeadband(value2, deadband))
        	netValue += value2;
        
        return netValue;
    }
    
    private static boolean checkDeadband(double value, double deadband) {
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
