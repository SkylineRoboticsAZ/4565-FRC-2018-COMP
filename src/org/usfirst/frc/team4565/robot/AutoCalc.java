package org.usfirst.frc.team4565.robot;

import org.usfirst.frc.team4565.robot.RobotMap;

public final class AutoCalc {
	
	private static final double[] poly = {-186.012, 713.046, -1167.53, 1065.54,
										  -592.274, 205.122, -43.1791, 5.04464, .75};
	private static final int polyLength = 9;
	
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
    	return horner(poly, polyLength, percentComplete);
    }
    
    public static double calculatePercentAngle(double startingAngle, double currentAngle, double endAngle) {
    	double numerator = currentAngle - startingAngle;
    	double denominator = endAngle - startingAngle;
    	return numerator / denominator;
    }
    
	static double horner(double poly[], int n, double x)
	{
		// Initialize result
		double result = poly[0]; 

		// Evaluate value of polynomial using Horner's method
		for (int i=1; i<n; i++)
			result = result*x + poly[i];

		return result;
	}
}
