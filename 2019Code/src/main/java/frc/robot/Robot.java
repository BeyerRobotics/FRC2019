/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotStates.ClimberState;
import frc.robot.commands.ZeroYaw;
import frc.robot.commands.arm.ResetArmEnc;
import frc.robot.commands.climb.SetBackState;
import frc.robot.commands.climb.SetFrontState;

/**
 * The VM is configured to automatically run this class, and to call the.
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Adaptor adaptor;
  	public static OI oi;
	public static Log log;
	public static RobotStates robotStates;
	public static Dispatcher dispatcher;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		adaptor = Adaptor.getInstance();
		oi = OI.getInstance();
		log = Log.getInstance();	
		robotStates = new RobotStates();
		dispatcher = Dispatcher.getInstance();

		Scheduler.getInstance().add(new SetBackState(ClimberState.IN));
		Scheduler.getInstance().add(new SetFrontState(ClimberState.IN));
	}

	@Override
	public void robotPeriodic() {
		if(Math.abs(adaptor.navx.getPitch()) > 45) Log.fatal("Entire Robot", 
															"Help I've fallen and I can't get up");

		SmartDashboard.putNumber("Pressure", adaptor.pressureTransducer.getVoltage()*(165/5));

		SmartDashboard.putString("Gear", "" + adaptor.shifters.gear);

		SmartDashboard.putString("Front Master", ""+ adaptor.climber.frontMasterState);
		SmartDashboard.putString("Front Left", "" + adaptor.climber.frontLeftState);
		SmartDashboard.putString("Front Right", "" + adaptor.climber.frontRightState);
		SmartDashboard.putString("Back Master", ""+ adaptor.climber.backMasterState);
		SmartDashboard.putString("Back Left", "" + adaptor.climber.backLeftState);
		SmartDashboard.putString("Back Right", "" + adaptor.climber.backRightState);

		SmartDashboard.putNumber("Voltage", Robot.adaptor.pdp.getVoltage());
    	SmartDashboard.putData("DriveTrain", Robot.adaptor.driveTrain); //Will show command that is using the subsystem

    	SmartDashboard.putData("Arm", Robot.adaptor.arm);
		SmartDashboard.putNumber("Arm Encoder", Robot.adaptor.arm.getArmDegrees());
		SmartDashboard.putNumber("Raw Arm", Robot.adaptor.arm.getRaw());
    	SmartDashboard.putData("Reset Arm Encoder", new ResetArmEnc());

    	SmartDashboard.putNumber("Yaw", Robot.adaptor.navx.getYaw());
    	SmartDashboard.putData("Reset Yaw", new ZeroYaw());
		SmartDashboard.putNumber("pitch", Robot.adaptor.navx.getRoll());
		SmartDashboard.putNumber("roll", Robot.adaptor.navx.getPitch());

		SmartDashboard.putData("Shifters", Robot.adaptor.shifters);

		SmartDashboard.putNumber("Hottest Motor Temp", Robot.adaptor.driveTrain.getHottestTempature());

		adaptor.serial.update();
		
		// adaptor.serial.filter(adaptor.serial.read());
		// SmartDashboard.putNumber("center", adaptor.serial.getData("center"));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// Scheduler.getInstance().removeAll();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
