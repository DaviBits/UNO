package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

  public Carta getCartaSeleccionadaUsuario(){return zonaCentral.getCartaJugador();}

  public void actualizarZonaSuperior(String nombreJugador, Carta cartaNueva){
    zonaSuperior.actualizar(nombreJugador, cartaNueva);
  }

  public void actualizarZonaCentral(ArrayList<Carta> cartasNuevas){
      zonaCentral.actualizar(cartasNuevas);

  }

  public void actualizarZonaInferior(){

  }

  public void resaltarErrores(){zonaCentral.resaltarErrores();}

  public void setCartaJugadorNull(){zonaCentral.setCartaJugadorNull();}

  public Button getAceptar(){return zonaInferior.getAceptar();}
    public Button getPasar(){return zonaInferior.getPasar();}
    public Button getTomar(){return zonaInferior.getTomar();}
    public void cambiarColorCartaTablero(String color){zonaSuperior.colorearMarcoDeLaCarta(color);}
    public void limpiarPantalla(){this.getChildren().clear();}
    public void mostrarGanador(String ganador){zonaCentral.mostrarGanador(ganador);}

}
