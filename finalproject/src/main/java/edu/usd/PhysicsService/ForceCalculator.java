package edu.usd.PhysicsService;

public class ForceCalculator extends PhysicsCalculator {
    private static final int INPUTS_LENGTH = 3;
    private String force, mass, acceleration;
    private String option;

    public ForceCalculator() {
        this.force = null;
        this.mass = null;
        this.acceleration = null;

        this.option = null;
    }

    public ForceCalculator(String[] inputs, String option) throws IllegalArgumentException {
        if (validateInputs(inputs)) {
            this.force = inputs[0];
            this.mass = inputs[1];
            this.acceleration = inputs[2];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    @Override
    public boolean validateInputs(String[] inputs) throws IllegalArgumentException {
        if (inputs.length != INPUTS_LENGTH) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String[] getInputs() {
        String[] inputs = { force, mass, acceleration };
        return inputs;
    }

    @Override
    public String getOption() {
        return this.option;
    }

    @Override
    public void setValues(String[] inputs, String option) throws IllegalArgumentException{
        if (validateInputs(inputs)) {
            this.force = inputs[0];
            this.mass = inputs[1];
            this.acceleration = inputs[2];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    public double calculateForce() throws IllegalArgumentException {
        double m, a;
        try {
            if (mass != null && acceleration != null) {
                m = Double.parseDouble(mass);
                a = Double.parseDouble(acceleration);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else {
                    return m * a; // F = ma
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }

    public double calculateAccel() throws IllegalArgumentException {
        double f, m;
        try {
            if (force != null && mass != null) {
                f = Double.parseDouble(force);
                m = Double.parseDouble(mass);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else {
                    return f / m; // a = F/m
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }

    public double calculateMass() throws IllegalArgumentException {
        double f, a;
        try {
            if (force != null && acceleration != null) {
                f = Double.parseDouble(force);
                a = Double.parseDouble(acceleration);
                if ((a == 0 && f != 0) || (f == 0 && a != 0) || (f > 0 && a < 0) || (f < 0 && a > 0)) {
                    throw new IllegalArgumentException("Force and acceleration don't make sense.");
                } else {
                    return f / a; // m = F/a
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    @Override
    public double calculate(String[] inputs, String output) throws IllegalArgumentException{
        setValues(inputs, output);

        return calculate();
    }

    @Override
    public double calculate() throws IllegalArgumentException {
        if (option.equalsIgnoreCase("Force")) {
            return calculateForce();
        } else if (option.equalsIgnoreCase("Mass")) {
            return calculateMass();
        } else if (option.equalsIgnoreCase("Acceleration")) {
            return calculateAccel();
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