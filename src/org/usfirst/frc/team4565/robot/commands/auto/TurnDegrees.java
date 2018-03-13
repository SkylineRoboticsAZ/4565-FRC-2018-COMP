package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.AutoCalc;
import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class TurnDegrees extends Command {

	private DriveTrain m_driveTrain;
	private Encoder m_leftEncoder, m_rightEncoder;
	private double m_goalRotations, m_leftRotations, m_rightRotations;
	private boolean m_turningRight;
	
    public TurnDegrees(DriveTrain driveTrain, double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_driveTrain = driveTrain;
    	m_leftEncoder = driveTrain.getLeftEncoder();
    	m_rightEncoder = driveTrain.getRightEncoder();
    	m_goalRotations = AutoCalc.calculateRobotTurn(Math.abs(degrees));
    	m_leftRotations = 0;
    	m_rightRotations = 0;
    	m_turningRight = (degrees >= 0 ? true : false);
    	
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftValue = m_leftEncoder.getDistance();
    	double rightValue = m_rightEncoder.getDistance();
    	
    	if (m_turningRight)
    		rightValue *= -1;
    	else
    		leftValue *= -1;
    	
    	double leftMotorValue, rightMotorValue;
    	
    	if (leftValue > rightValue) {
    		leftMotorValue = RobotMap.autoTurnSpeed - (leftValue - rightValue);
    		rightMotorValue = RobotMap.autoTurnSpeed;
    	} else if (rightValue > leftValue) {
    		leftMotorValue = RobotMap.autoTurnSpeed;
    		rightMotorValue = RobotMap.autoTurnSpeed - (rightValue - leftValue);
    	} else {
    		leftMotorValue = RobotMap.autoTurnSpeed;
    		rightMotorValue = RobotMap.autoTurnSpeed;
    	}
    	
    	if (m_turningRight)
    		rightMotorValue *= -1;
    	else
    		leftMotorValue *= -1;
    	
    	m_driveTrain.setLeftDrive(leftMotorValue);
    	m_driveTrain.setRightDrive(rightMotorValue);
    	
    	m_leftRotations = leftValue;
    	m_rightRotations = rightValue;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (m_leftRotations >= m_goalRotations && m_rightRotations >= m_goalRotations);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
