package com.example.uno.GUI;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PantallaGanador extends VBox {
    public PantallaGanador(String nombreGanador) {
        Font pressStartChica = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                24
        );
        Label lbl = new Label("¡EL GANADOR ES " + nombreGanador + "!");
        lbl.setFont(pressStartChica);
        lbl.setStyle("-fx-text-fill: #000000;");
        setAlignment(Pos.CENTER);
        getChildren().add(lbl);
    }
}
