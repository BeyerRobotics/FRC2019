/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.arm.ResetArmEnc;

/**
 * Subsystem to handle all outputs to SmartDashboard
 */
public class Dispatcher {
  private static Dispatcher dispatcher;

  private Dispatcher() {
  }

  public void update() {
    addNum("Voltage", Robot.adaptor.pdp.getVoltage());
    addSendable("DriveTrain", Robot.adaptor.driveTrain); //Will show command that is using the subsystem
    addSendable("Arm", Robot.adaptor.arm);
    addNum("Arm Encoder", Robot.adaptor.arm.getArmCount());
    addSendable("Reset Arm Encoder", new ResetArmEnc());
    addNum("Pressure Transducer", Robot.adaptor.pressureTransducer.getVoltage());
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
}
