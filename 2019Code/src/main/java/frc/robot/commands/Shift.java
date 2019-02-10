/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotStates.DriveGear;

/**
 * Add your docs here.
 */
public class Shift extends InstantCommand {
  /**
   * Add your docs here.
   */
  public Shift() {
    super();
    requires(Robot.adaptor.shifters);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(Robot.adaptor.shifters.gear == DriveGear.LOW) Robot.adaptor.shifters.shiftHigh();
    else if(Robot.adaptor.shifters.gear == DriveGear.HIGH) Robot.adaptor.shifters.shiftLow();
  }

}
