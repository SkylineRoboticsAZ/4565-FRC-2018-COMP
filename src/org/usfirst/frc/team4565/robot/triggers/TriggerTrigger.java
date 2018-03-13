package org.usfirst.frc.team4565.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;

import org.usfirst.frc.team4565.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;

/**
 *
 */
public class TriggerTrigger extends Trigger {

	private XboxController m_controller;
	private Trigger m_trigger;
	
	public enum Trigger {
		LeftTrigger, RightTrigger
	};
	
	public TriggerTrigger(XboxController controller, Trigger trigger) {
		m_controller = controller;
		m_trigger = trigger;
	}
	
    public boolean get() {
    	double value;

    	if (m_trigger == Trigger.LeftTrigger)
    		value = m_controller.getRawAxis(2);
    	else if (m_trigger == Trigger.RightTrigger)
    		value = m_controller.getRawAxis(3);
    	else
    		value = 0;
    	
    	return (value >= RobotMap.xboxTriggerDeadband);
    }
}
