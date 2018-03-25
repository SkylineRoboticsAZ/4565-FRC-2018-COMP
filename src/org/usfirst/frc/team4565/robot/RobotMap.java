/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4565.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final String robotType = "practice";
    //User controllers
	public static final int primaryJoystickPort = 0,
							secondaryJoystickPort = 1;
	//Secondary controller variables
	public static final double xboxTriggerDeadband = .5;
	//Robot drive motors
	public static final int leftFrontDriveMotor = 4,
							leftBackDriveMotor = 3,
							rightFrontDriveMotor = 1,
							rightBackDriveMotor = 0,
							leftEncoderPort0 = 0,
							leftEncoderPort1 = 1,
							rightEncoderPort0 = 2,
							rightEncoderPort1 = 3;
	//Robot drive parameters
	public static final double driveDeadband = .1,
							   driverTurnDeadband = .15,
                               driverTurnMultiplier = .7,
                               driverClawDeadband = .1,
                               boostDisabledMultiplier = .5,
                               boostEnabledMultiplier = 1;
	
	public static final boolean driverTurnInverted = true;
	//Bottom claw ports
	public static final int bottomClawSolenoidPort0 = 2,
							bottomClawSolenoidPort1 = 3,
							bottomClawPitchControlPort = 2;
	//Top claw ports
	public static final int topClawSolenoidPort0 = 0,
							topClawSolenoidPort1 = 1,
							topClawPitchControlPort = 5;
    //Winch ports
    public static final int winchPort = 3,
    						winchArmPort = 4;
	//Winch variables
    public static final double winchPower = 1,
    						   winchArmMultiplier = .75,
    						   winchArmDeadband = .2;
    //Auto config
    public static final double autoStraightSpeed = .6,
    						   autoTurnSpeed = .6,
    						   wheelDiameter = .1524,
    						   robotDiameter = .6096,
    						   autoClawPitchPulsePower = .75,
    						   autoClawPitchPulseTime = .75,
    						   autoDriveStopDistance = .05,
    						   autoBrakeTime = .25;
}
