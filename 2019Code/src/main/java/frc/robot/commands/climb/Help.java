/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Log;
import frc.robot.RobotStates.ClimberState;

public class Help extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Help() {
      addParallel(new SetFrontState(ClimberState.IN));
      addParallel(new SetBackState(ClimberState.IN));
      Log.fatal("Entire Robot", "Help I've Fallen and I Can't Get Up");
  }
}
