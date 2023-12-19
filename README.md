

The Ultimate Calculator

This is the ultimate calculator that has the ability to perform arithmetic, investment, grade, physics calculations as well as unit conversions. 

## Description

Arithmetic Calculator:The arithmetic calculator solves all of the simple to complex arithmetic problems that you have. It will start by bringing up a calculator screen and once the user inputs an arithmetic expression and hits the equals sign then they will be given the answer to their expression. It will also show what the inputted expression was right above. The arithmetic calculator can handle all forms of precedence as well as parentheses. This calculator is for anyone who needs to solve a math equation. 

Grade Calculator: Create a new tab for each class that you are currently taking. Within this tab fill out the components and their weights as found in the class syllabus. Enter all assignments, excluding your final, and choose which component they fall under. You can then calculate the score required on your final exam to achieve your desired grade.

Financial Calculator: The financial calculator asks the user for the starting amount, rate of return, years to grow, and monthly contribution. It then calculates the starting amount, total contribution, total interest earned, and ending balance for every year and displays the results in a table.

Unit Converter Calculator: Converts between different units, the categories are: Weight, Length, and temperature. Within those categories there are different units to choose from for example in the weight you can choose Pounds or in the temperature you can choose Celsius, etc. The user selects one of the categories from the first dropdown menu and then selects a unit within the category to convert from and then another unit to convert to. You can only convert between units within the same category. Then the user types a number they want converted from the unit they choose to the other unit they chose, then they click calculate and the converted value is displayed.

## Getting Started

First make sure that you have all folders downloaded, especially those is src, and gradle. Then simply run gradle and you will be greeted by our home screen where you can choose which calculator you would like. 

### Prerequisites

You must have Java and Javafx installed and be created with JDK 17. The application should be run on IntelliJ.

### Dependencies

You can look in the gradle.build file for all dependencies as well as below:

dependencies {
   testImplementation platform('org.junit:junit-bom:5.9.1')
   testImplementation 'org.junit.jupiter:junit-jupiter'
   testImplementation 'org.junit.jupiter:junit-jupiter-params'
   implementation 'com.google.guava:guava:31.1-jre'
       implementation('com.dlsc.formsfx:formsfx-core:11.6.0') {
       exclude(group: 'org.openjfx')
   }
   implementation('org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0')
   testImplementation 'junit:junit:4.13.1'
}


### Installing

First download and install Intellij for your IDE as it comes preset with the ability to create a project with JavaFX. Then once you create the project create it with Java as the language,  Gradle as your build tool, and JDK 17.0. 

## Testing

To run the tests, simply navigate to the test folder under src directory and click on whichever folder you would like to test. Once in the test file simply click run and ensure all tests pass. We used junit testing framework and followed TDD.
