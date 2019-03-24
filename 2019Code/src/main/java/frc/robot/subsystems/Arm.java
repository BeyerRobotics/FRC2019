/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotStates.ArmLevel;
import frc.robot.commands.arm.StopArm;
import frc.robotMap.outputs.ArmMap;
import frc.robotMap.outputs.MotorControllerMap;

/**
 * Subsystem to control the robot's arm
 */
public class Arm extends Subsystem{
  private static Arm arm;

  public ArmLevel position;
  private WPI_TalonSRX motor;

  private double prevError = Double.NaN;
  private double integral;
  private double prevSet = Double.NaN;
  
  public Arm() {
    motor = new WPI_TalonSRX(MotorControllerMap.ARM_MOTOR);
    // motor.configPeakCurrentLimit(15);
    // motor.configContinuousCurrentLimit(15);
    // motor.configPeakCurrentDuration(0);
    motor.configOpenloopRamp(1);
    // motor.enableCurrentLimit(true);
    SmartDashboard.putNumber("P", 1.5);
    SmartDashboard.putNumber("I", 2.0);
    SmartDashboard.putNumber("D", 0.0);
  }

  /**
   * Using a PID controller, move the arm to a given setpoint
   * @param level The level to move the arm to (TOP, MIDDLE, BOTTOM, STOW)
   * @author Joshua Tapia
   */
  public void moveArm(ArmLevel level){
    double setpoint = levelToSetpoint(level); // Convert to an encoder setpoint

    if(setpoint != prevSet) this.integral = 0;
    double error = setpoint - motor.getSelectedSensorPosition()/-11.37;
    SmartDashboard.putNumber("error", error);
    this.integral += ((error/126)*0.02);

    if(prevError == Double.NaN) prevError = 0.0;
  
    double out = (((error/126) * SmartDashboard.getNumber("P", 1.0)) + (SmartDashboard.getNumber("I", 0.0) * this.integral) + (SmartDashboard.getNumber("D", 0.0) * (((error - prevError)/126)/0.02)));
    
    if(Math.abs(error) < 20) motor.configOpenloopRamp(0); 
    else motor.configOpenloopRamp(1);

    if(error > 0.5 || error < -0.5) {
      if(out > 1) out = 1;
      else if(out < 0) out = 0.25 * out;
      else if(out < -1) out = -1;
      else if(out < 0.01 && out > -0.01) out = 0.0;
      
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
        SmartDashboard.putString("SetPt", "Top");
        return ArmMap.TOP_VAL;
      case MIDDLE:
        SmartDashboard.putString("SetPt", "Mid");
        return ArmMap.MID_VAL;
      case BOTTOM:
        SmartDashboard.putString("SetPt", "Bottom");
        return ArmMap.BOTTOM_VAL;
      case STOW:
        SmartDashboard.putString("SetPt", "Stow");
        return ArmMap.STOW_VAL;
      default:
        SmartDashboard.putString("SetPt", "Stow");
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

  public void move(Joystick joy){
      motor.set(-joy.getY() * .75);
  }

  public double getArmDegrees(){
    return motor.getSelectedSensorPosition()/-11.37;
  }

  public double getRaw() {
    return motor.getSelectedSensorPosition();
  }

  public double getArmRate(){
    return motor.getSelectedSensorVelocity();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new StopArm());
  }
}
