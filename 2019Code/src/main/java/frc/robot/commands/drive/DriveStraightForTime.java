/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DriveStraightForTime extends TimedCommand {
  
  private double speed;
  private double entranceAngle;

  public DriveStraightForTime(double timeout, double speed, double entranceAngle) {
      super(timeout);
      requires(Robot.adaptor.driveTrain);
      this.speed = speed;
      this.entranceAngle = entranceAngle;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    Robot.adaptor.navx.reset();
      Robot.adaptor.navx.setAngleAdjustment(-entranceAngle);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    Robot.adaptor.driveTrain.driveStraight(speed);
  }

  // Called once after timeout
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
