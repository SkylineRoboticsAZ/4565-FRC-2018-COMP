package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class TurnDegrees extends Command {

	private DriveTrain m_driveTrain;
	private Encoder m_leftEncoder, m_rightEncoder;
	private Gyro m_gyro;
	private double m_degrees, m_startingAngle, m_goalAngle;
	private boolean m_turningRight, m_braking, m_finished;
	private Timer m_brakeTimer;
	
    public TurnDegrees(DriveTrain driveTrain, double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_driveTrain = driveTrain;
    	m_degrees = degrees;
    	m_leftEncoder = driveTrain.getLeftEncoder();
    	m_rightEncoder = driveTrain.getRightEncoder();
    	m_gyro = driveTrain.getGyro();
    	m_turningRight = (degrees >= 0 ? true : false);
    	m_finished = false;
    	m_brakeTimer = new Timer();
    	m_braking = false;
    	
    	requires(driveTrain);
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
    	
    	if (!m_braking) {
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
	    	
	    	if (finished())
	    		brake();
	    	
    	} else {
        	if (m_brakeTimer.hasPeriodPassed(RobotMap.autoBrakeTime)) {
        		m_driveTrain.setLeftDrive(0);
        		m_driveTrain.setRightDrive(0);
        		
        		m_finished = true;
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return m_finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean finished() {
    	double angle = m_gyro.getAngle();
    	
    	System.out.println("Current Angle: " + angle);
    	
    	if (m_turningRight)
    		return (angle >= m_goalAngle);
    	else
    		return (angle <= m_goalAngle);
    }
    
    private void brake() {
    	m_brakeTimer.start();
    	m_braking = true;
    	
    	double drivePower = RobotMap.autoTurnSpeed;
    	
    	if (m_turningRight)
    		drivePower *= -1;
    		
		m_driveTrain.setLeftDrive(drivePower);
		m_driveTrain.setRightDrive(-drivePower);
    }
}
