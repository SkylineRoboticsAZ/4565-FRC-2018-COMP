package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team4565.robot.AutoCalc;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DriveStraight extends Command {

	private DriveTrain m_driveTrain;
	private Encoder m_leftEncoder, m_rightEncoder;
	private Gyro m_gyro;
	private double m_goalRotations, m_leftRotations, m_rightRotations,
				   m_oldLeftRotations, m_oldRightRotations, m_angle;
	private boolean m_reverse, m_stop = false;
	private Timer m_timer;
	
    public DriveStraight(DriveTrain driveTrain, double meters) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_driveTrain = driveTrain;
    	m_leftEncoder = driveTrain.getLeftEncoder();
    	m_rightEncoder = driveTrain.getRightEncoder();
    	m_gyro = driveTrain.getGyro();
    	m_goalRotations = AutoCalc.calculateRobotDrive(Math.abs(meters));
    	m_leftRotations = 0;
    	m_rightRotations = 0;
    	m_oldLeftRotations = 0;
    	m_oldRightRotations = 0;
    	m_timer = new Timer();
    	
    	if (meters >= 0)
    		m_reverse = false;
    	else
    		m_reverse = true;
    	
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    	m_angle = m_gyro.getAngle();
    	m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftValue = m_leftEncoder.getDistance();
    	double rightValue = m_rightEncoder.getDistance();
    	
    	double leftMotorSpeed = RobotMap.autoStraightSpeed, 
    		   rightMotorSpeed = RobotMap.autoStraightSpeed;
    	
    	if (m_reverse) {
    		leftValue *= -1;
    		rightValue *= -1;
    		leftMotorSpeed *= -1;
    		rightMotorSpeed *= -1;
    	}
    	
    	double angle = m_gyro.getAngle();
    	
    	if (angle < m_angle) {
    		leftMotorSpeed -= (angle - m_angle);
    	} else if (angle > m_angle) {
    		rightMotorSpeed -= (m_angle - angle);
    	}
    	
    	m_driveTrain.setLeftDrive(leftMotorSpeed);
    	m_driveTrain.setRightDrive(rightMotorSpeed);
    	
    	m_leftRotations = leftValue;
    	m_rightRotations = rightValue;
    	
    	if (!isMoving())
    		m_stop = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((m_leftRotations >= m_goalRotations && m_rightRotations >= m_goalRotations) || m_stop) {
    		return true;
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_driveTrain.setLeftDrive(0);
    	m_driveTrain.setRightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    private boolean isMoving() {
    	if (m_timer.hasPeriodPassed(.5)) {
    		if (m_oldLeftRotations != 0 && m_oldRightRotations != 0) {
        		if (((m_leftRotations - m_oldLeftRotations) < RobotMap.autoDriveStopDistance) ||
        			 ((m_rightRotations - m_oldRightRotations) < RobotMap.autoDriveStopDistance))
        			return false;
    		}
    		
    		m_oldLeftRotations = m_leftRotations;
    		m_oldRightRotations = m_rightRotations;
    	}
    	
    	return true;
    }
}
