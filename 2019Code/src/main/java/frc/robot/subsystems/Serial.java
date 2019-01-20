/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robotMap.inputs.CoprocessorMap;
/**
 * Add your docs here.
 */
public class Serial extends Subsystem {
  private static Serial serial;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private String input;
  private String message = "";

  private Character startChar = '|';
  private Character valueChar = ':';
  private Character endChar = '/';

  public static SerialPort arduino;

  public Serial() {
    arduino = new SerialPort(9600, CoprocessorMap.SERIAL_PORT);
  }

  public static Serial getInstance() {
    if(serial == null) serial = new Serial();
    return serial;
  }

  public void write(String variable, double value) {
    arduino.writeString(startChar+ variable +valueChar+ value +endChar);
  }

  public void writeColor() {
    /*arduino.writeString(startChar + "r" + valueChar + 1 + endChar +
                        startChar + "g" + valueChar + 2 + endChar +
                        startChar + "b" + valueChar + 3 + endChar);*/
    arduino.writeString("69");
  }

  public String read() {
    input = arduino.readString();
    if(!input.equals("")) message = input;
    return message;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
