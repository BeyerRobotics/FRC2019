/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Log;
import frc.robot.RobotStates.ClimbLevel;
import frc.robot.RobotStates.ClimberState;
import frc.robot.commands.TimeDelay;
import frc.robotMap.outputs.ClimbMap;

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
        addSequential(new SetFrontState(ClimberState.HOLD)); //runs when PushUp() is done
        addSequential(new PushForward(-3));
        addSequential(new SetBackState(ClimberState.HOLD));
        break;
      case FIRST:
        addSequential(new PushUp(ClimbMap.MID_CLIMB)); //calibrate these angles
        addSequential(new SetFrontState(ClimberState.HOLD)); //runs when PushUp() is done
        addSequential(new PushForward(-3));
        addSequential(new SetBackState(ClimberState.HOLD));
        break;
      case SECOND:
        addSequential(new PushUp(ClimbMap.HIGH_CLIMB));
        addSequential(new SetFrontState(ClimberState.HOLD)); //runs when PushUp() is done
        addSequential(new TimeDelay(2));
        addSequential(new PushForward(-3));
        addSequential(new SetBackState(ClimberState.HOLD));
        Log.info("Climber", "Yay! You climbed L3! (probably)");
        break;
      default:
        break;
    }
  }
}
