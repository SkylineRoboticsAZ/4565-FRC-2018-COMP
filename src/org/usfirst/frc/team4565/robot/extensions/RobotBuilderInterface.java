package org.usfirst.frc.team4565.robot.extensions;

import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

public interface RobotBuilderInterface {

	public DriveTrain initDriveTrain();
	public ScaleClaw initTopClaw();
	public Claw initBottomClaw();
	public Winch initWinch();
	public WinchArm initWinchArm();
}
