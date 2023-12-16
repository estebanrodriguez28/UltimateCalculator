import java.lang.Math;

public class KineticEnergyCalculator extends PhysicsCalculator {
    private static final int INPUTS_LENGTH = 3;
    private String kineticEnergy, mass, velocity;
    private String option;

    public KineticEnergyCalculator() {
        this.kineticEnergy = null;
        this.mass = null;
        this.velocity = null;

        this.option = null;
    }

    public KineticEnergyCalculator(String[] inputs, String option) throws IllegalArgumentException{
        if (validateInputs(inputs)) {
            this.kineticEnergy = inputs[0];
            this.mass = inputs[1];
            this.velocity = inputs[2];

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
        String[] inputs = { kineticEnergy, mass, velocity };
        return inputs;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public void setValues(String[] inputs, String option) throws IllegalArgumentException {
        if (validateInputs(inputs)) {
            this.kineticEnergy = inputs[0];
            this.mass = inputs[1];
            this.velocity = inputs[2];

            this.option = option;
        } else {
            throw new IllegalArgumentException("Incorrect number of inputs(expected " + INPUTS_LENGTH + ").");
        }
    }

    public double calculateKineticEnergy() throws IllegalArgumentException {
        double m, v;
        try {
            if (mass != null && velocity != null) {
                m = Double.parseDouble(mass);
                v = Double.parseDouble(velocity);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else {
                    return 0.5 * m * Math.pow(v, 2); // ke = 1/2mv^2
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }
    
    public double calculateMass() throws IllegalArgumentException {
        double ke, v;
        try {
            if (kineticEnergy != null && velocity != null) {
                ke = Double.parseDouble(kineticEnergy);
                v = Double.parseDouble(velocity);
                if (ke < 0) {
                    throw new IllegalArgumentException("Kinetic Energy cannot be negative.");
                } else if (v == 0) {
                    throw new IllegalArgumentException("Not enough information to compute.");
                } else {
                    return ke / (0.5 * Math.pow(v, 2)); // m = ke / (1/2v^2)
                }
            } else {
                throw new IllegalArgumentException("Not enough information to compute.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Variables must be numbers.");
        }
    }

    public double calculateVelocity() throws IllegalArgumentException {
        double ke, m;
        try {
            if (kineticEnergy != null && mass != null) {
                ke = Double.parseDouble(kineticEnergy);
                m = Double.parseDouble(mass);
                if (m <= 0) {
                    throw new IllegalArgumentException("Mass cannot be negative or zero.");
                } else if (ke < 0) {
                    throw new IllegalArgumentException("Kinetic Energy cannot be negative.");
                } else {
                    return Math.sqrt(ke / (0.5 * m)); // v = sqrt(ke / (1/2m))
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
        if (option.equalsIgnoreCase("Kinetic Energy")) {
            return calculateKineticEnergy();
        } else if (option.equalsIgnoreCase("Mass")) {
            return calculateMass();
        } else if (option.equalsIgnoreCase("Velocity")) {
            return calculateVelocity();
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
