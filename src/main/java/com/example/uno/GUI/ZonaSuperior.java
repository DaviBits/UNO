package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ZonaSuperior extends HBox {
    private Label labelJugador;
    private String nombreJugador;
    private Carta carta;
    private ImageView cartaGrafica;
    private StackPane cartaContenedor;

    public ZonaSuperior(Carta cartaTablero, String nombreJugador) {
        this.carta = cartaTablero;
        this.nombreJugador = nombreJugador;
        this.labelJugador = new Label();

        setSpacing(50);
        setAlignment(Pos.CENTER);
        setStyle(
                "-fx-background-color: linear-gradient(to right, #ffcc00, #ff9900);" +
                        "-fx-padding: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 5);"
        );

        Font pressStart = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                28
        );

        labelJugador.setFont(pressStart);
        labelJugador.setStyle("-fx-text-fill: #1a1a1a;");
        labelJugador.setText(nombreJugador);

        crearImagenCarta(carta);
        this.getChildren().addAll(labelJugador, cartaContenedor);
    }

    private void crearImagenCarta(Carta carta) {
        if (carta == null) return;

        String nombreImg = carta.getNombreImagen();
        Image imagen = new Image(getClass().getResourceAsStream("/CartasUno/" + nombreImg));
        cartaGrafica = new ImageView(imagen);
        cartaGrafica.setFitHeight(160);
        cartaGrafica.setFitWidth(110);

        cartaContenedor = new StackPane(cartaGrafica);
        cartaContenedor.setAlignment(Pos.CENTER);
        cartaContenedor.setStyle("-fx-background-radius: 10; -fx-padding: 5;");

        // sombra bajo la carta
        DropShadow sombra = new DropShadow();
        sombra.setColor(Color.gray(0, 0.4));
        sombra.setRadius(10);
        cartaGrafica.setEffect(sombra);

        // animación suave al aparecer
        ScaleTransition animacion = new ScaleTransition(Duration.millis(400), cartaContenedor);
        animacion.setFromX(0.8);
        animacion.setFromY(0.8);
        animacion.setToX(1);
        animacion.setToY(1);
        animacion.play();
    }

    public void actualizar(String nombreJugador, Carta cartaNueva) {
        this.nombreJugador = nombreJugador;
        this.labelJugador.setText(nombreJugador);
        this.carta = cartaNueva;

        if (cartaContenedor != null) {
            this.getChildren().remove(cartaContenedor);
        }

        crearImagenCarta(cartaNueva);
        this.getChildren().add(cartaContenedor);
    }

    public void colorearMarcoDeLaCarta(String color) {
        if (cartaContenedor == null) return;
        if (color == null) {
            cartaContenedor.setStyle("-fx-border-color: transparent;");
            return;
        }

        String key = color.trim().toLowerCase();
        String estilo;
        Color glowColor;

        switch (key) {
            case "rojo" -> {
                estilo = "-fx-border-color: #ba1a1a; -fx-border-width: 4; -fx-border-radius: 10;";
                glowColor = Color.RED;
            }
            case "azul" -> {
                estilo = "-fx-border-color: #142259; -fx-border-width: 4; -fx-border-radius: 10;";
                glowColor = Color.DEEPSKYBLUE;
            }
            case "verde" -> {
                estilo = "-fx-border-color: #47ba1a; -fx-border-width: 4; -fx-border-radius: 10;";
                glowColor = Color.LIMEGREEN;
            }
            case "amarillo" -> {
                estilo = "-fx-border-color: #ffc100; -fx-border-width: 4; -fx-border-radius: 10;";
                glowColor = Color.GOLD;
            }
            default -> {
                estilo = "-fx-border-color: transparent;";
                glowColor = null;
            }
        }

        cartaContenedor.setStyle(estilo);

        // efecto glow opcional
        if (glowColor != null) {
            DropShadow glow = new DropShadow();
            glow.setColor(glowColor);
            glow.setRadius(20);
            cartaContenedor.setEffect(glow);
        } else {
            cartaContenedor.setEffect(null);
        }
    }

    // Getters / Setters
    public void setNombreJugador(String nombreJugador) { this.nombreJugador = nombreJugador; }
    public void setCarta(Carta carta) { this.carta = carta; }
    public void setLabelJugador(Label labelJugador) { this.labelJugador = labelJugador; }
}
