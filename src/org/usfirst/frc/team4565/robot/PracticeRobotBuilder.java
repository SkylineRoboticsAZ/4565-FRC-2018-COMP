package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.extensions.RobotBuilderInterface;
import org.usfirst.frc.team4565.robot.extensions.TalonSRXWrapper;
import org.usfirst.frc.team4565.robot.extensions.TalonWrapper;
import org.usfirst.frc.team4565.robot.extensions.VictorSPWrapper;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class PracticeRobotBuilder implements RobotBuilderInterface {
	
	@Override
	public void initDriveTrain(DriveTrain driveTrain) {		
		//Create all the motor controller objects
		TalonSRXWrapper leftFrontMotor = new TalonSRXWrapper(RobotMap.leftFrontDriveMotor);
		TalonSRXWrapper leftBackMotor = new TalonSRXWrapper(RobotMap.leftBackDriveMotor);
		VictorSPWrapper rightFrontMotor = new VictorSPWrapper(RobotMap.rightFrontDriveMotor);
		VictorSPWrapper rightBackMotor = new VictorSPWrapper(RobotMap.rightBackDriveMotor);
		
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
		//Create all the motor controller objects
		VictorSPWrapper pitchMotor = new VictorSPWrapper(RobotMap.topClawPitchControlPort);
		DoubleSolenoid clawCylinder = new DoubleSolenoid(RobotMap.topClawSolenoidPort0, 
														 RobotMap.topClawSolenoidPort1);
		
		//Create the new Claw subsystem
		topClaw = new Claw(pitchMotor, clawCylinder, 1);
	}
	
	@Override
	public void initBottomClaw(Claw bottomClaw) {
		//Create all the motor controller objects
		VictorSPWrapper pitchMotor = new VictorSPWrapper(RobotMap.bottomClawPitchControlPort);
		DoubleSolenoid clawCylinder = new DoubleSolenoid(RobotMap.bottomClawSolenoidPort0, 
														 RobotMap.bottomClawSolenoidPort1);
		
		//Create the new Claw subsystem
		bottomClaw = new Claw(pitchMotor, clawCylinder, 1);
	}

	@Override
	public void initWinch(Winch winch) {
		TalonWrapper winchMotor = new TalonWrapper(RobotMap.winchPort);

		winch = new Winch(winchMotor);
	}
	
	@Override
	public void initWinchArm(WinchArm winchArm) {
		TalonWrapper winchArmMotor = new TalonWrapper(RobotMap.winchArmPort);
		
		winchArm = new WinchArm(winchArmMotor);
	}
}
