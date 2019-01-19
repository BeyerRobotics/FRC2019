/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetArmToYVal extends Command {
    private Joystick joystick;
  public SetArmToYVal(Joystick joystick) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.adaptor.arm);
    this.joystick = joystick;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(joystick.getY() < 0.1 && joystick.getY() > -0.1){
      Robot.adaptor.arm.stop();
    } else if(joystick.getY() < -0.1){
      Robot.adaptor.arm.set(joystick.getY());
    } else if(joystick.getY() > 0.1){
      Robot.adaptor.arm.set(joystick.getY());
    }
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
