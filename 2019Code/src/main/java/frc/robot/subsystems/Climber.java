/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robotMap.outputs.SolenoidMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  private static Climber climber;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static DoubleSolenoid frontPusher;
  private static DoubleSolenoid backPusher;

  private Climber() {
    frontPusher = new DoubleSolenoid(SolenoidMap.FPUSHER_A, SolenoidMap.FPUSHER_B);
    backPusher = new DoubleSolenoid(SolenoidMap.BPUSHER_A, SolenoidMap.BPUSHER_B);
  }

  public static Climber getInstance() {
    if(climber == null) climber = new Climber();
    return climber;
  }

  public void shiftFrontUp() {
    frontPusher.set(DoubleSolenoid.Value.kForward);
  }

  public void shiftFrontDown() {
    frontPusher.set(DoubleSolenoid.Value.kReverse);
  }

  public void shiftBackUp() {
    backPusher.set(DoubleSolenoid.Value.kReverse);
  }

  public void shiftBackDown() {
    backPusher.set(DoubleSolenoid.Value.kForward);
  }

  public void holdFront() {
    frontPusher.set(DoubleSolenoid.Value.kOff);
  }

  public void holdBack() {
    backPusher.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
