/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class PushForward extends Command {
  private double angle;
  /**
   * Actuates rear pistons, isFinished returns true when robot tilts to target angle
   * @param toAngle Target angle to stop at during actuation
   */
  public PushForward(double toAngle) {
    requires(Robot.adaptor.climber);
    angle = toAngle;
  }

  @Override
  protected void initialize() {
    Robot.adaptor.navx.reset();
    Robot.adaptor.climber.openBack();
    Robot.adaptor.climber.backLeftDown();
    Robot.adaptor.climber.backRightDown();
  }

  @Override
  protected void execute() {
    if(Robot.adaptor.navx.getPitch() > 5) {
      Robot.adaptor.climber.backLeftHold();
      Robot.adaptor.climber.backRightDown();
    } else if(Robot.adaptor.navx.getPitch() < -5) {
      Robot.adaptor.climber.backRightHold();
      Robot.adaptor.climber.backLeftDown();
    } else {
      Robot.adaptor.climber.backLeftDown();
      Robot.adaptor.climber.backRightDown();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.adaptor.navx.getRoll() < angle;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

}
