package com.example.uno.GUI;

import com.example.uno.controllers.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Tester extends Application {
    @Override
    public void start(Stage stage) {
        // Prueba con 3 jugadores
        new Controlador(stage, 3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

