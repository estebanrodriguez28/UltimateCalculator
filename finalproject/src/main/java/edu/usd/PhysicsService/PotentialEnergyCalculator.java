package edu.usd.PhysicsService;

public class PotentialEnergyCalculator extends PhysicsCalculator {
    private static final int INPUTS_LENGTH = 3;
    private static final double GRAVITY = 9.81;
    private String potentialEnergy, mass, height;
    private String option;

    public PotentialEnergyCalculator() {
        this.potentialEnergy = null;
        this.mass = null;
        this.height = null;

        this.option = null;
    }

    public PotentialEnergyCalculator(String[] inputs, String option) throws IllegalArgumentException{
        if (validateInputs(inputs)) {
            this.potentialEnergy = inputs[0];
            this.mass = inputs[1];
            this.height = inputs[2];

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
        String[] inputs = { potentialEnergy, mass, height };
        return inputs;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public void setValues(String[] inputs, String option) throws IllegalArgumentException {
        if (validateInputs(inputs)) {
            this.potentialEnergy = inputs[0];
            this.mass = inputs[1];
            this.height = inputs[2];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    public double calculatePotentialEnergy() throws IllegalArgumentException {
        double m, h;
        try {
            if (mass != null && height != null) {
                m = Double.parseDouble(mass);
                h = Double.parseDouble(height);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else {
                    return m * GRAVITY * h; // PE = mgh
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateMass() throws IllegalArgumentException {
        double pe, h;
        try {
            if (potentialEnergy != null && height != null) {
                pe = Double.parseDouble(potentialEnergy);
                h = Double.parseDouble(height);
                if (h == 0) {
                    if (pe == 0) {
                        throw new IllegalArgumentException("Unable to calculate mass.");
                    } else {
                        throw new IllegalArgumentException("Values don't make sense.");
                    }
                } else {
                    return pe / (GRAVITY * h); // m = PE/(g * h)
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateHeight() throws IllegalArgumentException {
        double pe, m;
        try {
            if (potentialEnergy != null && height != null) {
                pe = Double.parseDouble(potentialEnergy);
                m = Double.parseDouble(mass);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else {
                    return pe / (m * GRAVITY); // h = pe / (m * g)
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    @Override
    public double calculate(String[] inputs, String output) throws IllegalArgumentException {
        setValues(inputs, output);

        return calculate();
    }

    @Override
    public double calculate() throws IllegalArgumentException {
        if (option.equalsIgnoreCase("Potential Energy")) {
            return calculatePotentialEnergy();
        } else if (option.equalsIgnoreCase("Mass")) {
            return calculateMass();
        } else if (option.equalsIgnoreCase("Height")) {
            return calculateHeight();
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
