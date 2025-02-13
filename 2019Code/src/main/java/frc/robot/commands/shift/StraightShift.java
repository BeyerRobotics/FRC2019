/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.*;

public class StraightShift extends CommandGroup {
  private boolean shifted = false;
  /**
   * Add your docs here.
   */
  public StraightShift(Joystick joy) {
    addSequential(new Shift());
    shifted = true;
    if(shifted) addSequential(new DriveStraight(joy, 0));
  }
}
