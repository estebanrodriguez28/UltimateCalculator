package edu.usd.PhysicsService;

public class PhysicsCalculatorHandler {
    private KinematicCalculator kinematicCalculator;
    private ForceCalculator forceCalculator;
    private KineticEnergyCalculator kineticEnergyCalculator;
    private PotentialEnergyCalculator potentialEnergyCalculator;

    public PhysicsCalculatorHandler() {
        this.kinematicCalculator = new KinematicCalculator();
        this.forceCalculator = new ForceCalculator();
        this.kineticEnergyCalculator = new KineticEnergyCalculator();
        this.potentialEnergyCalculator = new PotentialEnergyCalculator();
    }

    public double handleCalculation(String calculator, String[] inputs, String option) throws IllegalArgumentException{
        if (calculator.equalsIgnoreCase("Kinematic")) {
            return kinematicCalculator.calculate(inputs, option);
        } else if (calculator.equalsIgnoreCase("Force")) {
            return forceCalculator.calculate(inputs, option);
        } else if (calculator.equalsIgnoreCase("Kinetic Energy")) {
            return kineticEnergyCalculator.calculate(inputs, option);
        } else if (calculator.equalsIgnoreCase("Potential Energy")) {
            return potentialEnergyCalculator.calculate(inputs, option);
        } else {
            throw new IllegalArgumentException("Invalid Option");
        }
    }
}
