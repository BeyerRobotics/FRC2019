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
  private static DoubleSolenoid pusher;

  private Climber() {
    pusher = new DoubleSolenoid(SolenoidMap.PUSHER_A, SolenoidMap.PUSHER_B);
  }

  public static Climber getInstance() {
    if(climber == null) climber = new Climber();
    return climber;
  }

  public void shiftUp() {
    pusher.set(DoubleSolenoid.Value.kForward);
  }

  public void shiftDown() {
    pusher.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
