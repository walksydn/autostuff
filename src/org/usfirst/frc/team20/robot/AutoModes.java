package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;

public class AutoModes {
	DriveTrain base;
	AnalogInput ultrasonic;
	AHRS navx;
	private boolean gotYaw;
	private double origYaw;
	DriveTrain drive = new DriveTrain(4);
	LineSensor line = new LineSensor();
	
	public AutoModes(DriveTrain drive, AnalogInput ultrasonic, AHRS navx){
		base = drive;						//TODO port numbers for DriveTrain
		drive.assignPort(0, 2);
    	drive.assignPort(1, 5);
    	drive.assignPort(2, 3);
    	drive.assignPort(3, 11);
	}
	public void drive(int distance, double speed){		//encoders
		
	}
	public void drive(double time, double speed){
		time = DriverStation.getInstance().getMatchTime();
		drive.drive(0, -speed);					//TODO negate proper side (straight)
		drive.drive(1, speed);
		drive.drive(2, -speed);
		drive.drive(3, speed);
	}
	public void drive(int speed){
		drive.drive(0, -speed);					//TODO negate proper side (straight)
		drive.drive(1, speed);
		drive.drive(2, -speed);
		drive.drive(3, speed);
	}
	public void stop(){
		drive.drive(0, 0);
		drive.drive(1, 0);
		drive.drive(2, 0);
		drive.drive(3, 0);	}
	public void driveToLine(){
		line.driveToLine();
	}
	public void turnToAngle(double angle, double tolerance){	//robot turns to angle a with a tolerance of t
		if(!gotYaw){
			origYaw = navx.getYaw();
			gotYaw = true;
		}
		if(navx.getYaw() > origYaw + angle + tolerance && navx.getYaw() < origYaw + 180){
			drive.drive(0, 1);			//TODO negate proper side
			drive.drive(1, 1);
			drive.drive(2, 1);
			drive.drive(3, 1);
		}
		if(navx.getYaw() < origYaw + angle + tolerance && navx.getYaw() > origYaw - 180){
			drive.drive(0, -1);			//TODO negate proper side
			drive.drive(1, -1);
			drive.drive(2, -1);
			drive.drive(3, -1);
		}
	}
	public void shoot(){
		
	}
}
