package com.example.uno.controllers;

import com.example.uno.GUI.InicializadorDeJugadores;
import com.example.uno.GUI.PantallaGanador;
import com.example.uno.GUI.PantallaJuego;
import com.example.uno.logic.Carta;
import javafx.scene.control.ChoiceDialog;
import com.example.uno.logic.JuegoUNO;
import com.example.uno.logic.Jugador;
import com.example.uno.logic.LogicaUno;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Controlador {
    private PantallaJuego pantallaJuego;
    private LogicaUno juegoUNO;
    private InicializadorDeJugadores inicializador;
    private int jugadorActual;
    private int totalJugadores;
    ArrayList<Jugador> jugadores=new ArrayList<>();
    private Scene escena;
    private int sentido;



    public Controlador(Stage stage, int numeroDeJugadores){
        this.totalJugadores=numeroDeJugadores;
        this.jugadorActual=1;
        this.sentido=1;
        this.jugadores=new ArrayList<>();
        this.inicializador=new InicializadorDeJugadores(jugadorActual);

        this.escena= new Scene(inicializador, 1000, 500);
        stage.setScene(escena);
        stage.setTitle("Uno, conteo de jugadores");
        stage.show();

        inicializador.getAceptar().setOnAction(e->{
            String nombre= inicializador.getNombreEscrito();
            if(nombre!=null && !nombre.isEmpty()){
                jugadores.add(new Jugador(nombre, 0));
                System.out.println("Jugador " + jugadorActual + ": " + nombre);
                inicializador.mostrarTextoBienvenida(nombre);
                jugadorActual++;
                if(jugadorActual<=totalJugadores){
                    inicializador.reset();
                    inicializador.setNumeroJugador(jugadorActual);
                }else{
                    System.out.println("todos listos");
                    System.out.println("total de jugadores registrados: "+ totalJugadores);
                    stage.setTitle("Juego UNO");
                    juegoUNO=new LogicaUno(totalJugadores);
                    jugar();
                }
            }else{
                System.out.println("ese no");
            }
        });

    }

    public void jugar(){
        jugadorActual=1;
        //considerar que en el constructor de logica uno ya esta inicializado el mazo y ya esta puesta la primera carta
        repartirCartas(); //si todos los jugadores ya tienen cartas, toca jugar
        verificarCartasEnConsola();
        pantallaJuego=new PantallaJuego(jugadores.get(jugadorActual-1).getCartas(), jugadores.get(jugadorActual-1).getNombre(), juegoUNO.getCartaTablero());
        escena.setRoot(pantallaJuego);

        //acciones de los botones
        pantallaJuego.getPasar().setOnAction(e->{
            ciclarTurnos();
            pantallaJuego.actualizarZonaCentral(jugadores.get(jugadorActual-1).getCartas());
            pantallaJuego.actualizarZonaSuperior(jugadores.get(jugadorActual-1).getNombre(), juegoUNO.getCartaTablero());

        });


        pantallaJuego.getTomar().setOnAction(e->{
            jugadores.get(jugadorActual-1).agregarCarta(juegoUNO.darCarta());
            juegoUNO.eliminarUltimaCartaDelMazo();
            pantallaJuego.actualizarZonaCentral(jugadores.get(jugadorActual-1).getCartas());

        });

        pantallaJuego.getAceptar().setOnAction(e->{
            if(pantallaJuego.getCartaSeleccionadaUsuario()!=null){
                boolean sePuedePoner=juegoUNO.verificarSiSePuede(pantallaJuego.getCartaSeleccionadaUsuario());
                System.out.println("Carta en tablero: "+ juegoUNO.getCartaTablero());
                System.out.println("Carta del jugador: "+pantallaJuego.getCartaSeleccionadaUsuario());

                System.out.println("se puede poner? "+sePuedePoner);

                if(sePuedePoner){
                    juegoUNO.setCartaTablero(pantallaJuego.getCartaSeleccionadaUsuario());
                    jugadores.get(jugadorActual-1).eliminarCarta(juegoUNO.getCartaTablero());
                    if(verificarSiGano()){
                        //pantallaJuego.limpiarPantalla();
                        escena.setRoot(new PantallaGanador(jugadores.get(jugadorActual - 1).getNombre()));
                    }
                    aplicarEfectosEspeciales(juegoUNO.getCartaTablero());
                    //ciclarTurnos();

                    pantallaJuego.actualizarZonaSuperior(jugadores.get(jugadorActual-1).getNombre(), juegoUNO.getCartaTablero());
                    pantallaJuego.actualizarZonaCentral(jugadores.get(jugadorActual-1).getCartas());
                    pantallaJuego.setCartaJugadorNull();
                }else{
                    pantallaJuego.resaltarErrores();
                }
            }
        });
    }

    public void ciclarTurnos() {
        if (sentido == 1) {
            jugadorActual++;
            if (jugadorActual > jugadores.size()) {
                jugadorActual = 1;
            }
        } else { // si quieres manejar reversa
            jugadorActual--;
            if (jugadorActual < 1) {
                jugadorActual = jugadores.size();
            }
        }
    }

    public boolean  verificarSiGano(){
        return jugadores.get(jugadorActual - 1).getCartas().isEmpty();
    }



    public void repartirCartas(){
        for (Jugador jugadore : jugadores) {
            jugadore.tomarCartas(juegoUNO.darCartas());
        }
    }

    public void aplicarEfectosEspeciales(Carta cartaPuesta){
        System.out.println("entre a verificarCarta");
        String colorUser;
     switch(cartaPuesta.getFuncion()){
         case " 🚫":
             System.out.println("saltar turno");
            ciclarTurnos();
            ciclarTurnos();
             break;
         case " 🔄":
             System.out.println("invertir turnos");
             this.sentido*=-1;
             ciclarTurnos();

             break;
         case " 2️⃣":
             ciclarTurnos();
             System.out.println("darle dos cartas");
             jugadores.get(jugadorActual-1).agregarCarta(juegoUNO.darCarta());
             juegoUNO.eliminarUltimaCartaDelMazo();
             jugadores.get(jugadorActual-1).agregarCarta(juegoUNO.darCarta());
             juegoUNO.eliminarUltimaCartaDelMazo();
             break;
         case "🌈":
             colorUser=pedirColor();
             juegoUNO.cambiarColorCartaTablero(colorUser);
             pantallaJuego.cambiarColorCartaTablero(colorUser);
             pantallaJuego.actualizarZonaSuperior(jugadores.get(jugadorActual-1).getNombre(), juegoUNO.getCartaTablero());

             break;
         case "🌈4️⃣":
             colorUser=pedirColor();
             pantallaJuego.cambiarColorCartaTablero(colorUser);
             juegoUNO.cambiarColorCartaTablero(colorUser);

             pantallaJuego.actualizarZonaSuperior(jugadores.get(jugadorActual-1).getNombre(), juegoUNO.getCartaTablero());
             ciclarTurnos();
             for(int i=0; i<2; i++){
                 jugadores.get(jugadorActual-1).agregarCarta(juegoUNO.darCarta());
                 juegoUNO.eliminarUltimaCartaDelMazo();

             }
             break;
         case " ":
             System.out.println("carta normal" );
             ciclarTurnos();
             break;

     }
    }



    public String pedirColor() {
        ArrayList<String> colores = new ArrayList<>();
        colores.add("rojo");
        colores.add("verde");
        colores.add("azul");
        colores.add("amarillo");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("rojo", colores);
        dialog.setTitle("Cambio de color");
        dialog.setHeaderText("Selecciona un nuevo color");
        dialog.setContentText("Color:");

        return dialog.showAndWait().orElse("rojo"); // si el jugador cierra sin elegir
    }


    public void verificarCartasEnConsola(){
        jugadores.forEach(Jugador::mostrarCartas);
    }


}
