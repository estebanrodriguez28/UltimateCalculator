import java.beans.Transient;
import java.lang.Math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class KineticEnergyCalculatorTest {
    KineticEnergyCalculator kineticEnergyCalculator;

    @BeforeEach
    void setup() {
        kineticEnergyCalculator = new KineticEnergyCalculator();
    }

    @Test
    @DisplayName("Test Getters")
    void getInputsTest() {
        String[] expectedInputs = {"Test", "Test", "Test"};
        kineticEnergyCalculator = new KineticEnergyCalculator(expectedInputs, "Test");
        String[] inputs = kineticEnergyCalculator.getInputs();
        assertEquals("Test", inputs[0], "Get Inputs Failed.");
        assertEquals("Test", inputs[1], "Get Inputs Failed.");
        assertEquals("Test", inputs[2], "Get Inputs Failed.");

        assertEquals("Test", potentialEnergyCalculator.getOption(), "Get Option Failed.");
    }

    @Test
    @DisplayName("Test Value Setter")
    void setValuesTest() {
        String[] expectedInputs = { "1.0", "2.0", "1.0" };
        kineticEnergyCalculator.setValues(expectedInputs, "Test");

        String[] inputs = kineticEnergyCalculator.getInputs();
        assertEquals("1.0", inputs[0], "Set Inputs Failed.");
        assertEquals("2.0", inputs[1], "Set Inputs Failed.");
        assertEquals("1.0", inputs[2], "Set Inputs Failed.");

        assertEquals("Test", kineticEnergyCalculator.getOption(), "Set Option Failed.");
    }

    @Test
    @DisplayName("Test Kinetic Energy Calculator")
    void kineticEnergyTest() {
        String[] inputs = { null, "5.0", "2.0" };
        kineticEnergyCalculator.setValues(inputs, "Kinetic Energy");

        double mass = Double.parseDouble(inputs[1]);
        double velocity = Double.parseDouble(inputs[2]);
        double expectedKE = 0.5 * mass * Math.pow(velocity, 2); // ke = 1/2mv^2

        assertEquals(expectedKE, kineticEnergyCalculator.calculateKineticEnergy(),
                "Kinetic Energy Calculation Failed.");
    }
    
    @Test
    @DisplayName("Test Mass Calculation")
    void massTest() {
        String[] inputs = { "16.0", null, "4.0" };
        kineticEnergyCalculator.setValues(inputs, "Mass");

        double kineticEnergy = Double.parseDouble(inputs[0]);
        double velocity = Double.parseDouble(inputs[2]);
        double expectedMass = kineticEnergy / (0.5 * Math.pow(velocity, 2)); // m = ke / (1/2v^2)

        assertEquals(expectedMass, kineticEnergyCalculator.calculateMass(), "Mass Calculation Failed.");
    }

    @Test
    @DisplayName("Test Velocity Calculation")
    void velocityTest() {
        String[] inputs = { "16.0", "2.0", null };
        kineticEnergyCalculator.setValues(inputs, "Velocity");

        double kineticEnergy = Double.parseDouble(inputs[0]);
        double mass = Double.parseDouble(inputs[1]);
        double expectedVelocity = Math.sqrt(kineticEnergy / (0.5 * mass)); //  v = sqrt(ke / (1/2m))

        assertEquals(expectedVelocity, kineticEnergyCalculator.calculateVelocity(), "Velocity Calculation Failed.");
    }

    @Parameterized
    @DisplayName("Test Calculation Method")
    @CsvSource({
            "'Kinetic Energy',   16.0",
            "Mass,                 2.0",
            "Velocity,               4.0"
    })
    void calculateTest(String option, double expected) {
        String[] input = { "16.0", "2.0", "4.0" };

        assertEquals(expected, kineticEnergyCalculator.calculate(input, option),
                "Calculate method failed " + option + "calculation.");
    }

    @Parameterized
    @DisplayName("Test Insufficient Information Error")
    @ValueSource(Strings = { "Kinetic Energy", "Mass", "Velocity" })
    void testInformationError(String option) {
        String[] input = { null, null, null };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kineticEnergyCalculator.calculate(input, option));
        assertEquals("Not enough information to compute.", exception.getMessage());
    }

    @Parameterized
    @DisplayName("Test Mass <= 0 Error")
    @ValueSource(Strings = { "Kinetic Energy", "Velocity" })
    void testMassError(String option) {
        String[] input = { "1.0", "-1.0", "1.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kineticEnergyCalculator.calculate(input, option));
        assertEquals("Mass cannot be negative or zero.", exception.getMessage());
    }

    @Parameterized
    @DisplayName("Test Kinetic Energy < 0 Throwable")
    @ValueSource(Strings = { "Mass", "Velocity" })
    void testKineticEnergyThrowable(String option) {
        String[] input = { "-2.0", "1.0", "1.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kineticEnergyCalculator.calculate(input, option));
        assertEquals("Kinetic Energy cannot be negative.", exception.getMessage());
    }

    @Parameterized
    @DisplayName("Test Number Format Exception")
    @ValueSource(Strings = { "Kinetic Energy", "Mass", "Velocity"  })
    void numberFormatExceptionTest(String option) {
        String[] input = { "Test", "Test", "Test" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kineticEnergyCalculator.calculate(input, option));
        assertEquals("Variables must be numbers.", exception.getMessage());
    }
}