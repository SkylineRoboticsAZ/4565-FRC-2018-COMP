package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleAuto extends CommandGroup {

	public enum Side {
		LeftSide, RightSide
	}
	
	private Side m_side;
	private DriveTrain m_driveTrain;
	private ScaleClaw m_scaleClaw;
	
    public ScaleAuto(DriveTrain driveTrain, ScaleClaw scaleClaw, Side side) {
    	m_driveTrain = driveTrain;
    	m_scaleClaw = scaleClaw;
    	m_side = side;
    }
    
    public void init(String sideString) {
    	Side switchSide = null;
    	
    	if (sideString.length() != 0)
    		switchSide = (sideString.charAt(1) == 'L' ? Side.LeftSide : Side.RightSide);
    	
    	if (switchSide == null)
    		return;
    	
    	if (m_side == switchSide) {
    		addSequential(new DriveStraight(m_driveTrain, 7.71271));
    		addSequential(new TurnDegrees(m_driveTrain, (m_side == Side.LeftSide ? -90 : 90)));
    		//addSequential(new DriveStraight(m_driveTrain, -1));
    	} else {
    		addSequential(new DriveStraight(m_driveTrain, 5.301869));
    		addSequential(new TurnDegrees(m_driveTrain, (m_side == Side.LeftSide ? 90 : -90)));
    		addSequential(new DriveStraight(m_driveTrain, 4.887722));
    		addSequential(new TurnDegrees(m_driveTrain, (m_side == Side.LeftSide ? -90 : 90)));
    		addSequential(new DriveStraight(m_driveTrain, 2.410841));
    		addSequential(new TurnDegrees(m_driveTrain, (m_side == Side.LeftSide ? 90 : -90)));
    	}
    	
    	//throw the cube here
    }
}
