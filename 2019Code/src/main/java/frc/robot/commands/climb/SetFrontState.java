/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotStates.ClimberState;

/**
 * Add your docs here.
 */
public class SetFrontState extends InstantCommand {
  private ClimberState state;
  /**
   * Add your docs here.
   */
  public SetFrontState(ClimberState state) {
    super();
    requires(Robot.adaptor.climber);
    this.state = state;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    switch (state) {
      case HOLD:
        Robot.adaptor.climber.holdFront();
        break;
      case IN:
        Robot.adaptor.climber.shiftFrontUp();
        Robot.adaptor.climber.frontLeftHold();
        Robot.adaptor.climber.frontRightHold();
        break;
      case OUT:
        Robot.adaptor.climber.openFront();
        Robot.adaptor.climber.frontLeftDown();
        Robot.adaptor.climber.frontRightDown();
        break;
      default:
        Robot.adaptor.climber.shiftFrontUp();
        break;
    }
  }
}
