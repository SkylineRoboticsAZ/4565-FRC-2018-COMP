/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.Winch;
import org.usfirst.frc.team4565.robot.subsystems.WinchArm;
import org.usfirst.frc.team4565.robot.extensions.RobotBuilderInterface;
import org.usfirst.frc.team4565.robot.commands.auto.MiddleSwitchAuto;
import org.usfirst.frc.team4565.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team4565.robot.commands.auto.DriveCurve;
import org.usfirst.frc.team4565.robot.commands.auto.StraightAuto;
import org.usfirst.frc.team4565.robot.commands.auto.TurnDegrees;
import org.usfirst.frc.team4565.robot.commands.SCurve;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	private enum Auto {
		NoAuto, LeftSide, RightSide, Middle, TestAuto
	}
	
	public static DriveTrain kDriveTrain;
	public static Claw kBottomClaw, kTopClaw;
	public static Winch kWinch;
	public static WinchArm kWinchArm;
	public static OI kOi;
	public static Command m_autoCommand;
	
	//private static Compressor m_compressor;

	SendableChooser<Auto> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotBuilderInterface robotBuilder = getRobotBuilder(RobotMap.robotType);
		
		if (robotBuilder == null)
			System.out.println("ERROR! Invalid robot type");
		
		//Initialize subsystems
		kDriveTrain = robotBuilder.initDriveTrain();
		kTopClaw = robotBuilder.initTopClaw();
		kBottomClaw = robotBuilder.initBottomClaw();
		kWinch = robotBuilder.initWinch();
		kWinchArm = robotBuilder.initWinchArm();
		
		kDriveTrain.setName("DriveTrain");
		kTopClaw.setName("topClaw");
		kBottomClaw.setName("BottomClaw");
		kWinch.setName("Winch");
		kWinchArm.setName("WinchArm");
		
		kOi = new OI();
		kOi.init();
		
		kOi.registerDevice(kDriveTrain.getName(), true);
		kOi.registerDevice(kTopClaw.getName(), false);
		kOi.registerDevice(kBottomClaw.getName(), true);
		kOi.registerDevice(kWinch.getName(), true);
		kOi.registerDevice(kWinchArm.getName(), true);
		
		m_chooser.addDefault("No Auto", Auto.NoAuto);
		m_chooser.addObject("Left Drive Straight", Auto.LeftSide);
		m_chooser.addObject("Right Drive Straight", Auto.RightSide);
		m_chooser.addObject("Middle Auto", Auto.Middle);
		m_chooser.addObject("Test Auto", Auto.TestAuto);
		SmartDashboard.putData("Auto Selection", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		switch (m_chooser.getSelected()) {
		case LeftSide:
			m_autoCommand = new StraightAuto(kDriveTrain, kBottomClaw, StraightAuto.Side.LeftSide);
			((StraightAuto)m_autoCommand).init(gameData);
			break;
		case RightSide:
			m_autoCommand = new StraightAuto(kDriveTrain, kBottomClaw, StraightAuto.Side.RightSide);
			((StraightAuto)m_autoCommand).init(gameData);
			break;
		case Middle:
			m_autoCommand = new MiddleSwitchAuto(kDriveTrain, kBottomClaw);
			((MiddleSwitchAuto)m_autoCommand).init(gameData);
			break;
		case TestAuto:
			m_autoCommand = new TurnDegrees(kDriveTrain, 90);
			break;
		default:
			m_autoCommand = null;
			break;
		}
		
		if (m_autoCommand != null)
			m_autoCommand.start();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		/*if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		m_autoCommand.start();*/
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autoCommand != null)
			m_autoCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	private RobotBuilderInterface getRobotBuilder(String robotType) {
		if (robotType.equals("practice"))
			return new PracticeRobotBuilder();
		else if (robotType.equals("competition"))
			return new CompRobotBuilder();
		
		return null;
	}
}
