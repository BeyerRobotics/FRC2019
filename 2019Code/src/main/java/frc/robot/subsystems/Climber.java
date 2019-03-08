/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
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

  public ClimberState frontState, backMasterState, backLeftState, backRightState;

  private static DoubleSolenoid frontPusher;
  private static DoubleSolenoid backMaster;
  private static DigitalOutput backLeftPusher;
  private static DigitalOutput backRightPusher;

  public Climber() {
    frontPusher = new DoubleSolenoid(SolenoidMap.FPUSHER_A, SolenoidMap.FPUSHER_B);
    backMaster = new DoubleSolenoid(SolenoidMap.BPUSHER_A, SolenoidMap.BPUSHER_B);
    backLeftPusher = new DigitalOutput(SolenoidMap.BLPUSHER);
    backRightPusher = new DigitalOutput(SolenoidMap.BRPUSHER);
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
    frontPusher.set(DoubleSolenoid.Value.kOff);
    frontState = ClimberState.HOLD;
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
