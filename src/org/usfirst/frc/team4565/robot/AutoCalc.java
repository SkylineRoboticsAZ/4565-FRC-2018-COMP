package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.RobotMap;

public final class AutoCalc {
	
	private static final double a = 71/30;
	private static final double b = 1.45;
	private static final double c = 11/30;
	private static final double d = .75;
	
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
    
    public static double calculateEasingValue(double percentComplete) {
    	return a * Math.pow(percentComplete, 3) + b * Math.pow(percentComplete, 2) + c * percentComplete + d;
    }
    
    public static double calculatePercentAngle(double startingAngle, double currentAngle, double endAngle) {
    	double numerator = currentAngle - startingAngle;
    	double denominator = endAngle - startingAngle;
    	return numerator / denominator;
    }
}
