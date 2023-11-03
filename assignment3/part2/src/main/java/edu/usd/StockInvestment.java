package edu.usd;

import java.util.Random;

public class StockInvestment extends AbstractInvestment {
    private double volatility;
    private Double randomInterestRate;

    public StockInvestment(String name, double interestRate, double startingValue, double volatility) {
        super(name, interestRate, startingValue);
        this.volatility = volatility;
    }

    public double getValue(int numDays) {

        // set random interest rate if undeclared
        if (this.randomInterestRate == null) {
            this.setRandomInterestRate();
        }

        // startingValue * (1.0 + randomInterestRate)^timeInYears
        return this.startingValue * Math.pow( (1.0 + this.randomInterestRate), (numDays/365.0) );

    }

    public double getVolatility() {
        return this.volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getRandomInterestRate() {
        return this.randomInterestRate;
    }

    // generate random value
    private void setRandomInterestRate() {
        Random random = new Random();
        this.randomInterestRate = this.interestRate + this.volatility * random.nextGaussian();
    }

}
