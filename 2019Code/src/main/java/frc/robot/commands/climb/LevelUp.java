/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates.ClimbLevel;
import frc.robotMap.outputs.ClimbMap;;

public class LevelUp extends CommandGroup {
  /**
   * Command to bring robot to the altitude needed for climb
   * @param level The level of the climb
   * @author Robert Smith
   */
  public LevelUp(ClimbLevel level) {
    switch (level) {
      case GROUND:
        addSequential(new PushUp(ClimbMap.LOW_CLIMB)); //isFinished returns true when pitch exceeds given angle
        addSequential(new HoldFront()); //runs when PushUp() is done
        addSequential(new PushForward(0));
        addSequential(new HoldBack());
        addSequential(new ReleaseFront());
        break;
      case FIRST:
        addSequential(new PushUp(ClimbMap.MID_CLIMB)); //calibrate these angles
        addSequential(new HoldFront());
        addSequential(new PushForward(0));
        addSequential(new HoldBack());
        addSequential(new ReleaseFront());
        break;
      case SECOND:
        addSequential(new PushUp(ClimbMap.HIGH_CLIMB));
        addSequential(new HoldFront());
        addSequential(new PushForward(0));
        addSequential(new HoldBack());
        addSequential(new ReleaseFront());
        break;
      default:
        break;
    }
  }
}
