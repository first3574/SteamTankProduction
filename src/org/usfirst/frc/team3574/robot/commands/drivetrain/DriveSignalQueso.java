package org.usfirst.frc.team3574.robot.commands.drivetrain;

/**
 * A drivetrain command consisting of the left, right motor settings and whether
 * the brake mode is enabled.
 */
public class DriveSignalQueso {
    public double leftMotor;
    public double rightMotor;
    public boolean breakMode;

    public DriveSignalQueso(double left, double right) {
        this(left, right, false);
    }

    public DriveSignalQueso(double left, double right, boolean breakMode) {
        this.leftMotor = left;
        this.rightMotor = right;
        this.breakMode = breakMode;
    }

    public static DriveSignalQueso NEUTRAL = new DriveSignalQueso(0, 0);
    public static DriveSignalQueso BREAK = new DriveSignalQueso(0, 0, true);

    @Override
    public String toString() {
        return "L: " + leftMotor + ", R: " + rightMotor;
    }
}