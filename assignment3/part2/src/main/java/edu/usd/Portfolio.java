package edu.usd;

import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Asset {
    private Cash cash;
    private List<Asset> assets;
    private String name;

    // cash and name parameters
    public Portfolio(Cash cash, String name) {
        this.cash = cash;
        this.assets = new ArrayList<>();
        this.name = name;
    }

    // only cash parameter, name stays static
    public Portfolio(Cash cash) {
        this.cash = cash;
        this.assets = new ArrayList<>();
        this.name = "My Portfolio";
    }

    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    public static class AssetNotFoundException extends Exception {
        public AssetNotFoundException(String message) {
            super(message);
        }
    }


    public void buy(Asset asset) throws InsufficientFundsException {

        // calculate difference between cash and asset
        double balanceAfter = cash.getValue() - asset.getStartingValue();

        if (balanceAfter < 0) {
            throw new InsufficientFundsException("Not enough cash for asset");
        }

        // add asset and subtract from cash value
        assets.add(asset);
        cash.setValue(balanceAfter);
    }

    public void sell(String name, int numDays) throws AssetNotFoundException {

        boolean assetExists = false;

        // iterate through asset list until name is found
        for (int i = 0; i < this.assets.size(); i++) {
            if (this.assets.get(i).getName().equals(name)) {
                assetExists = true;

                // add value of asset to cash balance
                double balanceAfter = this.assets.get(i).getValue(numDays) + cash.getValue();
                cash.setValue(balanceAfter);

                this.assets.remove(i);
                break;
            }
        }

        if (!assetExists) {
            throw new AssetNotFoundException("Asset with this name is not in the list");
        }
    }

    public double getValue(int numDays) {

        double total = 0;

        // add all asset values
        for (Asset asset: assets) {
            total += asset.getValue(numDays);
        }

        // add cash value to assets
        return total + this.cash.getValue();
    }

    public double getCashValue() {
        return this.cash.getValue();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getStartingValue() {
        return this.cash.getValue();
    }

    public void setCashValue(double cashValue) {
        this.cash.setValue(cashValue);
    }

}
