/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robotMap.inputs.IRMap;

/**
 * Add your docs here.
 */
public class IRArray extends Subsystem {

  private IRArray irArray;
  
  private AnalogInput lIR, cIR, rIR; //Left, center, and right IR sensors

  private static int threshold = 50;

  private IRArray() {
    lIR = new AnalogInput(IRMap.IR_L_PORT);
    cIR = new AnalogInput(IRMap.IR_C_PORT);
    rIR = new AnalogInput(IRMap.IR_R_PORT);
  }

  @Override
  public void initDefaultCommand() {
  }

  /**
   * Function to check where the line on the ground falls with respect to the
   * center of the robot.
   * 
   * @return -1, 0, or 1, corresponding to on left, center, or right
   */
  public int posOnLine() {
    if(lIR.get() > threshold) return -1;
    else if(rIR.get() > threshold) return 1;
    else if((cIR.get() > threshold) && 
            (lIR.get() < threshold) && 
            (rIR.get() < threshold)) return 0;
  }

  public IRArray getInstance() {
    if(irArray == null) irArray = new IRArray();
    return irArray;
  }

}
