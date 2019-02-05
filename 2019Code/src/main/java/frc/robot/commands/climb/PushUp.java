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
  private boolean isFinished;

  private double angle;
  /**
   * Actuates front pistons, isFinished returns true when robot tilts to target angle
   * @param toAngle Target angle to stop at during actuation
   */
  public PushUp(double toAngle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    angle = toAngle;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.adaptor.navx.reset();
  }

  @Override
  protected void execute() {
    if(Robot.adaptor.navx.getPitch() < angle) Robot.adaptor.climber.shiftFrontUp();
    else isFinished = true;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

}
