package org.usfirst.frc.team4565.robot.subsystems;

import org.usfirst.frc.team4565.robot.commands.drive.TeleopDrive;
import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.Encoder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Controls a drive train consisting of left and right side motors
 * @author Skyline Robotics - Ian McDonough
 *
 */
public class DriveTrain extends Subsystem {
	
	private List<MotorControllerInterface> m_leftSideMotors,
										   m_rightSideMotors;
	private Encoder m_leftEncoder, m_rightEncoder;
	private Gyro m_gyro;
	
	/**
	 * Constructs a DriveTrain subsystem object
	 */
	public DriveTrain() {
		m_leftSideMotors = new LinkedList<MotorControllerInterface>();
		m_rightSideMotors = new LinkedList<MotorControllerInterface>();
	}
	
	/**
	 * Add a motor to the left side of this drive train
	 * @param motor The motor to add
	 */
	public void addLeftSideMotor(MotorControllerInterface motor) {
		m_leftSideMotors.add(motor);
	}

	/**
	 * Add a motor to the right side of this drive train
	 * @param motor The motor to add
	 */
	public void addRightSideMotor(MotorControllerInterface motor) {
		m_rightSideMotors.add(motor);
	}
	
	public void setLeftSideEncoder(Encoder encoder) {
		configureEncoder(encoder);
		m_leftEncoder = encoder;
	}
	
	public void setRightSideEncoder(Encoder encoder) {
		configureEncoder(encoder);
		m_rightEncoder = encoder;
	}
	
	public void setGyro(Gyro gyro) {
		m_gyro = gyro;
	}
	
	public Encoder getLeftEncoder() {
		return m_leftEncoder;
	}
	
	public Encoder getRightEncoder() {
		return m_rightEncoder;
	}
	
	public Gyro getGyro() {
		return m_gyro;
	}
	
	/**
	 * Initialize the default command for the subsystem
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive(this));
    }
    
    public void setLeftDrive(double power) {
    	setMotorsPower(m_leftSideMotors, power);
    }
    
    public void setRightDrive(double power) {
    	setMotorsPower(m_rightSideMotors, power);
    }
    
    private void configureEncoder(Encoder encoder) {
    	encoder.setDistancePerPulse(1.0/360.0);
    	encoder.setSamplesToAverage(5);
    	encoder.reset();
    }
    
    private void setMotorsPower(List<MotorControllerInterface> list, double power) {
    	//Go through the list of motors and set the power on each one
    	Iterator<MotorControllerInterface> i = list.iterator();
    	while (i.hasNext()) {
    		MotorControllerInterface motor = i.next();
    		motor.setPower(power);
    	}
    }
    
}

