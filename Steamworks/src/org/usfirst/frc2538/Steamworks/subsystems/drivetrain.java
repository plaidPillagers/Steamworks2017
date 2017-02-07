// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2538.Steamworks.subsystems;

import java.text.NumberFormat;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc2538.Steamworks.OI;
import org.usfirst.frc2538.Steamworks.Robot;
import org.usfirst.frc2538.Steamworks.RobotMap;
import org.usfirst.frc2538.Steamworks.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer.StaticInterface;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class drivetrain extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftmotor = RobotMap.drivetrainLeftmotor;
    private final SpeedController rightmotor = RobotMap.drivetrainRightmotor;
    private final SpeedController centermotor = RobotMap.drivetrainCentermotor;
    private final AnalogInput rangeFinder1 = RobotMap.drivetrainRangeFinder1;
    private final Encoder leftEncoder1 = RobotMap.drivetrainLeftEncoder1;
    private final Encoder rightEncoder1 = RobotMap.drivetrainRightEncoder1;
    private final Encoder centerEncoder1 = RobotMap.drivetrainCenterEncoder1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final RobotDrive rd = new RobotDrive(leftmotor, rightmotor);
	private Joystick driveJoystick;
	private ADXRS450_Gyro gyroSPI;
	private ADXL345_I2C accel;
	private final double autoForwardPower = 0.6;
	private final double max = 0.5;
	private final double scaleAdjust = 0.16;
	private double gyroAngle=0.0;
	private int rightEncoderValue = 0;
	private int centerEncoderValue = 0;
	private int leftEncoderValue = 0;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		driveJoystick = Robot.oi.driveJoystick;
		gyroSPI = RobotMap.gyro;
		accel = RobotMap.accelerometer;
	}

	public void drive() {
		double y = driveJoystick.getY();
		double x = driveJoystick.getX();
		double z = driveJoystick.getThrottle();
		rd.arcadeDrive(y, x);
		centermotor.set(z);
	}
	
	private double getRange(){
		return rangeFinder1.getVoltage() * 3.461 - 0.136; 
	}

	public void drive2() {
		double y = driveJoystick.getY();
		double x = driveJoystick.getX();
		double z = driveJoystick.getThrottle();
		rd.arcadeDrive(y, -z);
		centermotor.set(x);
		String s = "" + gyroSPI.getAngle();
		//SmartDashboard.putString("autoCommand", s.substring(0, 4));
		// int rfv=rangeFinder1.getValue();
		double rfv = rangeFinder1.getVoltage() * 3.461 - 0.136;

		String s2 = Double.toString(rfv);
		SmartDashboard.putString("ultraVoltage", s2.substring(0, 8));
		String s3 = "" + accel.getX();
		SmartDashboard.putString("Accelerometer x", s3);
		String s4 = "" + leftEncoder1.get();
		String s5 = "" + rightEncoder1.get();
		String s6 = "" + centerEncoder1.get();
		SmartDashboard.putString("Leftencoder", s4);
		SmartDashboard.putString("Rightencoder", s5);
		SmartDashboard.putString("Centerencoder", s6);

	}

	public boolean autoDriveForwardEncoder(int distance,double speed,double tolerance){
		if (rightEncoder1.get() < rightEncoderValue + distance) {
			
			double leftScale = 1.0;
			double rightScale = 1.0;
			double g1 = gyroSPI.getAngle() * 1.0001;
			// SmartDashboard.putString("encoder",
			// Double.toString(g1-g0).substring(0, 6));
			if (Math.abs(g1 - gyroAngle) > tolerance) {
				if (g1 > gyroAngle) {
					leftScale *=1+(scaleAdjust);
					rightScale *=1-(scaleAdjust);
				} else if (g1 < gyroAngle) {
					rightScale *=1+(scaleAdjust);
					leftScale *=1-(scaleAdjust);
				}
			}
			leftmotor.set(-speed * leftScale);
			rightmotor.set(speed* rightScale);
			return false;
		} else {
		leftmotor.set(0);
		rightmotor.set(0);
		leftEncoderValue = leftEncoder1.get();
		rightEncoderValue = rightEncoder1.get();
		return true;
		}
	}
	
	public boolean autoDriveReverseEncoder(int distance,double speed,double tolerance){
		if (leftEncoder1.get() < leftEncoderValue + distance) {
			
			double leftScale = 1.0;
			double rightScale = 1.0;
			double g1 = gyroSPI.getAngle() * 1.0001;
			// SmartDashboard.putString("encoder",
			// Double.toString(g1-g0).substring(0, 6));
			if (Math.abs(g1 - gyroAngle) > tolerance) {
				if (g1 > gyroAngle) {
					leftScale *=1+(scaleAdjust);
					rightScale *=1-(scaleAdjust);
				} else if (g1 < gyroAngle) {
					rightScale *=1+(scaleAdjust);
					leftScale *=1-(scaleAdjust);
				}
			}
			leftmotor.set(speed * leftScale);
			rightmotor.set(-speed* rightScale);
			return false;
		} else {
		leftmotor.set(0);
		rightmotor.set(0);
		leftEncoderValue = leftEncoder1.get();
		rightEncoderValue = rightEncoder1.get();
		return true;
		}
		
	}
	
	public boolean autoDriveLeftEncoder(int distance,double speed){
		if (centerEncoder1.get() < centerEncoderValue + distance) {
			centermotor.set(speed);
			return false;
		}else{ 
		centermotor.set(0);
		centerEncoderValue = centerEncoder1.get();
		return true;
		}
	}
	
	public boolean autoDriveRightEncoder(int distance,double speed){
		if (centerEncoder1.get() < centerEncoderValue - distance) {
			centermotor.set(-speed);
			return false;
		}else{ 
		centermotor.set(0);
		centerEncoderValue = centerEncoder1.get();
		return true;
		}
	}
	
	public boolean autoRotateCW(double angle, double speed){
		if (gyroSPI.getAngle()< gyroAngle + angle){
			SmartDashboard.putString("autoCommand", Double.toString(gyroSPI.getAngle()));
			leftmotor.set(speed);
			rightmotor.set(speed);
		return false;
		} else {
			leftmotor.set(0);
			rightmotor.set(0);
			centerEncoderValue = centerEncoder1.get();
			leftEncoderValue = leftEncoder1.get();
			rightEncoderValue = rightEncoder1.get();
			return true;
		}
	}

	public boolean autoRotateCCW(double angle, double speed){
		if (gyroSPI.getAngle()< gyroAngle - angle){
			SmartDashboard.putString("autoCommand", Double.toString(gyroSPI.getAngle()));
			leftmotor.set(-speed);
			rightmotor.set(-speed);
		return false;
		} else {
			leftmotor.set(0);
			rightmotor.set(0);
			centerEncoderValue = centerEncoder1.get();
			leftEncoderValue = leftEncoder1.get();
			rightEncoderValue = rightEncoder1.get();
			return true;
		}
	}
	public boolean initallizeSensors(){
		gyroAngle=gyroSPI.getAngle();
		centerEncoderValue = centerEncoder1.get();
		leftEncoderValue = leftEncoder1.get();
		rightEncoderValue = rightEncoder1.get();
		return true;
	}
	
	public boolean fowardRangeFinder(double range,double speed,double tolerance){
	if (getRange() > range) {
		String s2 = Double.toString(getRange());
		SmartDashboard.putString("ultraVoltage", s2.substring(0, 8));
			
			double leftScale = 1.0;
			double rightScale = 1.0;
			double g1 = gyroSPI.getAngle() * 1.0001;
			// SmartDashboard.putString("encoder",
			// Double.toString(g1-g0).substring(0, 6));
			if (Math.abs(g1 - gyroAngle) > tolerance) {
				if (g1 > gyroAngle) {
					leftScale *=1+(scaleAdjust);
					rightScale *=1-(scaleAdjust);
				} else if (g1 < gyroAngle) {
					rightScale *=1+(scaleAdjust);
					leftScale *=1-(scaleAdjust);
				}
			}
			leftmotor.set(-speed * leftScale);
			rightmotor.set(speed* rightScale);
			return false;
		} else {
		leftmotor.set(0);
		rightmotor.set(0);
		leftEncoderValue = leftEncoder1.get();
		rightEncoderValue = rightEncoder1.get();
		return true;
		}
		
	}
	
	public boolean stop() {
		leftmotor.set(0);
		rightmotor.set(0);
		centermotor.set(0);
		return true;
	}

	public void forward() {
		
		
	}

}
