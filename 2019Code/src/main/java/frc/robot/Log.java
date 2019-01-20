package frc.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.util.Date;
import java.sql.Timestamp;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Class to track errors and events as they happen to assist user in debugging
 * @author FRC Team 3128 - Aluminum Narwhals
 * @author Robert Smith
 * @author Ben Calkins
 * @author Joshua Tapia
 *
 */

 public class Log {

    static String csvFile = "/media/sda1/log.csv"; // Change to directory of USB plugged into RIO

    static Date date;
    static Timestamp ts;
    static double remainingTime;
    static double time;

	private static Log log;

	private Log() {
	}
	
	public static Log getInstance() {
		if(log == null) log = new Log();
		return log;
	}

    /**
	 * Log a FATAL error, after which the robot cannot (properly) function.
	 * @param category
	 * @param message
	 */
	public static void fatal(String category, String message)
	{
		log("Fatal", category, message);
		
		//make it show up on the DS as well
		DriverStation.reportError("Fatal Error: " + message, true);
	}
	
	/**
	 * Log a failure which may kill one function or one thread, however the robot as a whole can keep functioning.
	 * @param category
	 * @param message
	 */
	public static void recoverable(String category, String message)
	{
		log("Recoverable", category, message);
		
		DriverStation.reportError("Error: " + message, true);

	}
	
	/**
	 * Log something which should not happen under normal circumstances and probably is a bug, but does not cause anything to crash.
	 * @param category
	 * @param message
	 */
	public static void unusual(String category, String message)
	{
		log("Unusual", category, message);
	}
	
	/**
	 * Log a semi-important message which the user should probably see, but does not indicate anything is broken.
	 */
	public static void info(String category, String message)
	{
		log("Info", category, message);
	}
	
	/**
	 * Log a message which is not important during normal operation, but is useful if you're trying to debug the robot.
	 * @param category
	 * @param message
	 */
	public static void debug(String category, String message)
	{
		log("Debug", category, message);
	}
	
	private static void log(String severity, String category, String message)
	{
        date = new Date();
        ts = new Timestamp(date.getTime());
        remainingTime = DriverStation.getInstance().getMatchTime();
        if(remainingTime <= 15 && DriverStation.getInstance().isAutonomous()) {
            time = Math.abs(remainingTime - 15);
        }
        else {
            time = Math.abs(remainingTime - 150);
        }

		System.out.println(String.format("[" + ts + "] *{%s}* [%s] %s", severity, category, message));
		
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(csvFile), true))){
            StringBuilder sb = new StringBuilder();
            
            sb.append(ts);
            sb.append(',');
            sb.append(time);
            sb.append(',');
			sb.append(severity);
			sb.append(',');
			sb.append(category);
			sb.append(',');
            sb.append(message);
            sb.append('\n');

            pw.append(sb.toString());
	        pw.close();
            System.out.println("Succesfully wrote csv");
		} catch (FileNotFoundException e) {
        	System.out.println("Failed to find file at " + csvFile);
        	DriverStation.reportError("Failed to find file at " + csvFile, true);
            e.printStackTrace();
        }
	}

 }