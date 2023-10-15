package edu.usd;

import java.util.Random;

public class StockInvestment extends AbstractInvestment {
    //TODO: add any member data
    public StockInvestment(String name, double interestRate, double startingValue, double volatility) {
        // TODO: implement constructor
    }

    public double getValue(int numDays) {
        //TODO: calculate the stock's value.
        // Since a stock's value is random, this will be modeled as a random interest rate.
        // The random interest rate will be interestRate + volatility * randomNumber.
        // You can use the Random class imported above and draw from a Gaussian distribution using the nextGaussian method.
        // Make sure that the same random interest rate is used per object.
        // Once you have the random interest rate, the calculation is the same as a bond:
        // startingValue * (1.0 + randomInterestRate)^timeInYears
        return 0.0;
    }

    //TODO: implement any getter, setter, and helper methods.
}
