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

import org.usfirst.frc2538.Steamworks.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shiftLow;
    public JoystickButton shiftHigh;
    public JoystickButton tiltButtonOut;
    public JoystickButton tiltbuttonIn;
    public JoystickButton mirrorDrive;
    public Joystick driveJoystick;
    public JoystickButton aimShooter;
    public JoystickButton activateShooter;
    public JoystickButton gateOpen;
    public JoystickButton gateClose;
    public JoystickButton lightsOn;
    public JoystickButton lightsOff;
    public JoystickButton angleShooter;
    public JoystickButton shooterStart;
    public JoystickButton intakeBall;
    public JoystickButton stopShooter;
    public Joystick secondaryJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        secondaryJoystick = new Joystick(1);
        
        stopShooter = new JoystickButton(secondaryJoystick, 12);
        stopShooter.whileHeld(new ShooterStop());
        intakeBall = new JoystickButton(secondaryJoystick, 4);
        intakeBall.whileHeld(new toggleBallIntake());
        shooterStart = new JoystickButton(secondaryJoystick, 11);
        shooterStart.whenPressed(new StartShooter());
        angleShooter = new JoystickButton(secondaryJoystick, 1);
        angleShooter.whileHeld(new shooterAngle());
        lightsOff = new JoystickButton(secondaryJoystick, 7);
        lightsOff.whenReleased(new LightOff());
        lightsOn = new JoystickButton(secondaryJoystick, 7);
        lightsOn.whenPressed(new LightOn());
        gateClose = new JoystickButton(secondaryJoystick, 5);
        gateClose.whenPressed(new CloseGate());
        gateOpen = new JoystickButton(secondaryJoystick, 3);
        gateOpen.whenPressed(new GearGateOpen());
        activateShooter = new JoystickButton(secondaryJoystick, 6);
        activateShooter.whileHeld(new ShooterLauncher());
        aimShooter = new JoystickButton(secondaryJoystick, 8);
        aimShooter.whenPressed(new ShooterAim(0));
        driveJoystick = new Joystick(0);
        
        mirrorDrive = new JoystickButton(driveJoystick, 2);
        mirrorDrive.whenPressed(new mirrorToggle());
        tiltbuttonIn = new JoystickButton(driveJoystick, 1);
        tiltbuttonIn.whenReleased(new TiltIn());
        tiltButtonOut = new JoystickButton(driveJoystick, 1);
        tiltButtonOut.whileHeld(new TiltOut());
        shiftHigh = new JoystickButton(driveJoystick, 4);
        shiftHigh.whenPressed(new HighGear());
        shiftLow = new JoystickButton(driveJoystick, 3);
        shiftLow.whenPressed(new LowGear());


        // SmartDashboard Buttons
        SmartDashboard.putData("DriveCommand", new DriveCommand());
        SmartDashboard.putData("PnewmaticCommand", new PnewmaticCommand());
        SmartDashboard.putData("Stop", new Stop());
        SmartDashboard.putData("SensorInit", new SensorInit());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    public Joystick getSecondaryJoystick() {
        return secondaryJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

