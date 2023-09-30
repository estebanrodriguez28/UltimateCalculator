
/*
 * This class converts temperature between Celsius and Fahrenheit.
 * It has many dirty and missing comments.
 * Add, remove, and modify comments to adhere to clean coding principles.
 */
public class DirtyComments {

    /**
     * This variable stores the Celsius value.
     */
    private double celsiusTemperature; 

    /**
     * This variable stores the Fahrenheit value.
     */
    private double fahrenheitTemperature;

    // Constructor that initializes the Celsius temperature
    public DirtyComments(double celsius) {
        this.celsiusTemperature = celsius;
        fahrenheitTemperature = convertToFahrenheit(celsiusTemperature);
    }

    public static void main(String[] args) {
        DirtyComments converter = new DirtyComments(25.0);
        // Printing the conversion from 25°C to Fahrenheit
        System.out.println("25°C is equivalent to " + converter.getFahrenheitTemperature() + "°F");
    }

    public double convertToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0/9.0;
    }

    public double getFahrenheitTemperature() {
        return fahrenheitTemperature;
    }

    public double getCelsiusTemperature() {
        // Gets the converted Celsius temperature
        return celsiusTemperature;
    }

    public double convertToFahrenheit(double celsius) {
        // Here we convert to the Fahrenheit temperature scale.
        // While much of the world uses Celsius, Fahrenheit is still standard in many countries.
        // The Fahrenheit temperature scale was first proposed by physicist Daniel Gabriel Fahrenheit.
        // He began training as a merchant in Amsterdam until both his parents died from eating poisonous mushrooms.
        // They ate death cap mushrooms, which are particularly dangerous since they resemble edible mushrooms.
        // Interestingly enough, the Roman Emperor Claudius may have succumbed to the same fate as Fahrenheit's parents.
        // Claudius was an able and effective administrator, and unfortunately, his death ensured that Nero came to power.
        // Nero is famous for the legend that he fiddled while Rome burned.
        // It suffices to say that Nero is not in the pantheon of great Roman Emperors.
        return celsius * 9.0/5.0 + 32;
    }

    /*
    // Previous implementation. Keeping in case it is needed later.
    public double convertToFahrenheit(double celsius) {
        return (celsius * 1.8) + 32;
    }
    */
}