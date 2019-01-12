/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robotMap.inputs;

/**
 * Add your docs here.
 */
public class EncoderMap {
    //Left
    public static final int DTL_A = 2;
    public static final int DTL_B = 3;
    public static final boolean DTL_INVERTED = false;
    public static final int DTL_TICKS_PER_REV = 200;
    //Right
    public static final int DTR_A = 0;
    public static final int DTR_B = 1;
    public static final boolean DTR_INVERTED = true;
    public static final int DTR_TICKS_PER_REV = 200;

    public static final double ENC_GEAR_CONVERSION = 15./42.;
}
