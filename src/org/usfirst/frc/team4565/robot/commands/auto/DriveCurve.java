package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.AutoCalc;
import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveCurve extends Command {

	private DriveTrain m_driveTrain;
	private Encoder m_leftEncoder, m_rightEncoder;
	private Gyro m_gyro;
	private double m_turnRatio, m_degrees, m_startingAngle, m_goalAngle;
	private boolean m_turningRight, m_reflectX;
	
    public DriveCurve(DriveTrain driveTrain, double radius, double degrees, boolean reflectX) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_driveTrain = driveTrain;
    	m_leftEncoder = driveTrain.getLeftEncoder();
    	m_rightEncoder = driveTrain.getRightEncoder();
    	m_gyro = driveTrain.getGyro();
    	m_degrees = degrees;
    	m_turningRight = (degrees >= 0 ? true : false);
    	m_reflectX = reflectX;
    	
    	if(reflectX)
    		m_degrees *= -1;
    	
    	//Calculate ratio
    	double absRadius = Math.abs(radius);
    	
    	if (absRadius <= RobotMap.robotDiameter) {
    		System.out.println("DRIVE CURVE ERROR: Radius less than or equal to robot's radius");
    		m_turnRatio = -1;
    		return;
    	}

    	double difference = .5 * RobotMap.robotDiameter;
    	
    	double innerRadius = absRadius - difference;
    	double outerRadius = absRadius + difference;
    	
    	m_turnRatio = innerRadius / outerRadius;
    	
    	requires(driveTrain);
    }
    
    public DriveCurve(DriveTrain driveTrain, double radius, double degrees) {
    	this(driveTrain, radius, degrees, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    	m_startingAngle = m_gyro.getAngle();
    	m_goalAngle = m_startingAngle + m_degrees;
    	System.out.println("Starting Angle: " + m_startingAngle);
    	System.out.println("Goal Angle: " + m_goalAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftValue = Math.abs(m_leftEncoder.getDistance());
    	double rightValue = Math.abs(m_rightEncoder.getDistance());
    	
    	double experimentalRatio;
    	
    	if (m_turningRight)
    		experimentalRatio = rightValue / leftValue;
    	else
    		experimentalRatio = leftValue / rightValue;
    	
    	double leftMotorValue, rightMotorValue;
    	
		if (m_turningRight) {
			leftMotorValue = RobotMap.autoTurnSpeed;
			rightMotorValue = RobotMap.autoTurnSpeed * m_turnRatio;
		} else {
			leftMotorValue = RobotMap.autoTurnSpeed * m_turnRatio;
			rightMotorValue = RobotMap.autoTurnSpeed;
		}
    	
    	if (experimentalRatio > m_turnRatio) {
    		if (m_turningRight)
    			rightMotorValue -= experimentalRatio - m_turnRatio;
    		else
    			leftMotorValue -= experimentalRatio - m_turnRatio;
    	} else if (m_turnRatio > experimentalRatio) {
    		if (m_turningRight)
    			rightMotorValue += m_turnRatio - experimentalRatio;
    		else
    			leftMotorValue += m_turnRatio - experimentalRatio;
    	}
    	
    	double angle = m_gyro.getAngle();
    	double percentComplete = AutoCalc.calculatePercentAngle(m_startingAngle, angle, m_goalAngle);
    	double easingFactor = AutoCalc.calculateEasingValue(percentComplete);
    	
    	leftMotorValue *= easingFactor;
    	rightMotorValue *= easingFactor;
    	
    	if (m_reflectX) {
    		leftMotorValue *= -1;
    		rightMotorValue *= -1;
    	}
    	
    	m_driveTrain.setLeftDrive(leftMotorValue);
    	m_driveTrain.setRightDrive(rightMotorValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double angle = m_gyro.getAngle();
    	
    	System.out.println("Current Angle: " + angle);
    	
    	if (m_reflectX) {
    		if (m_turningRight)
    			return (angle <= m_goalAngle);
        	else
        		return (angle >= m_goalAngle);
    	} else {
    		if (m_turningRight)
        		return (angle >= m_goalAngle);
        	else
        		return (angle <= m_goalAngle);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
