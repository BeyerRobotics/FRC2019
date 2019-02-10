/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ResetArmEnc extends InstantCommand {
  /**
   * Add your docs here.
   */
  public ResetArmEnc() {
    super();
    requires(Robot.adaptor.arm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.adaptor.arm.resetEnc();
  }

}
