/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robotMap.inputs.ShooterMap;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private static Shooter shooter;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid pitchSolenoid = null;
  
  public Shooter(){
  pitchSolenoid = new DoubleSolenoid(ShooterMap.SHOOTER_PITCH_SOLENOID_DEPLOY, ShooterMap.SHOOTER_PITCH_SOLENOID_RETRACT);
  }

  public void pushOut(){
    pitchSolenoid.set(Value.kForward);

  }

  public void retract(){
    pitchSolenoid.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

public static Shooter getInstance() {
  if(shooter == null) shooter = new Shooter();
  return shooter;
}
}
