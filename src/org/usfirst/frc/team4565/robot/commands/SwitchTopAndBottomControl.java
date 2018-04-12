package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.OI;
import org.usfirst.frc.team4565.robot.commands.ToggleDeviceControl;
import org.usfirst.frc.team4565.robot.subsystems.Claw;
import org.usfirst.frc.team4565.robot.subsystems.ScaleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchTopAndBottomControl extends CommandGroup {

	private OI m_oi;
	private Claw m_bottomClaw;
	private ScaleClaw m_topClaw;
	
    public SwitchTopAndBottomControl(OI oi, Claw bottomClaw, ScaleClaw topClaw) {
    	m_oi = oi;
    	m_bottomClaw = bottomClaw;
    	m_topClaw = topClaw;
    	
    	addSequential(new ToggleDeviceControl(m_oi, m_bottomClaw.getName(), m_topClaw.getName()));
    	addSequential(new AutoCorrectTopClaw(m_oi, m_topClaw));
    }
}
