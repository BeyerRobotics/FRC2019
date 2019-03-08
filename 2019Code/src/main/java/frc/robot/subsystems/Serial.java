/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.triggers.IsBrowningOut;
import frc.robotMap.inputs.CoprocessorMap;
/**
 * Subsystem to handle communications with an Arduino over serial.
 * 
 * @author Robert Smith
 * @author Ben Fichtenkort
 */
public class Serial extends Subsystem {
  private static Serial serial;

  private boolean initialized = false;

  private static final Character startChar = '|';
  private static final Character valueChar = ':';
  private static final Character endChar = '/';

  private HashMap<String, Integer> varMap = new HashMap<>();

  private static SerialPort arduino;

  public Serial() {
    try {
      arduino = new SerialPort(9600, CoprocessorMap.SERIAL_PORT);
      System.out.println("Successfully initalized arduino");
      initialized = true;
    } catch (Exception e) {
      System.out.println("Failed to initalize arduino");
      initialized = false;
    }
  }

  public static Serial getInstance() {
    if(serial == null) serial = new Serial();
    return serial;
  }

  /**
   * Format information to be sent over serial.
   * 
   * @param variables Keys to label variables to be tracked.
   * @param values Integer values assigned to given keys.
   * @author Robert Smith
   */
  public void write(String[] variables, Integer[] values) {
    if(initialized) {
      StringBuilder sb = new StringBuilder();
      for(int i=0;i<variables.length;i++) {
        sb.append(startChar + variables[i] + valueChar + values[i] + endChar); 
      }
      arduino.writeString(sb.toString());
    }
  }

  /**
   * Update local instance of information from serial.  Only
   * changes if something is new and the arduino is sending something.
   * 
   * @return The unformatted string of information from serial.
   */
  public String read() {
    if(initialized) {
      String input = arduino.readString();
      String message = "";
      int startsAt=0,endsAt=0;

      if(!input.equals("")) {
        startsAt = input.indexOf(startChar, endsAt);
        endsAt = input.indexOf(endChar, endsAt);

        if(endsAt>startsAt && startsAt>-1 && endsAt>-1) message = input.substring(startsAt, endsAt+1);
      }
      return message;
    } else return "";
  }

  /**
   * Filter information from serial and add variables to
   * a local hash map
   * 
   * @param read The input from the serial. Use read().
   * @author Robert Smith
   */
  public void filter(String read) {
    if(initialized) {
      int startsAt=0,splitsAt=0,endsAt=0;
      String name="",value="";

      while(read.length() > 0) {
        startsAt = read.indexOf(startChar, endsAt);
        splitsAt = read.indexOf(valueChar, endsAt);
        endsAt = read.indexOf(endChar, endsAt);

        name = read.substring(startsAt+1, splitsAt);
        value = read.substring(splitsAt+1, endsAt);

        read = read.substring(endsAt+1);

        varMap.put(name,stringToInt(value));
        SmartDashboard.putString(name, value);
      }
    }
  }

  /**
   * Send values over serial that will be constantly updated.
   */
  public void update() {
    // if(IsBrowningOut.get()) varMap.put("b",1); //"b" stands for brownout, 1 is true
    // else varMap.put("b",0);

    if(Robot.adaptor.ds.getAlliance() == Alliance.Red) varMap.put("c",0); //"c" stands for team color, 1 is blue
    else varMap.put("c",1);

    if(Robot.adaptor.ds.isDisabled()) varMap.put("d",1); //"d" stands for disabled (as in the robot), 1 is true
    else varMap.put("d",0);

    String[] names = {"b","c","d"};
    Integer[] values = {varMap.get("b"),varMap.get("c"),varMap.get("d")};
    write(names, values);
  }

  /**
   * Getter for hash map of variables from serial.
   * 
   * @param key The string assigned to the value you want to get.
   * @return The stored variable assigned to the given key.
   */
  public int getData(String key) {
    if(varMap.get(key) != null) return varMap.get(key);
    else return 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Convert string of numbers into an integer.
   */
  static int stringToInt(String input){
		try {
			int i = Integer.parseInt(input);
			return i;
		} catch(NumberFormatException nfe) {
			System.out.println(nfe);
		}
		return 0;
  }
  
  /**
   * Convert boolean to either a 0 or 1.
   */
  static int boolToInt(boolean input) {
    if(input) return 1;
    else return 0;
  }
}
