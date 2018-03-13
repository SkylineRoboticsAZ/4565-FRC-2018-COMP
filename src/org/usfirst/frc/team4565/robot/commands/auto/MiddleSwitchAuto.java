package org.usfirst.frc.team4565.robot.commands.auto;

//import org.usfirst.frc.team4565.robot.RobotMap;
import org.usfirst.frc.team4565.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleSwitchAuto extends CommandGroup {
	
	private enum SwitchSide {
		LeftSide, RightSide
	}
	
	private SwitchSide m_side;
	private DriveTrain m_driveTrain;
	private Claw m_claw;

    public MiddleSwitchAuto(DriveTrain driveTrain, Claw claw) {
    	
    	m_driveTrain = driveTrain;
    	m_claw = claw;
    	
    	/*addSequential(new DriveStraight(m_driveTrain, 3.05));
    	addSequential(new OpenClaw(m_claw));*/
    }
    
    public void init(String sideString) {
    	if (sideString.length() != 0) {
        	if (sideString.charAt(0) == 'L')
        		m_side = SwitchSide.LeftSide;
        	else
        		m_side = SwitchSide.RightSide;
        	
        	m_side = sideString.charAt(0) == 'L' ? SwitchSide.LeftSide : SwitchSide.RightSide;
    	}
    	
    	addSequential(new DriveStraight(m_driveTrain, .5));
    	addSequential(new TurnDegrees(m_driveTrain, (m_side == SwitchSide.LeftSide ? -90 : 90)));
    	addSequential(new DriveStraight(m_driveTrain, 1.65));/*(1.65 + 
    			(m_side == SwitchSide.LeftSide ? RobotMap.autoStartOffset : -RobotMap.autoStartOffset))));*/
    	addSequential(new TurnDegrees(m_driveTrain, (m_side == SwitchSide.LeftSide ? 90 : -90)));
    	addSequential(new DriveStraight(m_driveTrain, 1.7));
    	addSequential(new ClawPitchPulse(m_claw, true));
    	addSequential(new OpenClaw(m_claw));
    	addSequential(new ClawPitchPulse(m_claw));
    }
}
