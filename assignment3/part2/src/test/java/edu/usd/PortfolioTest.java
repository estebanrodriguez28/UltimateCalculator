package edu.usd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    // Create a mock BondInvestment.
    BondInvestment mockBondInvestment;
    // Create a mock StockInvestment.
    StockInvestment mockStockInvestment;
    Portfolio portfolio;

    @BeforeEach
    public void setUp() {
        mockBondInvestment = new BondInvestment("treasury", 0.05, 1000.0);
        mockStockInvestment = new StockInvestment("amazon", 0.05, 1000.0, 0.5);
        portfolio = new Portfolio(new Cash(2000));
    }

    @Test
    void buy() {
        try {
            portfolio.buy(mockBondInvestment);
        } catch (Exception e) {
            System.out.println("Insufficient funds");
        }

        assertEquals(2000 - mockBondInvestment.getStartingValue(), portfolio.getCashValue());
    }

    @Test
    void sell() {
        try {
            portfolio.buy(mockBondInvestment);
        } catch (Exception e) {
            System.out.println("Insufficient funds");
        }

        try {
            portfolio.sell("treasury", 200);
        } catch (Exception e) {
            System.out.println("Unable to sell");
        }

        assertNotEquals(2000, portfolio.getCashValue());

    }

    @Test
    void getValue() {
        try {
            portfolio.buy(mockBondInvestment);
            portfolio.buy(mockStockInvestment);
        } catch (Exception e) {
            System.out.println("Insufficient funds");
        }

        assertNotEquals(portfolio.getCashValue(), portfolio.getValue(700));
    }
}