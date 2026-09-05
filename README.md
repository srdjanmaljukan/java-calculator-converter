# Calculator & Unit Converter

A desktop application built with JavaFX combining a scientific calculator and a multi-category unit converter in a single tabbed interface.

## Features

### Calculator
- Basic arithmetic operations: addition, subtraction, multiplication, division
- Advanced functions: square root, exponentiation (power), percentage
- Input validation and error handling (e.g. division by zero, invalid input)

### Unit Converter
- Length (meters, kilometers, centimeters, millimeters, miles, feet, inches)
- Mass (kilograms, grams, pounds, ounces)
- Temperature (Celsius, Fahrenheit, Kelvin)
- Volume (liters, milliliters, gallons, cubic meters)
- Time (seconds, minutes, hours, days)

## Tech Stack

- **Java 25**
- **JavaFX 21** — GUI framework
- **Maven** — build and dependency management

## Running the Project

### Requirements
- JDK 21 or higher
- Maven (or use your IDE's bundled Maven support)

### Via IDE (recommended)
Open the project in IntelliJ IDEA (or another Maven-aware IDE), then run the `javafx:run` Maven goal:
- **Maven panel → Plugins → javafx → javafx:run**

### Via command line
```bash
mvn clean javafx:run
```

## Architecture Notes

- Business logic (`logic` package) is fully decoupled from the UI (`ui` package), making the conversion and calculation logic independently testable.
- Unit conversions use a base-unit approach (e.g. meters for length, seconds for time) except temperature, which requires dedicated formulas due to its non-linear (offset-based) conversion.