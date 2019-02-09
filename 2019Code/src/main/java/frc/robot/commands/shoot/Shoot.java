/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
  /**
   * This class allows the solenoids to shoot using the PushOut() method and then delays them
   *  for one second before they stow.
   */
  public Shoot() {
      addSequential(new PushOut(), 1);
      addSequential(new Stow());
  }
}
