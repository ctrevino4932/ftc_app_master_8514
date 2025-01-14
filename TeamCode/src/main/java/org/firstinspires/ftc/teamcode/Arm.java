package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm {
    private DcMotorSimple sweeper;
    private DcMotor armMotor;
   // private Servo release;
    private int multiplier = 1;

    public Arm(HardwareMap hardwareMap) {
        /* Initializes Arm */
        armMotor = hardwareMap.dcMotor.get("catapultArm");
        sweeper = hardwareMap.dcMotor.get("sweeper");
    //    release = hardwareMap.servo.get("ballDrop");
        armMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void init() {
        /* Initializes catapult arm and sets the desired position */
      //  release.setPosition(0.0);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setTargetPosition(0);
    }

    public void rotateArm() {
        /* Turns Motor a full revolution, explained in Control Math. */
        armMotor.setTargetPosition(multiplier * 1650);
        armMotor.setPower(100);
        multiplier++;
    }
    public void sweepPower(double power) {
        /* Sets the power of the sweeping device */
        sweeper.setPower(power);
    }

    public boolean revComplete() {
        return armPosition() >= armRange()[0] && armPosition() <= armRange()[1];
    }
    private int[] armRange() {
        return new int[]{
                armMotor.getTargetPosition() - 15,
                armMotor.getTargetPosition() + 15
        };
    }

    public int armPosition() {
        /* Returns the current position of the catapult */
        return armMotor.getCurrentPosition();
    }
/*    public void drop() {
        if (release.getPosition() > 0.3) {
            release.setPosition(0.0);
        }
        else if (release.getPosition() < 0.7) {
            release.setPosition(1.0);
        }
    }*/
}