/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveStraight extends Command {
  private double entranceAngle;
  private Joystick joy;

  public DriveStraight(Joystick joy, double entranceAngle) {
    requires(Robot.adaptor.driveTrain);
    this.joy = joy;
    this.entranceAngle = entranceAngle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.adaptor.navx.zeroYaw();
    Robot.adaptor.navx.setAngleAdjustment(-entranceAngle);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.adaptor.driveTrain.driveStraight((joy.getX() + joy.getZ())/2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
