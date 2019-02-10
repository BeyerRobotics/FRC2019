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
import frc.robot.commands.shoot.Stow;
import frc.robotMap.outputs.SolenoidMap;

/**
 * This subsystem allows the Solenoids to deploy and retract in order to control the shooter. 
 * @author Emilio Satavu
 * @author Isabella Perez
 */
public class Shooter extends Subsystem {
  private static Shooter shooter;
  DoubleSolenoid pitchSolenoid;
  
  public Shooter(){
    pitchSolenoid = new DoubleSolenoid(SolenoidMap.SHOOTER_PITCH_SOLENOID_DEPLOY, SolenoidMap.SHOOTER_PITCH_SOLENOID_RETRACT);
  }

  public void pushOut(){
    pitchSolenoid.set(Value.kForward);
  }

  public void retract(){
    pitchSolenoid.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Stow());
  }

  public static Shooter getInstance() {
    if(shooter == null) shooter = new Shooter();
    return shooter;
  }
}
