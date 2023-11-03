package edu.usd;

public class Cash implements Asset{
    private double value;

    public Cash(double value) {
        this.value = value;
    }

    // getValue that implements the Asset function
    @Override
    public double getValue(int numDays) {
        return this.value;
    }

    // getValue with no parameters
    public double getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return "Cash";
    }

    @Override
    public double getStartingValue() {
        return this.value;
    }

    // setter for value
    public void setValue(double value) {
        this.value = value;
    }

}
