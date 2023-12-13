package edu.usd;

public class BondInvestment extends AbstractInvestment {
    public BondInvestment(String name, double interestRate, double startingValue) {
        super(name, interestRate, startingValue);
    }

    @Override
    public double getValue(int numDays) {
        // startingValue * (1.0 + interestRate)^timeInYears
        return (this.startingValue * Math.pow( (1.0 + this.interestRate), (numDays/365.0) ) );
    }
}
