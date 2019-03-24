/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.robot.subsystems.*;
import frc.robotMap.inputs.CoprocessorMap;
import frc.robotMap.inputs.PressureTransducerMap;

/**
 * Declare and initialize all systems here, and call all systems from here. The
 * use of this class prevents 'Robots don't quit' error by implementing
 * singletons for every system. Call getInstance() methods when initializing.
 * 
 * @author Robert Smith
 *
 */
public class Adaptor {
	private static Adaptor adaptor;
	
	public DriverStation ds;

	public PowerDistributionPanel pdp;
	
	public Compressor comp;
	
	public AHRS navx;

	public AnalogInput pressureTransducer;

	public UsbCamera armCam;
	public UsbCamera topCam;
	
	public DriveTrain driveTrain;

	public Shifters shifters;

	public Shooter shooter;

	public Climber climber;

	public Arm arm;
	
	public Serial serial;

	public Vision vision;
	
	private Adaptor(){
		ds = DriverStation.getInstance();

		pdp = new PowerDistributionPanel();
		
		comp = new Compressor();
		
		navx = new AHRS(CoprocessorMap.NAVX_PORT);

		armCam = CameraServer.getInstance().startAutomaticCapture("arm", 0);
		armCam.setResolution(150, 150);
		topCam = CameraServer.getInstance().startAutomaticCapture("top", 1);
		topCam.setResolution(150, 150);
		
		driveTrain = DriveTrain.getInstance();

		shifters = Shifters.getInstance();

		shooter = Shooter.getInstance();

		climber = Climber.getInstance();
		
		arm = Arm.getInstance();

		pressureTransducer = new AnalogInput(PressureTransducerMap.PRESSURE_TRANSDUCER_PORT);

		serial = Serial.getInstance();

		vision = Vision.getInstance();
	}
	
	public static Adaptor getInstance(){
		if(adaptor == null)	adaptor = new Adaptor(); 
		return adaptor;
	}
}
