// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2538.Steamworks;


import org.usfirst.frc2538.Steamworks.commands.AutoCommandGroupToo;
import org.usfirst.frc2538.Steamworks.commands.AutonomousCommand;
import org.usfirst.frc2538.Steamworks.commands.DriveForward;
import org.usfirst.frc2538.Steamworks.commands.Stop;
import org.usfirst.frc2538.Steamworks.subsystems.BallIntake;
import org.usfirst.frc2538.Steamworks.subsystems.GearSystem;
import org.usfirst.frc2538.Steamworks.subsystems.Pnewmatics;
import org.usfirst.frc2538.Steamworks.subsystems.Vision;
import org.usfirst.frc2538.Steamworks.subsystems.drivetrain;
import org.usfirst.frc2538.Steamworks.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command fowardCommand;
    Command stopCommand;
    CameraServer server;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static drivetrain drivetrain;
    public static Pnewmatics pnewmatics;
    public static Shooter shooter;
    public static GearSystem gearSystem;
    public static BallIntake ballIntake;
    public static Vision vision;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
   // public static NetworkTable networkTable;
    public Robot() {
        server = CameraServer.getInstance();
        server.setQuality(45);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new drivetrain();
        pnewmatics = new Pnewmatics();
        shooter = new Shooter();
        gearSystem = new GearSystem();
        ballIntake = new BallIntake();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        vision = new Vision();
        
      //  NetworkTable.setServerMode();
        //networkTable = NetworkTable.getTable("vision");
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        //networkTable.putNumber("test",0.5);
        
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutoCommandGroupToo();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        
      
        
}

  
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
