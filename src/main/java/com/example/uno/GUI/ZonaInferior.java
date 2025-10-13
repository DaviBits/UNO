package com.example.uno.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ZonaInferior extends HBox {
    private Button aceptar;
    private Button pasar;
    private Button tomar;

    public ZonaInferior(){
        Font pressStart = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                32
        );

        Font pressStartChica = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                20
        );

        this.setSpacing(20);
        setAlignment(Pos.CENTER);
        aceptar=new Button("aceptar");
        pasar=new Button("pasar");
        tomar=new Button("tomar");

        aceptar.setFont(pressStart);
        pasar.setFont(pressStart);
        tomar.setFont(pressStart);

        aceptar.setOnMouseClicked(e->{});
        pasar.setOnMouseClicked(e->{});
        tomar.setOnMouseClicked(e->{});

        this.getChildren().addAll(aceptar, tomar, pasar);


    }
}
