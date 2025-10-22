package com.example.uno.GUI;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class InicializadorDeJugadores extends VBox {
    private Label labelIngresar;
    private TextField campoNombre;
    private String stringLeido;
    private Label labelBienvenida;
    private int numeroJugador;
    private Button aceptar;

    public InicializadorDeJugadores(int numeroDelJugador) {
        this.numeroJugador = numeroDelJugador;
        this.stringLeido = null;

        this.labelIngresar = new Label();
        this.labelBienvenida = new Label();
        this.campoNombre = new TextField();
        this.aceptar = new Button("ACEPTAR");

        setSpacing(25);
        setAlignment(Pos.CENTER);
        setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #ffcc00, #ff3300);" +
                        "-fx-padding: 40;" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-radius: 25;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 8);"
        );

        Font pressStart = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                28
        );

        Font pressStartChica = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                18
        );

        labelIngresar.setFont(pressStartChica);
        labelIngresar.setStyle("-fx-text-fill: #1a1a1a;");
        labelIngresar.setText("Jugador " + numeroJugador + ", ¡ingresa tu nombre!");

        campoNombre.setFont(pressStartChica);
        campoNombre.setPromptText("Escribe tu nombre...");
        campoNombre.setMaxWidth(350);
        campoNombre.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 10;" +
                        "-fx-text-fill: #222;"
        );

        aceptar.setFont(pressStart);
        aceptar.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12 24;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-cursor: hand;"
        );

        aceptar.setOnMouseEntered(e -> aceptar.setStyle(
                "-fx-background-color: #ff0000;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12 24;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-cursor: hand;"
        ));

        aceptar.setOnMouseExited(e -> aceptar.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12 24;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;"
        ));

        labelBienvenida.setFont(pressStart);
        labelBienvenida.setStyle("-fx-text-fill: #000000;");

        DropShadow sombraTexto = new DropShadow();
        sombraTexto.setColor(Color.gray(0, 0.3));
        sombraTexto.setRadius(4);
        labelIngresar.setEffect(sombraTexto);

        this.getChildren().addAll(labelIngresar, campoNombre, labelBienvenida, aceptar);
    }

    public void pedirNombre() {
        this.stringLeido = null;
        labelIngresar.setText("Jugador " + numeroJugador + ", ingresa tu nombre:");

        aceptar.setOnMouseClicked(e -> {
            stringLeido = campoNombre.getText();
            if (stringLeido != null && !stringLeido.isEmpty()) {
                labelBienvenida.setText("¡BIENVENIDO " + stringLeido.toUpperCase() + "!");
                mostrarAnimacionBienvenida();
            }
        });
    }

    private void mostrarAnimacionBienvenida() {
        FadeTransition ft = new FadeTransition(Duration.millis(600), labelBienvenida);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }

    public int getNumeroJugador() { return numeroJugador; }

    public void setNumeroJugador(int numeroJugador) {
        this.numeroJugador = numeroJugador;
        labelIngresar.setText("Jugador " + numeroJugador + ", ingresa tu nombre:");
    }

    public String getNombreEscrito() { return campoNombre.getText(); }

    public void reset() {
        campoNombre.clear();
        labelBienvenida.setText("");
        stringLeido = null;
    }

    public void mostrarTextoBienvenida(String nombre) {
        this.stringLeido = nombre;
        labelBienvenida.setText("¡Bienvenidos todos!");
    }

    public Button getAceptar() { return aceptar; }
    public String getStringLeido() { return stringLeido; }
}
