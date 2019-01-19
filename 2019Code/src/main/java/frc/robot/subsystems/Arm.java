/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robotMap.outputs.MotorControllerMap;
import frc.robotMap.outputs.SolenoidMap;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  private Arm arm;

  private WPI_TalonSRX armDriver;

  private Solenoid armBrake;

  private Arm() {
    armDriver = new WPI_TalonSRX(MotorControllerMap.ARM);
    armBrake = new Solenoid(SolenoidMap.ARM);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void set(double y) {
    armBrake.set(true);
    armDriver.set(y);
  }

  public void stop() {
    armDriver.set(0);
    armBrake.set(false);
  }

  public Arm getInstance() {
    if(arm == null) arm = new Arm();
    return arm;
  }
}
