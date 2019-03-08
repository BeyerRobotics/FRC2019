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
public class SetBackState extends InstantCommand {
  private ClimberState state;
  /**
   * Add your docs here.
   */
  public SetBackState(ClimberState state) {
    super();
    requires(Robot.adaptor.climber);
    this.state = state;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    switch (state) {
      case HOLD:
        Robot.adaptor.climber.holdBack();
        break;
      case IN:
        Robot.adaptor.climber.shiftBackUp();
        Robot.adaptor.climber.backLeftHold();
        Robot.adaptor.climber.backRightHold();
        break;
      case OUT:
        Robot.adaptor.climber.openBack();
        Robot.adaptor.climber.backLeftDown();
        Robot.adaptor.climber.backRightDown();
        break;
      default:
        Robot.adaptor.climber.shiftBackUp();
        break;
    }
  }

}
