/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * This class pushes the solenoid out for shooter.
 */
public class PushOut extends InstantCommand {
  public PushOut() {
    super();
    requires(Robot.adaptor.shooter);
  }

  @Override
  protected void initialize() {
      Robot.adaptor.shooter.pushOut();
  }
}
