package edu.usd.PhysicsService;
import java.lang.Math;

public class KinematicCalculator extends PhysicsCalculator {
    private static final int INPUTS_LENGTH = 5;
    private String initialV, finalV, time, accel, dist;
    private String option;

    public KinematicCalculator() {
        this.initialV = null;
        this.finalV = null; 
        this.time = null; 
        this.accel = null; 
        this.dist = null;

        this.option = null;
    }

    public KinematicCalculator(String inputs[], String option) throws IllegalArgumentException{
        if (validateInputs(inputs)) {
            this.initialV = inputs[0];
            this.finalV = inputs[1];
            this.time = inputs[2];
            this.accel = inputs[3];
            this.dist = inputs[4];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    @Override
    public boolean validateInputs(String[] inputs) throws IllegalArgumentException{
        if (inputs.length != INPUTS_LENGTH){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String[] getInputs() {
        String[] inputs = { this.initialV, this.finalV, this.time, this.accel, this.dist };
        return inputs;
    }
    
    @Override
    public String getOption() {
        return this.option;
    }

    @Override
    public void setValues(String[] inputs, String option) throws IllegalArgumentException{
        if (validateInputs(inputs)){
            this.initialV = inputs[0];
            this.finalV = inputs[1];
            this.time = inputs[2];
            this.accel = inputs[3];
            this.dist = inputs[4];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    public double calculateDist() throws IllegalArgumentException{
        double a, vf, vi, t;
        try {
            if (time != null) {
                t = Double.parseDouble(time);
                if (t < 0.0) {
                    throw new IllegalArgumentException("Time must be positive.");
                } else if (accel != null && initialV != null) {
                    a = Double.parseDouble(accel);
                    vi = Double.parseDouble(initialV);
                    return (vi * t) + (0.5 * a * Math.pow(t, 2)); // x = vit + 1/2at^2
                } else if (initialV != null && finalV != null) {
                    vi = Double.parseDouble(initialV);
                    vf = Double.parseDouble(finalV);
                    return (0.5 * (vi + vf) * t);
                } else {
                    throw new IllegalArgumentException("Not enough information to compute.");
                }
            } else if ( accel != null && finalV != null && initialV != null) {
                a = Double.parseDouble(accel);
                vf = Double.parseDouble(finalV);
                vi = Double.parseDouble(initialV);
                if (a == 0) {
                    if (vf == vi) {
                        throw new IllegalArgumentException("Not enough data to compute."); // need time
                    } else {
                        throw new IllegalArgumentException("Acceleration and Velocities don't make sense.");
                    }
                } else if ((a > 0.0 && vf >= vi) || (a < 0.0 && vf <= vi)) {
                    return ((Math.pow(vf, 2) - Math.pow(vi, 2)) / (2 * a)); // vf^2 = vi^2 + 2ax
                } else {
                    throw new IllegalArgumentException("Starting and Final Velocity don't make sense.");
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }

        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
}
    
    public double calculateAccel() throws IllegalArgumentException{
        double vi, vf, t, x;
        try {
            if (time != null) {
                t = Double.parseDouble(time);
                if (t < 0.0) {
                    throw new IllegalArgumentException("Time must be positive.");
                } else if (finalV != null && initialV != null) {
                    vi = Double.parseDouble(initialV);
                    vf = Double.parseDouble(finalV);
                    return (vf - vi) / t; // vf = vi + at
                } else if (dist != null && initialV != null) {
                    x = Double.parseDouble(dist);
                    vi = Double.parseDouble(initialV);
                    return (x - (vi * t)) / (0.5 * Math.pow(t, 2)); // x = vit + 1/2at^2
                } else {
                    throw new IllegalArgumentException("Not enough information to compute.");
                }
            } else if (finalV != null && initialV != null && dist != null) {
                vi = Double.parseDouble(initialV);
                vf = Double.parseDouble(finalV);
                x = Double.parseDouble(dist);
                if (x == 0.0) {
                    return 0.0;
                } else if ((x > 0 && vi < 0 && vf < 0) || (x < 0 && vi > 0 && vf > 0)){
                    throw new IllegalArgumentException("Distance and velocities do not make sense.");
                } else {
                    return (Math.pow(vf, 2) - Math.pow(vi, 2)) / (2 * x);
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }  
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateTime() throws IllegalArgumentException {
        double a, vf, vi, x;
        try{
            if (accel != null && initialV != null && finalV != null) {
                a = Double.parseDouble(accel);
                vi = Double.parseDouble(initialV);
                vf = Double.parseDouble(finalV);
                if ((a > 0.0 && vf >= vi) || (a < 0.0 && vf <= vi)) {
                    return (vf - vi) / a; // vf = vi + at
                } else if (a == 0) {
                    if (vf == vi) {
                        throw new IllegalArgumentException("Not enough information to compute."); // need time
                    } else {
                        throw new IllegalArgumentException("Acceleration and Velocities don't make sense.");
                    }
                } else {
                    throw new IllegalArgumentException("Starting and Final Velocity don't make sense.");
                }
            } else if (dist != null && initialV != null && accel != null) {
                x = Double.parseDouble(dist);
                vi = Double.parseDouble(initialV);
                a = Double.parseDouble(accel);
                if ((a > 0 && vi > 0 && x < 0) || (a < 0 && vi < 0 && x > 0)) {
                    throw new IllegalArgumentException("Values don't make sense.");
                } else {
                    // solve using quadratic formula
                    double arg = (Math.pow(vi, 2) - (4 * (0.5 * a) * (x * -1))); // 0 = 1/2at^2 - vit - x
                    if (arg < 0 || a == 0) {
                        throw new IllegalArgumentException("Values don't make sense.");
                    } else {
                        return ((vi * -1) + Math.sqrt(arg)) / (2 * a); // only want positive value for time
                    }
                }
            } else if (dist != null && initialV != null && finalV != null) {
                x = Double.parseDouble(dist);
                vi = Double.parseDouble(initialV);
                vf = Double.parseDouble(finalV);
                if ((vi < vf && x < 0) || (vi > vf && x > 0)) {
                    throw new IllegalArgumentException("Values don't make sense.");
                } else {
                    return x / (0.5 * (vi + vf)); // x = 1/2(vi + vf)t
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateInitV() throws IllegalArgumentException{
        double a, vf, t, x;
        try {
            if (time != null) {
                t = Double.parseDouble(time);
                if (t < 0.0){
                    throw new IllegalArgumentException("Time must be positive.");
                } else if (accel != null && finalV != null) {
                    a = Double.parseDouble(accel);
                    vf = Double.parseDouble(finalV);
                    return vf - (a * t); // vf = vi + at
                }  else if (accel != null && dist != null){
                    a = Double.parseDouble(accel);
                    x = Double.parseDouble(dist);
                    if ((a > 0 && x < 0) || (a < 0 && x > 0)){
                        throw new IllegalArgumentException("Values don't make sense.");
                    } else if (t == 0){
                        throw new IllegalArgumentException("Not enough information to compute.");
                    }else {
                        return (x - (0.5 * a * Math.pow(t, 2)) / t); // x = vit + 1/2at^2
                    }
                } else if (dist != null && finalV != null){
                    vf = Double.parseDouble(finalV);
                    x = Double.parseDouble(dist);
                    if (t < 0){
                        throw new IllegalArgumentException("Time must be positive.");
                    } else if (t == 0){
                        return vf;
                    }else {
                        return ((x / t) * 2.0) - vf; // x = 1/2(vi + vf)t
                    }
                } else {
                    throw new IllegalArgumentException("Not enough information to compute.");
                }
            }else if (accel != null && dist != null && finalV != null){
                a = Double.parseDouble(accel);
                vf = Double.parseDouble(finalV);
                x = Double.parseDouble(dist);
                return Math.sqrt(Math.pow(vf, 2) - (2.0 * a * x)); // vf^2 = vi^2 + 2ax
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateFinalV() throws IllegalArgumentException{
        double a, vi, t, x;
        try {
            if (time != null) {
                t = Double.parseDouble(time);
                if (t < 0.0){
                    throw new IllegalArgumentException("Time must be positive.");
                } else if (accel != null && initialV != null) {
                    a = Double.parseDouble(accel);
                    vi = Double.parseDouble(initialV);
                    return vi + (a * t); // vf = vi + at
                } else if (dist != null && initialV != null) {
                    x = Double.parseDouble(dist);
                    vi = Double.parseDouble(initialV);
                    if (t == 0) {
                        return vi;
                    } else {
                        return (x / (0.5 * t)) - vi; // x = 1/2(vi + vf)t
                    }
                } else {
                    throw new IllegalArgumentException("Not enough information to compute.");
                }
            } else if (accel != null && initialV != null && dist != null) {
                a = Double.parseDouble(accel);
                vi = Double.parseDouble(initialV);
                x = Double.parseDouble(dist);
                if ((a > 0 && vi > 0 && x < 0) || (a < 0 && vi < 0 && x > 0)) {
                    throw new IllegalArgumentException("Values don't make sense.");
                } else {
                    return Math.sqrt(Math.pow(vi, 2) + (2 * a * x)); // vf^2 = vi^2 + 2ax
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }

    @Override
    public double calculate(String[] inputs, String option) throws IllegalArgumentException{
        setValues(inputs, option);

        return calculate();
    }

    @Override
    public double calculate() throws IllegalArgumentException
    {
        if (option.equalsIgnoreCase("Distance")) { // calcualte distance
            return calculateDist();
        } else if (option.equalsIgnoreCase("Acceleration")) { // calculate acceleration
            return calculateAccel();
        } else if (option.equalsIgnoreCase("Time")) { // calculate time
            return calculateTime();
        } else if (option.equalsIgnoreCase("Initial Velocity")) { // calculate initial velocity
            return calculateInitV();
        } else if (option.equalsIgnoreCase("Final Velocity")) { // calculate final velocity
            return calculateFinalV();
        } else {
            throw new IllegalArgumentException("Invalid Option.");
        }

    }
    
    @Override
    public void saveResult(String filename, String data) {
    }

    @Override
    public void printResult(String data) {
    }
}
