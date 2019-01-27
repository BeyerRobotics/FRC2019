/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robotMap.inputs;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Add your docs here.
 */
public class CoprocessorMap {
    public static final SPI.Port NAVX_PORT = SPI.Port.kMXP;

    public static final SerialPort.Port SERIAL_PORT = SerialPort.Port.kMXP;
}
