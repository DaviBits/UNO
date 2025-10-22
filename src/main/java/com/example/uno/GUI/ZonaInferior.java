package com.example.uno.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class ZonaInferior extends HBox {
    private Button aceptar;
    private Button pasar;
    private Button tomar;

    public ZonaInferior() {
        Font pressStart = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                22 // más pequeño para caber mejor
        );

        setSpacing(40);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff9900);" +
                "-fx-padding: 20; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20;");

        aceptar = crearBoton("ACEPTAR", "#47ba1a");  // Verde UNO
        tomar = crearBoton("TOMAR", "#142259");      // Azul UNO
        pasar = crearBoton("PASAR", "#ba1a1a");      // Rojo UNO

        aceptar.setFont(pressStart);
        pasar.setFont(pressStart);
        tomar.setFont(pressStart);

        getChildren().addAll(aceptar, tomar, pasar);
    }

    private Button crearBoton(String texto, String colorBase) {
        Button btn = new Button(texto);
        btn.setStyle(
                "-fx-background-color: " + colorBase + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;"
        );

        // Efecto sombra
        DropShadow sombra = new DropShadow();
        sombra.setColor(Color.BLACK);
        sombra.setRadius(6);
        btn.setEffect(sombra);

        // Efecto hover
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: derive(" + colorBase + ", 30%);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: " + colorBase + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;"
        ));
        return btn;
    }

    public Button getAceptar() {
        return aceptar;
    }

    public Button getTomar() {
        return tomar;
    }

    public Button getPasar() {
        return pasar;
    }
}
