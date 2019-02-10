/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Subsystem to handle all outputs to SmartDashboard
 */
public class Dispatcher extends Subsystem {
  private static Dispatcher dispatcher;

  private Dispatcher() {
  }

  public void update() {
    addNum("Voltage", Robot.adaptor.pdp.getVoltage());
    addSendable("DriveTrain", Robot.adaptor.driveTrain);
  }

  public void addNum(String key, double val) {
    SmartDashboard.putNumber(key, val);
  }

  public void addBool(String key, Boolean val) {
    SmartDashboard.putBoolean(key, val);
  }

  public void addStr(String key, String val) {
    SmartDashboard.putString(key, val);
  }
  
  public void addSendable(String key, Sendable val) {
    SmartDashboard.putData(key, val);
  }

  public static Dispatcher getInstance() {
    if(dispatcher == null) dispatcher = new Dispatcher();
    return dispatcher;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
