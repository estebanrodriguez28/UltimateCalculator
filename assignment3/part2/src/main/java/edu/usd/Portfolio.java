package edu.usd;

public class Portfolio implements Asset {
    //TODO: add any member data

    public Portfolio(Cash cash, String name) {
        //TODO: implement constructor that uses cash and the name
    }

    //TODO: apply static polymorphism to implement another constructor that takes in only cash.
    // A default value for name should be used.

    public void buy(Asset asset) throws InsufficientFundsException {
        // TODO: implement the logic for buying an asset.
        // You should implement the InsufficientFundsException as a nested class.
        // InsufficientFundsException should be thrown if there is not enough cash to cover buying the asset.
    }

    public void sell(String name, int numDays) throws AssetNotFoundException {
        // TODO: implement the logic to sell an asset.
        // The asset will be matched based on the name attribute.
        // The asset value should be converted to cash.
        // You should implement the AssetNotFoundException as a nested class.
        // AssetNotFoundException should be thrown if there is no asset in the portfolio with a matching name.
    }

    public double getValue(int numDays) {
        // TODO: implement the portfolio's total value calculation.
        // This should include the value of all the assets plus the cash value in the portfolio.
        return 0.0;
    }

    public double getCashValue() {
        // TODO: determine the value of the cash in the portfolio.
        return 0.0;
    }

    // TODO: add any other getter, setter, or helper methods.
}
