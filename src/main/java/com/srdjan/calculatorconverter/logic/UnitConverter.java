package com.srdjan.calculatorconverter.logic;

import java.util.LinkedHashMap;
import java.util.Map;

public class UnitConverter {

    public enum Category {
        LENGTH, MASS, VOLUME, TIME, TEMPERATURE
    }

    // Conversion factors relative to a base unit per category
    // (ignored for TEMPERATURE, which uses dedicated formulas)
    private final Map<Category, Map<String, Double>> factors = new LinkedHashMap<>();

    public UnitConverter() {
        Map<String, Double> length = new LinkedHashMap<>();
        length.put("Meters", 1.0);
        length.put("Kilometers", 1000.0);
        length.put("Centimeters", 0.01);
        length.put("Millimeters", 0.001);
        length.put("Miles", 1609.344);
        length.put("Feet", 0.3048);
        length.put("Inches", 0.0254);
        factors.put(Category.LENGTH, length);

        Map<String, Double> mass = new LinkedHashMap<>();
        mass.put("Kilograms", 1.0);
        mass.put("Grams", 0.001);
        mass.put("Pounds", 0.45359237);
        mass.put("Ounces", 0.0283495231);
        factors.put(Category.MASS, mass);

        Map<String, Double> volume = new LinkedHashMap<>();
        volume.put("Liters", 1.0);
        volume.put("Milliliters", 0.001);
        volume.put("Gallons (US)", 3.785411784);
        volume.put("Cubic Meters", 1000.0);
        factors.put(Category.VOLUME, volume);

        Map<String, Double> time = new LinkedHashMap<>();
        time.put("Seconds", 1.0);
        time.put("Minutes", 60.0);
        time.put("Hours", 3600.0);
        time.put("Days", 86400.0);
        factors.put(Category.TIME, time);

        // Temperature has no factor map; handled separately in convertTemperature()
        factors.put(Category.TEMPERATURE, new LinkedHashMap<>() {{
            put("Celsius", null);
            put("Fahrenheit", null);
            put("Kelvin", null);
        }});
    }

    public String[] getUnits(Category category) {
        return factors.get(category).keySet().toArray(new String[0]);
    }

    public double convert(Category category, String fromUnit, String toUnit, double value) {
        if (category == Category.TEMPERATURE) {
            return convertTemperature(fromUnit, toUnit, value);
        }

        double fromFactor = factors.get(category).get(fromUnit);
        double toFactor = factors.get(category).get(toUnit);

        double baseValue = value * fromFactor;
        return baseValue / toFactor;
    }

    private double convertTemperature(String fromUnit, String toUnit, double value) {
        double celsius = switch (fromUnit) {
            case "Celsius" -> value;
            case "Fahrenheit" -> (value - 32) * 5.0 / 9.0;
            case "Kelvin" -> value - 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + fromUnit);
        };

        return switch (toUnit) {
            case "Celsius" -> celsius;
            case "Fahrenheit" -> celsius * 9.0 / 5.0 + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + toUnit);
        };
    }
}