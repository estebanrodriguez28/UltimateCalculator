import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class FinancialComponentTest {
    private FinancialComponent testObject;
    private double startingAmount;
    private double rateOfReturn;
    private double yearsToGrow;
    private double monthlyContribution;

    @BeforeEach
    void setUp(){
    // Create a new financialComponent Object before each test
    startingAmount = 100.0;
    rateOfReturn = 0.07;
    yearsToGrow = 5.0;
    monthlyContribution = 50.0;
    testObject = new FinancialComponent(startingAmount, rateOfReturn, yearsToGrow, monthlyContribution);
    }

    // Test getStartingAmount() method
    @Test
    @DisplayName("Checks if getStartingAmount() returns the starting amount")
    void testGetStartingAmount(){
        double expectedStartingAmount = 100.0;
        assertEquals(expectedStartingAmount, testObject.getStartingAmount());
    }

    // Test getRateOfReturn() method
    @Test
    @DisplayName("Checks if getRateOfReturn() returns the rate of return")
    void testGetRateOfReturn(){
        double expectedRateOfReturn = 0.07;
        assertEquals(expectedRateOfReturn, testObject.getRateOfReturn());
    }

    // Test getYearsToGrow() method
    @Test
    @DisplayName("Checks if getYearsToGrow() returns the number of years to grow")
    void testGetYearsToGrow(){
        double expectedYearsToGrow = 5.0;
        assertEquals(expectedYearsToGrow, testObject.getYearsToGrow());
    }

    // Test getMonthlyContribution() method
    @Test
    @DisplayName("Checks if getMonthlyContribution() returns the monthly contribution")
    void testGetMonthlyContribution(){
        double expectedMonthlyContribution = 50.0;
        assertEquals(expectedMonthlyContribution, testObject.getMonthlyContribution());
    }

    // Test setStartingAmount() method
    @Test
    @DisplayName("Checks if setStartingAmmount() sets the starting amount")
    void testSetStartingAmount() {
        double expectedStartingAmount = 100.0;
        testObject.setStartingAmount(expectedStartingAmount);
        assertEquals(expectedStartingAmount, testObject.getStartingAmount());
    }

    // Test the setRateOfReturn() method
    @Test
    @DisplayName("Checks if setRateOfReturn() sets the rate of return")
    void testSetRateOfReturn() {
        double expectedRateOfReturn = 0.07;
        testObject.setRateOfReturn(expectedRateOfReturn);
        assertEquals(expectedRateOfReturn, testObject.getRateOfReturn());
    }

    // Test the setYearsToGrow method
    @Test
    @DisplayName("Checks if setYearsToGrow() sets the number of years to grow")
    void testSetYearsToGrow() {
        double expectedYearsToGrow = 5.0;
        testObject.setYearsToGrow(expectedYearsToGrow);
        assertEquals(expectedYearsToGrow, testObject.getYearsToGrow());
    }

    // Test setMonthlyContribution method
    @Test
    @DisplayName("Checks if setMonthlyContribution() sets the monthly contribution")
    void testSetMonthlyContribution() {
        double expectedMonthlyContribution = 50.0;
        testObject.setMonthlyContribution(expectedMonthlyContribution);
        assertEquals(expectedMonthlyContribution, testObject.getMonthlyContribution());
    }
    
}
