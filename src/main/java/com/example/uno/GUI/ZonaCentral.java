package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.animation.FadeTransition;
import javafx.util.Duration;


import java.util.ArrayList;

public class ZonaCentral extends HBox {
    private ArrayList<Carta> cartas;
    private ArrayList <Button> cartasGraficas;
    private Button cartaSeleccionada = null;
    private Carta cartaJugador;
    private Label labelGanador;

    public ZonaCentral(ArrayList<Carta> cartas){
        Font pressStartChica = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P-Regular.ttf"),
                20
        );
        labelGanador=new Label();
        labelGanador.setFont(pressStartChica);
        labelGanador.setAlignment(Pos.CENTER);
        labelGanador.setStyle("-fx-text-fill: #000000; ");
        this.cartas=cartas;
        cartasGraficas=new ArrayList<>();
        this.cartaJugador=null;
        setSpacing(30);
        setAlignment(Pos.CENTER);


        crearCartasGraficas(cartas);
       //. this.getChildren().add(labelGanador);
        labelGanador.setVisible(false);


    }

    public void mostrarGanador(String ganador){
        labelGanador.setText("EL GANADOR ES "+ ganador +"!!");
        labelGanador.setVisible(true);

    }

    public void crearCartasGraficas(ArrayList<Carta> cartas){
        this.getChildren().clear();
        cartasGraficas.clear();

        for(Carta carta : cartas){
            if(carta!=null){
                ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/CartasUno/" + carta.getNombreImagen())));
                img.setFitHeight(150);
                img.setFitWidth(100);

                Button btn = new Button();
                btn.setGraphic(img);
                btn.setStyle("-fx-background-color: transparent;");

                // Evento de selección
                btn.setOnAction(e -> {
                    if (cartaSeleccionada != null) {
                        cartaSeleccionada.setStyle("-fx-background-color: transparent;");
                        cartaSeleccionada.setScaleX(1);
                        cartaSeleccionada.setScaleY(1);
                    }

                    cartaSeleccionada = btn;
                    this.cartaJugador = carta;

                    // Pequeño efecto de zoom
                    btn.setScaleX(1.1);
                    btn.setScaleY(1.1);
                    btn.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
                });

                // ⚡️ Efecto FadeTransition para que aparezcan suave
                FadeTransition fade = new FadeTransition(Duration.millis(400), btn);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.play();

                cartasGraficas.add(btn);
                getChildren().add(btn);
            }
        }

        getChildren().add(labelGanador);
    }


    public void resaltarErrores(){
        cartaSeleccionada.setStyle("-fx-border-color: #ba1a1a; -fx-border-width: 3;");
    }

    public void actualizar(ArrayList<Carta> cartasNuevas){
        this.cartas=cartasNuevas;
        crearCartasGraficas(cartas);
    }

    public Carta getCartaJugador(){return cartaJugador;}
    public void setCartaJugadorNull(){this.cartaJugador=null;}

    public ArrayList<Carta> getCartaSeleccionada() {
        return cartas;//placeHolder
    }

    public void actualizarCartas(ArrayList<Carta> nuevasCartas) {
        this.cartas = nuevasCartas;
        crearCartasGraficas(nuevasCartas);
    }

}
