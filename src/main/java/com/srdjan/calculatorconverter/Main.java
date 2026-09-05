package com.srdjan.calculatorconverter;

import com.srdjan.calculatorconverter.ui.CalculatorView;
import com.srdjan.calculatorconverter.ui.ConverterView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab calculatorTab = new Tab("Calculator", new CalculatorView());
        calculatorTab.setClosable(false);

        Tab converterTab = new Tab("Unit Converter", new ConverterView());
        converterTab.setClosable(false);

        tabPane.getTabs().addAll(calculatorTab, converterTab);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("Calculator & Unit Converter");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}