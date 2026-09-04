package com.srdjan.calculatorconverter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane(new Label("Calculator / Unit Converter"));
        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle("Calculator & Unit Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}