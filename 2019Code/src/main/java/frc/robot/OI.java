/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotStates.ClimbLevel;
import frc.robotMap.inputs.JoystickMap;
import frc.robot.RobotStates.ArmLevel;
import frc.robot.commands.drive.*;
import frc.robot.commands.climb.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.shift.*;
import frc.robot.commands.shoot.Shoot;

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
		getJoystickButton(1, 2).whenPressed(new Shoot());

		//Climb commands
		getJoystickButton(1, 8).whenPressed(new LevelUp(ClimbLevel.GROUND));
		getJoystickButton(1, 10).whenPressed(new LevelUp(ClimbLevel.FIRST));
		getJoystickButton(1, 12).whenPressed(new LevelUp(ClimbLevel.SECOND));
		getJoystickButton(1, 4).whenPressed(new ReleaseFront());
		getJoystickButton(1, 5).whenPressed(new ReleaseBack());

		//Arm PID commands
		getJoystickButton(1, 1).whileHeld(new MoveArm(getJoystick(0)));
		getJoystickButton(1, 11).whenPressed(new SetArmPosition(ArmLevel.TOP));
		getJoystickButton(1, 9).whenPressed(new SetArmPosition(ArmLevel.MIDDLE));
		getJoystickButton(1, 7).whenPressed(new SetArmPosition(ArmLevel.BOTTOM));

		//Backup Arm PID commands
		getJoystickButton(0, 3).whenPressed(new SetArmPosition(ArmLevel.TOP));
		getJoystickButton(0, 2).whenPressed(new SetArmPosition(ArmLevel.MIDDLE));
		getJoystickButton(0, 1).whenPressed(new SetArmPosition(ArmLevel.BOTTOM));
		getJoystickButton(0, 5).whenPressed(new SetArmPosition(ArmLevel.STOW));
		
		/* DAVID */
		getJoystickButton(2, 6).whileHeld(new TankDrive(getJoystick(2).getX(), getJoystick(2).getZ()));
		getJoystickButton(2, 2).whileHeld(new SlowTankDrive(getJoystick(2).getX(), getJoystick(2).getZ()));
		getJoystickButton(2, 1).whileHeld(new StraightShift(getJoystick(2).getY()));
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
}
