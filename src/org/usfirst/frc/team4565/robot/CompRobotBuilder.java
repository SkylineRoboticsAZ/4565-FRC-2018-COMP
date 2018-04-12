package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.commands.claw.TeleopClawPitchControl;
import org.usfirst.frc.team4565.robot.extensions.MotorGroup;
import org.usfirst.frc.team4565.robot.extensions.RobotBuilderInterface;
import org.usfirst.frc.team4565.robot.extensions.TalonSRXWrapper;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class CompRobotBuilder implements RobotBuilderInterface {
	
	@Override
	public DriveTrain initDriveTrain() {		
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
		
		ADXRS450_Gyro gyro = new ADXRS450_Gyro();
		
		//Create the DriveTrain subsystem
		DriveTrain driveTrain = new DriveTrain();
		driveTrain.addLeftSideMotor(leftFrontMotor);
		driveTrain.addLeftSideMotor(leftBackMotor);
		driveTrain.addRightSideMotor(rightFrontMotor);
		driveTrain.addRightSideMotor(rightBackMotor);
		driveTrain.setLeftSideEncoder(leftEncoder);
		driveTrain.setRightSideEncoder(rightEncoder);
		driveTrain.setGyro(gyro);
		
		return driveTrain;
	}
	
	@Override
	public ScaleClaw initTopClaw() {
		//Create all the motor controller objects
		TalonSRXWrapper pitchMotor = new TalonSRXWrapper(RobotMap.topClawPitchControlPort);
		TalonSRXWrapper secondPitchMotor = new TalonSRXWrapper(RobotMap.topClawPitchControlPort1);
		
		MotorGroup group = new MotorGroup();
		
		group.addMotor(pitchMotor);
		group.addMotor(secondPitchMotor);
		group.setInverted(true);
		
		DoubleSolenoid clawCylinder = new DoubleSolenoid(RobotMap.topClawSolenoidPort0, 
														 RobotMap.topClawSolenoidPort1);
		DoubleSolenoid pitchPiston = new DoubleSolenoid(RobotMap.topClawPitchPistonPort0, 
														  RobotMap.topClawPitchPistonPort1);
		
		//Create the new Claw subsystem
		ScaleClaw topClaw = new ScaleClaw(group, clawCylinder, pitchPiston);
		topClaw.setDefaultCommand(new TeleopClawPitchControl(topClaw, 1));
		return topClaw;
	}
	
	@Override
	public Claw initBottomClaw() {
		//Create all the motor controller objects
		TalonSRXWrapper pitchMotor = new TalonSRXWrapper(RobotMap.bottomClawPitchControlPort);
		DoubleSolenoid clawCylinder = new DoubleSolenoid(RobotMap.bottomClawSolenoidPort0, 
														 RobotMap.bottomClawSolenoidPort1);
		
		pitchMotor.setInverted(true);
		
		//Create the new Claw subsystem
		Claw bottomClaw = new Claw(pitchMotor, clawCylinder);
		bottomClaw.setDefaultCommand(new TeleopClawPitchControl(bottomClaw, 1));
		return bottomClaw;
	}

	@Override
	public Winch initWinch() {
		TalonSRXWrapper winchMotor = new TalonSRXWrapper(RobotMap.winchPort);

		Winch winch = new Winch(winchMotor);
		return winch;
	}
	
	@Override
	public WinchArm initWinchArm() {
		TalonSRXWrapper winchArmMotor = new TalonSRXWrapper(RobotMap.winchArmPort);
		
		WinchArm winchArm = new WinchArm(winchArmMotor);
		return winchArm;
	}
}
