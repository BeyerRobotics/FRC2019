/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robotMap.outputs.SolenoidMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class Shifters extends Subsystem {
  private static Shifters shifters;

  private DoubleSolenoid pancakes;

  public boolean state;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Shifters() {
    Robot.dispatcher.addSendable("Shifters", this);

    pancakes = new DoubleSolenoid(SolenoidMap.SHIFTER_A, SolenoidMap.SHIFTER_B);
  }

  public static Shifters getInstance() {
    if(shifters == null) shifters = new Shifters();
    return shifters;
  }

  public void shiftHigh() {
    pancakes.set(DoubleSolenoid.Value.kForward);
    state = true;
    Robot.dispatcher.addBool("Gear", true);
  }

  public void shiftLow() {
    pancakes.set(DoubleSolenoid.Value.kReverse);
    state = false;
    Robot.dispatcher.addBool("Gear", false);
  }

  @Override
  public void initDefaultCommand() {
  }
}
