package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.extensions.RobotBuilderInterface;
import org.usfirst.frc.team4565.robot.extensions.TalonSRXWrapper;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class CompRobotBuilder implements RobotBuilderInterface {
	
	@Override
	public void initDriveTrain(DriveTrain driveTrain) {		
		//Create all the motor controller objects
		TalonSRXWrapper leftFrontMotor = new TalonSRXWrapper(RobotMap.leftFrontDriveMotor);
		TalonSRXWrapper leftBackMotor = new TalonSRXWrapper(RobotMap.leftBackDriveMotor);
		TalonSRXWrapper rightFrontMotor = new TalonSRXWrapper(RobotMap.rightFrontDriveMotor);
		TalonSRXWrapper rightBackMotor = new TalonSRXWrapper(RobotMap.rightBackDriveMotor);
		
		//Invert the right side
		rightFrontMotor.setInverted(true);
		rightBackMotor.setInverted(true);
		
		//Create encoder objects
		Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPort0, RobotMap.leftEncoderPort1, true, EncodingType.k4X);
		Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPort0, RobotMap.rightEncoderPort1, false, EncodingType.k4X);
		
		//Create the DriveTrain subsystem
		driveTrain = new DriveTrain();
		driveTrain.addLeftSideMotor(leftFrontMotor);
		driveTrain.addLeftSideMotor(leftBackMotor);
		driveTrain.addRightSideMotor(rightFrontMotor);
		driveTrain.addRightSideMotor(rightBackMotor);
		driveTrain.setLeftSideEncoder(leftEncoder);
		driveTrain.setRightSideEncoder(rightEncoder);
	}
	
	@Override
	public void initTopClaw(Claw topClaw) {
		
	}
	
	@Override
	public void initBottomClaw(Claw bottomClaw) {
		//Create all the motor controller objects
		TalonSRXWrapper pitchMotor = new TalonSRXWrapper(RobotMap.bottomClawPitchControlPort);
		DoubleSolenoid clawCylinder = new DoubleSolenoid(RobotMap.bottomClawSolenoidPort0, 
														 RobotMap.bottomClawSolenoidPort1);
		
		//Create the new Claw subsystem
		bottomClaw = new Claw(pitchMotor, clawCylinder, 1);
	}

	@Override
	public void initWinch(Winch winch) {
		TalonSRXWrapper winchMotor = new TalonSRXWrapper(RobotMap.winchPort);

		winch = new Winch(winchMotor);
	}
	
	@Override
	public void initWinchArm(WinchArm winchArm) {
		TalonSRXWrapper winchArmMotor = new TalonSRXWrapper(RobotMap.winchArmPort);
		
		winchArm = new WinchArm(winchArmMotor);
	}
}
