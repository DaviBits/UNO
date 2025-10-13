package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ZonaSuperior extends HBox {
    private Label labelJugador;
    private String nombreJugador;
    private Carta carta;
    private ImageView cartaGrafica;

    public ZonaSuperior(Carta cartaTablero, String nombreJugador){
        carta=cartaTablero;
        this.nombreJugador=nombreJugador;
        this.labelJugador=new Label();
        setSpacing(30);
        setAlignment(Pos.CENTER);

        Font pressStart = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                32
        );

        Font pressStartChica = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                20
        );

        labelJugador.setFont(pressStart);
        labelJugador.setStyle("-fx-text-fill: #000000; ");
        labelJugador.setText(nombreJugador);

        cartaGrafica=crearImagenCarta(carta);

        this.getChildren().addAll(labelJugador, cartaGrafica);
    }

    private ImageView crearImagenCarta(Carta carta){
        if (carta == null) return new ImageView();

        String nombreImg = carta.getNombreImagen(); // por ejemplo "rojo_5.png"
        Image imagen = new Image(getClass().getResourceAsStream("/CartasUno/" + nombreImg));
        ImageView imgView = new ImageView(imagen);
        imgView.setFitHeight(150);
        imgView.setFitWidth(100);
        return imgView;
    }
    public void setNombreJugador(String nombreJugador) {this.nombreJugador = nombreJugador;}
    public void setCarta(Carta carta) {this.carta = carta;}
    public void setLabelJugador(Label labelJugador) {this.labelJugador = labelJugador;}
}
