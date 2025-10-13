package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ZonaCentral extends HBox {
    private ArrayList<Carta> cartas;
    private ArrayList <Button> cartasGraficas;
    private Button cartaSeleccionada = null;

    public ZonaCentral(ArrayList<Carta> cartas){
        this.cartas=cartas;
        cartasGraficas=new ArrayList<>();

        setSpacing(30);
        setAlignment(Pos.CENTER);

        crearCartasGraficas(cartas);

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
                btn.setStyle("-fx-background-color: transparent;"); // para que solo se vea la carta

                // Evento de selección
                btn.setOnAction(e -> {
                    if (cartaSeleccionada != null) {
                        // quitar borde a la carta previamente seleccionada
                        cartaSeleccionada.setStyle("-fx-background-color: transparent;");
                    }
                    // marcar la nueva carta seleccionada
                    cartaSeleccionada = btn;
                    cartaSeleccionada.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
                });

                cartasGraficas.add(btn);
                getChildren().add(btn);
            }
        }
    }

    public void actualizarCartas(ArrayList<Carta> nuevasCartas) {
        this.cartas = nuevasCartas;
        crearCartasGraficas(nuevasCartas);
    }

}
