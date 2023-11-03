package edu.usd;

public abstract class AbstractInvestment implements Asset {
    protected String name;
    protected double interestRate;
    protected double startingValue;

    public AbstractInvestment(String name, double interestRate, double startingValue) {
        this.name = name;
        this.interestRate = interestRate;
        this.startingValue = startingValue;
    }

    @Override
    public abstract double getValue(int numDays);

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getStartingValue() {
        return this.startingValue;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }
}