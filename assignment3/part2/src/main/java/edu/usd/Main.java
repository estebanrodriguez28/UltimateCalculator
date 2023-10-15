package edu.usd;

public class Main {
    public static void main(String[] args) {
        // Code to run a portfolio simulation. Feel free to modify as you wish.
        Cash startingCash = new Cash(1000.0);
        Portfolio portfolio = new Portfolio(startingCash);
        System.out.printf("The starting portfolio value is %f%n", portfolio.getValue(0));
        BondInvestment tBill = new BondInvestment("Treasury Bill", 0.05, 100);
        StockInvestment googleStock = new StockInvestment("Google Stock", 0.1, 100, 0.01);
        try {
            portfolio.buy(tBill);
            portfolio.buy(googleStock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.printf("The portfolio value after buying the stock and bond is %f%n", portfolio.getValue(0));
        System.out.printf("The portfolio's cash value after buying the stock and bond is %f%n", portfolio.getCashValue());

        int oneYear = 365;
        System.out.printf("The portfolio value after 1 year is %f%n", portfolio.getValue(oneYear));
        try {
            portfolio.sell(googleStock.getName(), oneYear);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.printf("The portfolio's cash value after selling the Google stock after a year is %f%n", portfolio.getCashValue());
    }
}