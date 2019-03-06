/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class PushUp extends InstantCommand {
  private double angle;
  /**
   * Actuates front pistons, isFinished returns true when robot tilts to target angle
   * @param toAngle Target angle to stop at during actuation
   */
  public PushUp(double toAngle) {
    requires(Robot.adaptor.climber);
    angle = toAngle;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.adaptor.climber.shiftFrontDown();
  }

  @Override
  protected boolean isFinished() {
    return Robot.adaptor.navx.getRoll() > angle;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

}