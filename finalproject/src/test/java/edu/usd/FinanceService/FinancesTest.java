import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class FinancesTest {
    // We are testing the Finances class
    private Finances testObject;
    private double startingAmount;
    private double rateOfReturn;
    private double yearsToGrow;
    private double monthlyContribution;


    @BeforeEach
    void setUp(){
    // Create a new financialComponent Object before each
    startingAmount = 100.0;
    rateOfReturn = 0.07;
    yearsToGrow = 5.0;
    monthlyContribution = 50.0;
    testObject = new Finances(new FinancialComponent(startingAmount, rateOfReturn, yearsToGrow, monthlyContribution), new ArrayList<>());
    }

    // Test checks the CalculateContribution() method
    @Test
    @DisplayName("Checks if calculateContribution() returns the total contribution")
    void testCalculateContribution(){
        double expectedContribution = 3000.0;
        assertEquals(expectedContribution, testObject.calculateContribution());
    }
    // Test checks the CalculateInterest() method
    @Test
    @DisplayName("Checks if calculateInterest() returns the total interest earned")
    void testCalculateInterest() {
        double expectedInterest = testObject.calculateInterest();
        assertEquals(expectedInterest, testObject.calculateInterest());
    }

    // Test checks the calculateEndingBalance() method
    @Test
    @DisplayName("Checks if calculateEndingBalance() returns the ending balance")
    void testCalculateEndingBalance() {
    double expectedBalance = testObject.calculateEndingBalance(yearsToGrow);
    assertEquals(expectedBalance, testObject.calculateEndingBalance(yearsToGrow));
}


    //Test checks the getCalculation() method
    @Test
    @DisplayName("Checks if getCalculations() returns the calculations")
    void testGetCalculations() {
        List<Double> expectedCalculations = new ArrayList<>();
        expectedCalculations.add(testObject.calculateContribution());
        expectedCalculations.add(testObject.calculateInterest());
        expectedCalculations.add(testObject.calculateEndingBalance(yearsToGrow)); // Provide the year parameter
        assertIterableEquals(expectedCalculations, testObject.getCalculations());
    }   


 
    //Test checks the getComponent() method
    @Test
    @DisplayName("Checks if getComponent() returns the financial object instance")
    void testGetComponent() {
        FinancialComponent expectedComponent = new FinancialComponent(startingAmount, rateOfReturn, yearsToGrow, monthlyContribution);
        assertEquals(expectedComponent.getStartingAmount(), testObject.getComponent().getStartingAmount());
        assertEquals(expectedComponent.getRateOfReturn(), testObject.getComponent().getRateOfReturn());
        assertEquals(expectedComponent.getYearsToGrow(), testObject.getComponent().getYearsToGrow());
        assertEquals(expectedComponent.getMonthlyContribution(), testObject.getComponent().getMonthlyContribution());
    }


}
