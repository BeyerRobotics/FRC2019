/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotStates.ArmLevel;
import frc.robot.commands.arm.SetArmPosition;
import frc.robotMap.outputs.ArmMap;
import frc.robotMap.outputs.MotorControllerMap;

/**
 * Subsystem to control the robot's arm
 */
public class Arm extends Subsystem{
  private static Arm arm;
  public ArmLevel position;
  private WPI_TalonSRX motor;
  double prevError = Double.NaN;
  double prevSetPoint = Double.NaN;
  double integral;
  double prevSet = 0;
  
  public Arm() {
    motor = new WPI_TalonSRX(MotorControllerMap.ARM_MOTOR);
    motor.setSelectedSensorPosition(0);
    SmartDashboard.putNumber("P", 1.0);
    SmartDashboard.putNumber("I",0.0);
    SmartDashboard.putNumber("D", 0.0);
  }

  /**
   * Using a PID controller, move the arm to a given setpoint
   * @param level The level to move the arm to (TOP, MIDDLE, BOTTOM, STOW)
   * @author Joshua Tapia
   */
  public void moveArm(ArmLevel level){
    double setpoint = levelToSetpoint(level);

    if(setpoint != prevSet) this.integral = 0;
    double error = setpoint - motor.getSelectedSensorPosition()/11.37;
    SmartDashboard.putNumber("error", error);
    this.integral += ((error/360)*0.02);

    if(prevError == Double.NaN) prevError = 0.0;
  
    double out = (((error/360) * SmartDashboard.getNumber("P", 3.0)) + (SmartDashboard.getNumber("I", 0.0) * this.integral)  + (SmartDashboard.getNumber("D", 0.0) * ((prevError/360)/0.02)));
    
    if(error < 0.5 || error > -0.5) {
      if(out > 1 && out > 0) out = 1;
      else if(out < -1 && out < 0) out = -1;
      else if(out < 0.01 && out > 0) out = 0.0;
      else if(out > -0.01 && out < 0) out = 0.0;
      
      SmartDashboard.putNumber("out", out);
      motor.set(out);
    } else {
      motor.set(0);
    }

    prevError = error;
    prevSet = setpoint;
  }

  /**
   * Convert ArmLevel enumerator to an encoder value
   * @param level Target ArmLevel from RobotStates
   * @return The corresponding encoder value
   */
  private int levelToSetpoint(ArmLevel level) {
    switch(level) {
      case TOP:
        return ArmMap.TOP_VAL;
      case MIDDLE:
        return ArmMap.MID_VAL;
      case BOTTOM:
        return ArmMap.BOTTOM_VAL;
      case STOW:
        return ArmMap.STOW_VAL;
      default:
        return ArmMap.STOW_VAL;
    }
  }

  public void resetEnc() {
    motor.setSelectedSensorPosition(0);
  }

  public static Arm getInstance(){
    if (arm == null) arm = new Arm();
    return arm;
  }

  public void halt(){
    motor.set(0);
  }

  public void move(double speed){
      motor.set(speed);
  }

  public double getArmCount(){
    return motor.getSelectedSensorPosition()/11.37;
  }

  public double getArmRate(){
    return motor.getSelectedSensorVelocity();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new SetArmPosition(ArmLevel.STOW));
  }
}
