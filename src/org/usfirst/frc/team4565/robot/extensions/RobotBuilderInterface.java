package org.usfirst.frc.team4565.robot.extensions;

import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;

public interface RobotBuilderInterface {

	public void initDriveTrain(DriveTrain driveTrain);
	public void initTopClaw(Claw topClaw);
	public void initBottomClaw(Claw bottomClaw);
	public void initWinch(Winch winch);
	public void initWinchArm(WinchArm winchArm);
}
