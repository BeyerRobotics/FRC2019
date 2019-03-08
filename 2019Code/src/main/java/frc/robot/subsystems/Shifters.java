/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotStates.DriveGear;
import frc.robotMap.outputs.SolenoidMap;

/**
 * Subsystem to track drive gear and change it.
 */
public class Shifters extends Subsystem {
  private static Shifters shifters;

  private Solenoid pancakes;

  public DriveGear gear = DriveGear.LOW;

  public Shifters() {
    pancakes = new Solenoid(SolenoidMap.SHIFTER);
  }

  public static Shifters getInstance() {
    if(shifters == null) shifters = new Shifters();
    return shifters;
  }

  public void shiftHigh() {
    pancakes.set(true);
    gear = DriveGear.HIGH;
  }

  public void shiftLow() {
    pancakes.set(false);
    gear = DriveGear.LOW;
  }

  @Override
  public void initDefaultCommand() {
  }
}
