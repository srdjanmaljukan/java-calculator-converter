package com.srdjan.calculatorconverter.ui;

import com.srdjan.calculatorconverter.logic.CalculatorEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalculatorView extends VBox {

    private final CalculatorEngine engine = new CalculatorEngine();

    private final TextField inputA = new TextField();
    private final TextField inputB = new TextField();
    private final ComboBox<String> operationBox = new ComboBox<>();
    private final Label resultLabel = new Label("Result: ");

    public CalculatorView() {
        setSpacing(10);
        setPadding(new Insets(15));

        operationBox.getItems().addAll(
                "Add (+)", "Subtract (-)", "Multiply (*)", "Divide (/)",
                "Square Root (√a)", "Power (a^b)", "Percentage (b% of a)"
        );
        operationBox.setValue("Add (+)");

        inputA.setPromptText("Value A");
        inputB.setPromptText("Value B");

        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculate());

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, new Label("A:"), inputA);
        inputGrid.addRow(1, new Label("B:"), inputB);

        HBox controls = new HBox(10, operationBox, calculateButton);
        controls.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(inputGrid, controls, resultLabel);
    }

    private void calculate() {
        if (inputA.getText().isBlank() || inputB.getText().isBlank()) {
            resultLabel.setText("Result: please fill in all fields");
            return;
        }

        try {
            double a = Double.parseDouble(inputA.getText());
            String operation = operationBox.getValue();
            double result;

            switch (operation) {
                case "Add (+)" -> result = engine.add(a, parseB());
                case "Subtract (-)" -> result = engine.subtract(a, parseB());
                case "Multiply (*)" -> result = engine.multiply(a, parseB());
                case "Divide (/)" -> result = engine.divide(a, parseB());
                case "Square Root (√a)" -> result = engine.squareRoot(a);
                case "Power (a^b)" -> result = engine.power(a, parseB());
                case "Percentage (b% of a)" -> result = engine.percentage(a, parseB());
                default -> throw new IllegalStateException("Unknown operation");
            }

            resultLabel.setText("Result: " + formatResult(result));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: invalid input");
        } catch (ArithmeticException ex) {
            resultLabel.setText("Result: " + ex.getMessage());
        }
    }

    private double parseB() {
        return Double.parseDouble(inputB.getText());
    }

    private String formatResult(double result) {
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.format("%.0f", result);
        }
        return String.format("%.4f", result);
    }
}