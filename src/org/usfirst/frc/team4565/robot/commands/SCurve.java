package org.usfirst.frc.team4565.robot.commands;

import org.usfirst.frc.team4565.robot.commands.auto.DriveCurve;
import org.usfirst.frc.team4565.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team4565.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SCurve extends CommandGroup {

    public SCurve(DriveTrain driveTrain) {
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
    	addSequential(new DriveCurve(driveTrain, 1, 45));
    	addSequential(new DriveStraight(driveTrain, 1));
    	addSequential(new DriveCurve(driveTrain, 1, -45));
    	addSequential(new DriveStraight(driveTrain, 1));
    }
}
