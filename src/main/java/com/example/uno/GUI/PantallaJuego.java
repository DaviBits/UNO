package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PantallaJuego extends VBox {
  private ZonaInferior zonaInferior;
  private ZonaCentral zonaCentral;
  private ZonaSuperior zonaSuperior;

  public PantallaJuego(ArrayList<Carta> cartasTurno, String nombreJugador, Carta cartaturno){
      this.zonaSuperior=new ZonaSuperior(cartaturno, nombreJugador);
      this.zonaCentral=new ZonaCentral(cartasTurno);
      this.zonaInferior=new ZonaInferior();
      this.setSpacing(20);
      this.setAlignment(Pos.CENTER);

      this.getChildren().addAll(zonaSuperior, zonaCentral, zonaInferior);

  }

}
