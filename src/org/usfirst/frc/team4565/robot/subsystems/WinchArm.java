package org.usfirst.frc.team4565.robot.subsystems;

import org.usfirst.frc.team4565.robot.commands.winch.TeleopWinchArmControl;
import org.usfirst.frc.team4565.robot.extensions.MotorControllerInterface;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchArm extends Subsystem {

    private MotorControllerInterface m_winchArmMotor;

    public WinchArm(MotorControllerInterface winchArmMotor) {
        m_winchArmMotor = winchArmMotor;
    }
    
    public void setWinchArmPower(double power) {
    	m_winchArmMotor.setPower(power);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TeleopWinchArmControl(this));
    }
}
