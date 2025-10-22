package com.example.uno.logic;

import java.util.ArrayList;

public class LogicaUno {
    private ArrayList<Jugador> jugadores;
    private Mazo mazo;
    private Carta cartaTablero;
    private int turno;
    private int numJugadores;
    private String sentidoTurnos;

    public LogicaUno(int cantidadJugadores){
        this.jugadores=new ArrayList<>();
        this.numJugadores=cantidadJugadores;
        this.sentidoTurnos="derecho";
        this.turno=0;

        this.mazo=new Mazo();
        mazo.mezclar();
        ponerCartaEnEltablero();
    }

    public boolean verificarSiSePuede(Carta cartaJugador){
        if(!cartaJugador.esEspecial()){
            if(cartaJugador.getColor().equals(cartaTablero.getColor())){
                return true;
            }
            if(cartaJugador.getNumero()==cartaTablero.getNumero()){
                return true;
            }
        }else{
            if(cartaJugador.getColor().equals(cartaTablero.getColor())){
                return true;
            }
            if(cartaJugador.getColor().equals("⚫")){
                return true;
            }
            if(cartaJugador.getNumero()==cartaTablero.getNumero()){
                return true;
            }
            if(cartaJugador.getFuncion().equals(cartaTablero.getFuncion())){
                return true;
            }
        }


        return false;
    }


    public ArrayList<Carta> darCartas(){
        ArrayList<Carta> mano= new ArrayList<>();
        for(int i=0; i<7; i++){
            mano.add(mazo.getUltimaCarta());
            mazo.eliminarUltimaCarta();
        }
        return mano;
    }

    public Carta darCarta(){
        return mazo.getUltimaCarta();
    }

    public void rellenarMazo(){

    }

    public void eliminarUltimaCartaDelMazo(){mazo.eliminarUltimaCarta();}

    public void ponerCartaEnEltablero(){
        mazo.mezclar();
        Carta carta=mazo.getUltimaCarta();
        if(!carta.esEspecial()){
            this.cartaTablero=carta;
            mazo.eliminarUltimaCarta();
        }else{
            ponerCartaEnEltablero();
        }
    }
    public void cambiarColorCartaTablero(String color){
        cartaTablero.setColor(color);
    }


    public String getNombreJugadorEnTurno(){return jugadores.get(turno).getNombre();}

    public Carta getCartaTablero(){return cartaTablero;}

    public void setCartaTablero(Carta cartaNueva){this.cartaTablero=cartaNueva;}

    public ArrayList<Carta> getCartasEnTurno(){return jugadores.get(turno).getCartas();}

    public boolean hayGanador(){
        boolean  banderaGanador=false;
        for(Jugador jugador: jugadores){
            if(jugador.getNumeroCartas()==0){
                banderaGanador=true;
            }
        }
        return banderaGanador;
    }
}
