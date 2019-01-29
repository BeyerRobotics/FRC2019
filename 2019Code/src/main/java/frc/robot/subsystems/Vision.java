/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Subsystem to handle all logic surrounding vision targeting.
 */
public class Vision extends Subsystem {
  private static Vision vision;
  
  public static final int CENTER = 100; //the center x pixel

  private Vision() {

  }

  public static Vision getInstance() {
    if(vision == null) vision = new Vision();
    return vision;
  }

  public int getTarget() {
    return Robot.adaptor.serial.getData("c");
  }

  public int getCenter() {
    return CENTER;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
