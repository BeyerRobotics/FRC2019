/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotStates.ClimberState;
import frc.robotMap.outputs.SolenoidMap;

/**
 * Subsystem to control any action of the climbing mechanism.  Front
 * pistons actuate on same solenoid, same for back.
 */
public class Climber extends Subsystem {
  private static Climber climber;

  public ClimberState frontState, backState;

  private static DoubleSolenoid frontPusher;
  private static DoubleSolenoid backPusher;

  public Climber() {
    frontPusher = new DoubleSolenoid(SolenoidMap.FPUSHER_A, SolenoidMap.FPUSHER_B);
    backPusher = new DoubleSolenoid(SolenoidMap.BPUSHER_A, SolenoidMap.BPUSHER_B);
  }

  public static Climber getInstance() {
    if(climber == null) climber = new Climber();
    return climber;
   }

  public void shiftFrontUp() {
    frontPusher.set(DoubleSolenoid.Value.kForward);
    frontState = ClimberState.IN;
  }

  public void shiftFrontDown() {
    frontPusher.set(DoubleSolenoid.Value.kReverse);
    frontState = ClimberState.OUT;
  }

  public void shiftBackUp() {
    backPusher.set(DoubleSolenoid.Value.kReverse);
    backState = ClimberState.IN;
  }

  public void shiftBackDown() {
    backPusher.set(DoubleSolenoid.Value.kForward);
    backState = ClimberState.OUT;
  }

  public void holdFront() {
    frontPusher.set(DoubleSolenoid.Value.kOff);
    frontState = ClimberState.HOLD;
  }

  public void holdBack() {
    backPusher.set(DoubleSolenoid.Value.kOff);
    backState = ClimberState.HOLD;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new HoldFront());
  }
}