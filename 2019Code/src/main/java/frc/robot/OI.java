/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotStates.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.climb.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shift.Shift;
import frc.robot.commands.shoot.*;
import frc.robotMap.inputs.JoystickMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static OI oi;

	private static final int JOYSTICK_NUM = 3;
	private static final int BUTTON_NUM = 12;

	private static Joystick gunnerStick = new Joystick(JoystickMap.GUNNER_STICK);
	private static Joystick driver = new Joystick(JoystickMap.DRIVER);
	private static Joystick gunnerButton = new Joystick(JoystickMap.GUNNER_BUTTON);

	private static Joystick[] joysticks = { gunnerButton, gunnerStick, driver };
	private static JoystickButton[][] buttons = new JoystickButton[JOYSTICK_NUM][BUTTON_NUM];

	public OI(){
		createButtons();
		
		/* BEN */
		//Gripper
		getJoystickButton(1, 2).whenPressed(new PushOut());
		getJoystickButton(1, 2).whenReleased(new Stow());

		//Climb commands
		getJoystickButton(1, 8).whenPressed(new LevelUp(ClimbLevel.GROUND));  //uncomment this for auto
		getJoystickButton(1, 10).whenPressed(new LevelUp(ClimbLevel.FIRST));  //uncomment this for auto
		getJoystickButton(1, 12).whenPressed(new LevelUp(ClimbLevel.SECOND));  //uncomment this for auto
		getJoystickButton(1, 7).whenPressed(new SetFrontState(ClimberState.OUT));  //uncomment this for manual
		getJoystickButton(1, 7).whenReleased(new SetFrontState(ClimberState.HOLD));
		getJoystickButton(1, 9).whenPressed(new SetBackState(ClimberState.OUT));  //uncomment this for manual
		getJoystickButton(1, 5).whenPressed(new SetFrontState(ClimberState.HOLD));
		getJoystickButton(1, 6).whenPressed(new SetBackState(ClimberState.HOLD));
		// getJoystickButton(1, 11).whenPressed(new SetFrontState(ClimberState.IN));  //uncomment this for manual
		// getJoystickButton(1, 12).whenPressed(new SetBackState(ClimberState.IN));  //uncomment this for manual
		getJoystickButton(1, 3).whenPressed(new SetFrontState(ClimberState.IN));
		getJoystickButton(1, 4).whenPressed(new SetBackState(ClimberState.IN));

		//Manual Arm
		getJoystickButton(1, 1).whileHeld(new MoveArm(getJoystick(1)));

		//Arm PID commands
		getJoystickButton(0, 3).whenPressed(new SetArmPosition(ArmLevel.TOP));
		getJoystickButton(0, 2).whenPressed(new SetArmPosition(ArmLevel.MIDDLE));
		getJoystickButton(0, 1).whenPressed(new SetArmPosition(ArmLevel.BOTTOM));
		getJoystickButton(0, 5).whenPressed(new SetArmPosition(ArmLevel.STOW));
		getJoystickButton(0, 6).whenPressed(new ResetArmEnc());
		
		
		/* DAVID */
		getJoystickButton(2, 6).whileHeld(new TankDrive(getJoystick(2)));
		getJoystickButton(2, 2).whileHeld(new SlowTankDrive(getJoystick(2)));
		getJoystickButton(2, 4).whileHeld(new DriveStraight(getJoystick(2), 0));
		getJoystickButton(2, 1).whenPressed(new PushOut());
		getJoystickButton(2, 1).whenReleased(new Stow());
	}

	public static OI getInstance() {
		if (oi == null) {
			oi = new OI();
		}
		return oi;
	}

	/**
	 * Populates the array "buttons" with buttons from 1 to 12 of each joystick.
	 * 
	 * @author Vincent Benenati
	 * @author Robert Smith
	 */
	private void createButtons() {
		for (int joystickNum = 0; joystickNum < JOYSTICK_NUM; joystickNum++) {
			for (int buttonNum = 0; buttonNum < BUTTON_NUM; buttonNum++) {
				buttons[joystickNum][buttonNum] = new JoystickButton(joysticks[joystickNum], buttonNum + 1);
			}
		}
	}

	/**
	 * Enables the user to input joystick port and desired button number, and
	 * receive a usable button.
	 *
	 * @author Vincent Benenati
	 * @author Robert Smith
	 * @param joystickNum Joystick number from 0 to 2
	 * @param buttonNum   Button number from 1 to 12
	 * @return JoystickButton
	 */
	public JoystickButton getJoystickButton(final int joystickNum, final int buttonNum) {
		return buttons[joystickNum][buttonNum - 1];
	}

	/**
	 * Disregards unintentional input calibrated by the deadzone
	 *
	 * @author Vincent Benenati
	 * @param joystick the joystick to be monitored
	 * @param deadzone the size of deadzone from 0 to 1
	 * @return true if joystick is within deadzone
	 *         <p>
	 *         false if joystick is outside of deadzone
	 *
	 */
	public static boolean isDeadZone(Joystick joystick, double deadzone) {
		if (Math.pow(joystick.getX(), 2) + Math.pow(joystick.getY(), 2) < Math.pow(deadzone, 2)) {
			return true;
		}
		return false;
	}

	public Joystick getJoystick(final int joystickNum) {
		return joysticks[joystickNum];
	}

	public double average(double val1, double val2) {
		return (val1 + val2) / 2;
	}
}
