package com.srdjan.calculatorconverter.ui;

import com.srdjan.calculatorconverter.logic.UnitConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConverterView extends VBox {

    private final UnitConverter converter = new UnitConverter();

    private final ComboBox<UnitConverter.Category> categoryBox = new ComboBox<>();
    private final ComboBox<String> fromUnitBox = new ComboBox<>();
    private final ComboBox<String> toUnitBox = new ComboBox<>();
    private final TextField valueField = new TextField();
    private final Label resultLabel = new Label("Result: ");

    public ConverterView() {
        setSpacing(10);
        setPadding(new Insets(15));

        categoryBox.getItems().addAll(UnitConverter.Category.values());
        categoryBox.setValue(UnitConverter.Category.LENGTH);
        categoryBox.setOnAction(e -> refreshUnits());

        valueField.setPromptText("Value");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, new Label("Category:"), categoryBox);
        grid.addRow(1, new Label("From:"), fromUnitBox);
        grid.addRow(2, new Label("To:"), toUnitBox);
        grid.addRow(3, new Label("Value:"), valueField);

        HBox controls = new HBox(10, convertButton);
        controls.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(grid, controls, resultLabel);

        refreshUnits(); // populate dropdowns for the initial category
    }

    private void refreshUnits() {
        UnitConverter.Category category = categoryBox.getValue();
        String[] units = converter.getUnits(category);

        fromUnitBox.getItems().setAll(units);
        toUnitBox.getItems().setAll(units);

        if (units.length > 0) {
            fromUnitBox.setValue(units[0]);
            toUnitBox.setValue(units.length > 1 ? units[1] : units[0]);
        }
    }

    private void convert() {
        if (valueField.getText().isBlank()) {
            resultLabel.setText("Result: please fill in all fields");
            return;
        }

        try {
            double value = Double.parseDouble(valueField.getText());
            UnitConverter.Category category = categoryBox.getValue();
            String from = fromUnitBox.getValue();
            String to = toUnitBox.getValue();

            double result = converter.convert(category, from, to, value);
            resultLabel.setText("Result: " + formatResult(result));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: invalid input");
        }
    }

    private String formatResult(double result) {
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.format("%.0f", result);
        }
        return String.format("%.4f", result);
    }
}