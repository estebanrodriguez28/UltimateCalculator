package edu.usd.PhysicsService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PotentialEnergyCalculatorTest {
    PotentialEnergyCalculator potentialEnergyCalculator;

    @BeforeEach
    void setup() {
        potentialEnergyCalculator = new PotentialEnergyCalculator();
    }

    @Test
    @DisplayName("Test Getter for Inputs")
    void getInputsTest() {
        String[] inputs = potentialEnergyCalculator.getInputs();
        assertEquals(null, inputs[0], "Get Inputs Failed.");
        assertEquals(null, inputs[1], "Get Inputs Failed.");
        assertEquals(null, inputs[2], "Get Inputs Failed.");
    }

    @Test
    @DisplayName("Test Getter for Option")
    void getOptionTest() {
        assertEquals(null, potentialEnergyCalculator.getOption(), "Get Option Failed.");
    }

    @Test
    @DisplayName("Test Value Setter")
    void setValuesTest() {
        String[] expectedInputs = { "1.0", "2.0", "0.5" };
        potentialEnergyCalculator.setValues(expectedInputs, "Test");

        String[] inputs = potentialEnergyCalculator.getInputs();
        assertEquals("1.0", inputs[0], "Set Inputs Failed.");
        assertEquals("2.0", inputs[1], "Set Inputs Failed.");
        assertEquals("0.5", inputs[2], "Set Inputs Failed.");

        assertEquals("Test", potentialEnergyCalculator.getOption(), "Set Option Failed.");
    }

    @Test
    @DisplayName("Test Potential Energy Calculation")
    void potentialEnergyTest() {
        String[] inputs = { null, "5.0", "10.0" };
        potentialEnergyCalculator.setValues(inputs, "Potential Energy");

        double mass = Double.parseDouble(inputs[1]);
        double height = Double.parseDouble(inputs[2]);
        double expectedPE = mass * height;

        assertEquals(expectedPE, potentialEnergyCalculator.calculatePotentialEnergy(), "Potential Energy Calculation Failed.");
    }

    @Test
    @DisplayName("Test Mass Calculation")
    void massTest() {
        String[] inputs = { "10.0", null, "10.0" };
        potentialEnergyCalculator.setValues(inputs, "Mass");

        double potentialEnergy = Double.parseDouble(inputs[0]);
        double height = Double.parseDouble(inputs[2]);
        double expectedMass = potentialEnergy / (9.81 * height);

        assertEquals(expectedMass, potentialEnergyCalculator.calculateMass(), "Mass Calculation Failed.");
    }

    @Test
    @DisplayName("Test Height Calculation")
    void heightTest() {
        String[] inputs = { "10.0", "5.0", null };
        potentialEnergyCalculator.setValues(inputs, "Height");

        double potentialEnergy = Double.parseDouble(inputs[0]);
        double mass = Double.parseDouble(inputs[1]);
        double expectedHeight = potentialEnergy / (mass * 9.81);

        assertEquals(expectedHeight, potentialEnergyCalculator.calculateHeight(), "Height Calculation Failed.");
    }

    @ParameterizedTest
    @DisplayName("Test Calculation Method")
    @CsvSource({
            "'Potential Energy',   9.81",
            "Mass,                 1.0",
            "Height,               1.0"
    })
    void calculateTest(String option, double expected) {
        String[] input = { "9.81", "1.0", "1.0" };

        assertEquals(expected, potentialEnergyCalculator.calculate(input, option),
                "Calculate method failed " + option + "calculation.");
    }

    @ParameterizedTest
    @DisplayName("Test Insufficient Information Error")
    @ValueSource(strings = { "Potential Energy", "Mass", "Height" })
    void testInformationError(String option) {
        String[] input = { null, null, null };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> potentialEnergyCalculator.calculate(input, option));
        assertEquals("Not enough information to compute.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Mass <= 0 Error")
    @ValueSource(strings = { "Potential Energy", "Height" })
    void testMassError(String option) {
        String[] input = { "1.0", "-1.0", "1.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> potentialEnergyCalculator.calculate(input, option));
        assertEquals("Mass cannot be negative or zero.", exception.getMessage());
    }

    @Test
    @DisplayName("Test Mass Throwables")
    void testMassErrors() {
        String[] input = { "0.0", null, "0.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> potentialEnergyCalculator.calculate(input, "Mass"));
        assertEquals("Unable to calculate mass.", exception.getMessage());

        String[] secondInput = { "1.0", null, "0.0" };
        exception = assertThrows(IllegalArgumentException.class,
                () -> potentialEnergyCalculator.calculate(secondInput, "Mass"));
        assertEquals("Values don't make sense.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Number Format Exception")
    @ValueSource(strings = { "Potential Energy", "Mass", "Height" })
    void numberFormatExceptionTest(String option) {
        String[] input = { "Test", "Test", "Test" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> potentialEnergyCalculator.calculate(input, option));
        assertEquals("Variables must be numbers.", exception.getMessage());
    }
}
