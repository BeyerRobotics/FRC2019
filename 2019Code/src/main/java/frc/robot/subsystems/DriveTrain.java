/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Log;
import frc.robot.Robot;
import frc.robot.commands.drive.Halt;
import frc.robot.triggers.IsBrowningOut;
import frc.robotMap.AutoMap;
import frc.robotMap.outputs.MotorControllerMap;

/**
 * Subsystem to control all functions of drive train.
 */

public class DriveTrain extends Subsystem {
  private static DriveTrain driveTrain;
	
	/**Put all ports for drive train motors here, front, back, middle for each side.
	 * The first port given will act as a 'master' to the remaining motors.
	 * To configure for 4 motor drive, simply exclude a 'middle' port. */
	private static int[] DTL_IDs = { //Drive Train Left
			MotorControllerMap.DTL_FRONT,
			MotorControllerMap.DTL_BACK,
			MotorControllerMap.DTL_MIDDLE
	};
	private static boolean[] DTL_INVs = { //Drive Train Left
			MotorControllerMap.DTL_FRONT_INV,
			MotorControllerMap.DTL_BACK_INV,
			MotorControllerMap.DTL_MIDDLE_INV
	};
	
	private static final int[] DTR_IDs = { //Drive Train Right
			MotorControllerMap.DTR_FRONT, 
			MotorControllerMap.DTR_BACK,
			MotorControllerMap.DTR_MIDDLE
	};
	private static boolean[] DTR_INVs = { //Drive Train Right
			MotorControllerMap.DTR_FRONT_INV,
			MotorControllerMap.DTR_BACK_INV,
			MotorControllerMap.DTR_MIDDLE_INV
	};


	private CANSparkMax leftMaster, leftSlave, leftSlaveB;
	private CANEncoder leftEncoder;
	
	private CANSparkMax rightMaster, rightSlave, rightSlaveB;
	private CANEncoder rightEncoder;

	private DifferentialDrive robotDrive;
	
	private int startOfBrownOut = 0;

    /**Initialize motors and drive encoders here*/
    public DriveTrain() {	
    	leftMaster = new CANSparkMax(DTL_IDs[0],  CANSparkMaxLowLevel.MotorType.kBrushless);
		leftMaster.setInverted(DTL_INVs[0]);
		leftMaster.setOpenLoopRampRate(0.5);
    	leftSlave = new CANSparkMax(DTL_IDs[1], CANSparkMaxLowLevel.MotorType.kBrushless);
		leftSlave.follow(leftMaster, DTL_INVs[1]);
		leftSlave.setOpenLoopRampRate(0.5);
		leftEncoder = leftMaster.getEncoder();
    	
    	rightMaster = new CANSparkMax(DTR_IDs[0], CANSparkMaxLowLevel.MotorType.kBrushless);
		rightMaster.setInverted(DTR_INVs[0]);
		rightMaster.setOpenLoopRampRate(0.5);
    	rightSlave = new CANSparkMax(DTR_IDs[1], CANSparkMaxLowLevel.MotorType.kBrushless);
		rightSlave.follow(rightMaster, DTR_INVs[1]);
		rightSlave.setOpenLoopRampRate(0.5);
		rightEncoder = rightMaster.getEncoder();
    	
    	if(DTL_IDs.length == 3){
			leftSlaveB = new CANSparkMax(DTL_IDs[2], CANSparkMaxLowLevel.MotorType.kBrushless);
			leftSlaveB.setOpenLoopRampRate(0.5);
			leftSlaveB.follow(leftMaster, DTL_INVs[2]);	
			Log.info("Drive Train", "Initialized with 6 motors");
    	} else leftSlaveB.close();
    	if(DTR_IDs.length == 3){
			rightSlaveB = new CANSparkMax(DTR_IDs[2], CANSparkMaxLowLevel.MotorType.kBrushless);
			rightSlaveB.setOpenLoopRampRate(0.5);
			rightSlaveB.follow(rightMaster, DTR_INVs[2]);
		} else rightSlaveB.close();
    	
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);
    }
    
    public static DriveTrain getInstance(){
		if(driveTrain == null) driveTrain = new DriveTrain();
    	return driveTrain;
    }

    @Override
    public void initDefaultCommand() {
		setDefaultCommand(new Halt()); 
    }
    
    /*Begin Drive methods*/
    public void arcadeDrive(Joystick joystick){
		if(IsBrowningOut.get()) {
			robotDrive.setMaxOutput(0.75);
			if(startOfBrownOut == 0) {
				Log.recoverable("Voltage", "The voltage dropped below 9.5V.");
				startOfBrownOut = 1;
			}
		} else {
			startOfBrownOut = 0;
			robotDrive.setMaxOutput(1);
		}
		robotDrive.arcadeDrive(-joystick.getY(), joystick.getX());
    }
    
    public void tankDrive(double left, double right){
		if(IsBrowningOut.get()) {
			robotDrive.setMaxOutput(0.75);
			if(startOfBrownOut == 0) {
				Log.recoverable("Voltage", "The voltage dropped below 9.5V.");
				startOfBrownOut = 1;
			}
		} else {
			startOfBrownOut = 0;
			robotDrive.setMaxOutput(1);
		}
		robotDrive.tankDrive(left, right);
	}
	
    public void driveStraight(double speed){
    	double angle = Robot.adaptor.navx.getAngle();
    	double curve = -angle * AutoMap.kP;
    	robotDrive.curvatureDrive(speed, curve, false);
	}

	public void halt() {
		robotDrive.stopMotor();
	}

	public void turn(double error) {
		double curve = error * AutoMap.kP;
		robotDrive.tankDrive(curve, -curve);
	}
    
    /*Begin Encoder methods*/
    // public void reset(){
	// 	leftEnc.                         //Reset method has not been added
    // }
    
    public double getDTLCount(){
    	return leftEncoder.getPosition();
    }
    
    public double getDTRCount(){
    	return rightEncoder.getPosition();
    }
    
    public double getDTLRate(){
    	return rightEncoder.getVelocity();
    }
    
    public double getDTRRate(){
    	return rightEncoder.getVelocity();
	}
}
