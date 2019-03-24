/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class HasTipped extends Trigger {
  // private static boolean hasChecked;

  public boolean get() {
    // if(!hasChecked) {
    //   if(Math.abs(Robot.adaptor.navx.getPitch()) > 45) {
    //     hasChecked = true;
    //     return true;
    //   } else if(Math.abs(Robot.adaptor.navx.getRoll()) > 45) {
    //     hasChecked = true;
    //     return true;
    //   } else return false;
    // } else if(hasChecked) {
    //   if(Math.abs(Robot.adaptor.navx.getPitch()) < 45) {
    //     hasChecked = false;
    //   } else if(Math.abs(Robot.adaptor.navx.getRoll()) < 45) {
    //     hasChecked = false;
    //   } else return false;
    // }
    // return false;
    if(Math.abs(Robot.adaptor.navx.getPitch()) > 45 || Math.abs(Robot.adaptor.navx.getRoll()) > 45) {
      return true;
    } else return false;
  }
}
