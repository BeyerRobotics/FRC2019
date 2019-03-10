/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotStates.ClimberState;
import frc.robotMap.outputs.SolenoidMap;

/**
 * Subsystem to control any action of the climbing mechanism.  Front
 * pistons actuate on same solenoid, same for back.
 */
public class Climber extends Subsystem {
  private static Climber climber;

  public ClimberState frontMasterState, frontLeftState, frontRightState, backMasterState, backLeftState, backRightState;

  private static DoubleSolenoid frontMaster;
  private static Solenoid frontLeftPusher;
  private static Solenoid frontRightPusher;
  private static DoubleSolenoid backMaster;
  private static Solenoid backLeftPusher;
  private static Solenoid backRightPusher;

  public Climber() {
    frontMaster = new DoubleSolenoid(1, SolenoidMap.FPUSHER_A, SolenoidMap.FPUSHER_B);
    frontLeftPusher = new Solenoid(0, SolenoidMap.FLPUSHER);
    frontRightPusher = new Solenoid(0, SolenoidMap.FRPUSHER);
    backMaster = new DoubleSolenoid(1, SolenoidMap.BPUSHER_A, SolenoidMap.BPUSHER_B);
    backLeftPusher = new Solenoid(0, SolenoidMap.BLPUSHER);
    backRightPusher = new Solenoid(0 ,SolenoidMap.BRPUSHER);
  }

  public static Climber getInstance() {
    if(climber == null) climber = new Climber();
    return climber;
   }

  public void shiftFrontUp() {
    frontMaster.set(DoubleSolenoid.Value.kForward);
    frontMasterState = ClimberState.IN;
  }

  public void openFront() {
    frontMaster.set(DoubleSolenoid.Value.kReverse);
    frontMasterState = ClimberState.OUT;
  }

  public void frontLeftDown() {
    frontLeftPusher.set(true);
    frontLeftState = ClimberState.OUT;
  }

  public void frontLeftHold() {
    frontLeftPusher.set(false);
    frontLeftState = ClimberState.HOLD;
  }

  public void frontRightDown() {
    frontRightPusher.set(true);
    frontRightState = ClimberState.OUT;
  }

  public void frontRightHold() {
    backRightPusher.set(false);
    frontRightState = ClimberState.HOLD;
  }

  public void openBack() {
    backMaster.set(DoubleSolenoid.Value.kForward);
    backMasterState = ClimberState.OUT;
  }

  public void shiftBackUp() {
    backMaster.set(DoubleSolenoid.Value.kReverse);
    backMasterState = ClimberState.IN;
  }

  public void backLeftDown() {
    backLeftPusher.set(true);
    backLeftState = ClimberState.OUT;
  }

  public void backLeftHold() {
    backLeftPusher.set(false);
    backLeftState = ClimberState.HOLD;
  }

  public void backRightDown() {
    backRightPusher.set(true);
    backRightState = ClimberState.OUT;
  }

  public void backRightHold() {
    backRightPusher.set(false);
    backRightState = ClimberState.HOLD;
  }

  public void holdFront() {
    frontMaster.set(DoubleSolenoid.Value.kOff);
    frontMasterState = ClimberState.HOLD;
  }

  public void holdBack() {
    backMaster.set(DoubleSolenoid.Value.kOff);
    backMasterState = ClimberState.HOLD;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new HoldFront());
  }
}
