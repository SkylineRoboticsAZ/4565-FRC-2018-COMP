package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.RobotMap;

public final class AutoCalc {
	private static final double distancePerRotation = RobotMap.wheelDiameter * Math.PI;
	private static final double robotCircumference = RobotMap.robotDiameter * Math.PI;
	
	//Converts a displacement in meters of the robot into rotations of the robot's wheels in degrees
    public static double calculateRobotDrive(double meters) {
    	return meters / distancePerRotation;
    }
    
    //Converts an overall rotation of the robot in degrees into rotations of the robot's wheels in degrees
    public static double calculateRobotTurn(double degrees) {
    	return calculateRobotDrive((robotCircumference * degrees) / 360);
    }
}
