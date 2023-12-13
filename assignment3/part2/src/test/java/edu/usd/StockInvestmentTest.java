package edu.usd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockInvestmentTest {

    private StockInvestment stock;

    @BeforeEach
    public void setUp() {
        stock = new StockInvestment("amazon", 0.05, 1000.0, 0.5);
    }

    @Test
    void getValue() {
        double checkOne = stock.getValue(365);
        double checkTwo = stock.getValue(365);

        // return is randomized so check both to ensure randomInterestRate is static to instance
        assertEquals(checkOne, checkTwo);
    }
}