package org.usfirst.frc.team4565.robot.commands.auto;

import org.usfirst.frc.team4565.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightAuto extends CommandGroup {
	
	public enum Side {
		LeftSide, RightSide
	}
	
	private Side m_side;
	private DriveTrain m_driveTrain;
	private Claw m_claw;

    public StraightAuto(DriveTrain driveTrain, Claw claw, Side side) {
    	m_driveTrain = driveTrain;
    	m_claw = claw;
    	m_side = side;
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.	
    }
    
    public void init(String sideString) {
    	
    	Side switchSide = null;
    	
    	if (sideString.length() != 0)
    		switchSide = (sideString.charAt(0) == 'L' ? Side.LeftSide : Side.RightSide);
    	
    	addSequential(new DriveStraight(m_driveTrain, 3.6));
    	
    	if (switchSide != null && m_side == switchSide) {
    		addSequential(new TurnDegrees(m_driveTrain, (m_side == Side.LeftSide ? 90 : -90)));
    		addSequential(new DriveStraight(m_driveTrain, 1));
        	addSequential(new ClawPitchPulse(m_claw, true));
        	addSequential(new OpenClaw(m_claw));
        	addSequential(new ClawPitchPulse(m_claw));
    	}
    }
}
