/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotStates {

    public static DriveGear driveGear;
    public static ClimbLevel climbLevel;

    public enum DriveGear {
        HIGH, LOW
    }

    public enum ClimbLevel {
        GROUND, FIRST, SECOND
    }

}
