package com.example.uno.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class JuegoUNO {
    private ArrayList<Jugador> jugadores;
    private Mazo mazo;
    private Carta cartaTablero;
    private int turno;
    private int numJugadores;
    private String sentidoTurnos;

    public JuegoUNO(int cantidadJugadores){
        jugadores=new ArrayList<>();
        this.numJugadores=cantidadJugadores;
        this.sentidoTurnos="derecho";

        mazo=new Mazo();
        mazo.mezclar();
        mazo.mostrarMazo();
        cartaTablero=mazo.getUltimaCarta();
        mazo.eliminarUltimaCarta();

        //los jugadores ingresan sus nombres y se guardan en un AL
        llenarJugadores(cantidadJugadores);
        this.numJugadores=cantidadJugadores;
        //se reparten cartas
        repartirCartas(cantidadJugadores);
        //secuencia de juego
        this.turno=0;
        jugar();

    }

    public void repartirCartas(int cantidadJugadores){
        for(int i=0; i<cantidadJugadores; i++){
            for(int j=0; j<7; j++){
                jugadores.get(i).agregarCarta(mazo.getUltimaCarta());
                mazo.eliminarUltimaCarta();
            }
        }
    }



    public void llenarJugadores(int cantidad){
        if(cantidad<=1){
            System.out.println("Cantidad de jugadores invalida");
        }else{
            Scanner scan = new Scanner(System.in);
            for(int i=0; i<cantidad; i++){
                System.out.println("Ingrese el nombre del jugador "+(i+1));
                String nombre=scan.nextLine();
                jugadores.add(new Jugador(nombre, 0));
            }
        }


    }

    public boolean verificarGanador(){
        for (Jugador jugadore : jugadores) {
            if (jugadore.getNumeroCartas() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarSiSePuedePoner(Carta cartaJugador){
        boolean esValida=false;

        if(cartaJugador.getColor().equals(cartaTablero.getColor())){
            esValida=true;
        }
        if(cartaJugador.getNumero()==cartaTablero.getNumero()){
            esValida=true;
        }
        if(cartaJugador.getFuncion().equals(cartaTablero.getFuncion())){
            esValida=true;
        }

        return esValida;
    }

    public void hacerJugada(int turno){
        Jugador jugadorEnTurno = jugadores.get(turno);
        Scanner scan = new Scanner(System.in);
        String opcion;
        System.out.println("Carta en tablero: ");
        System.out.println(cartaTablero);
        System.out.println("Cartas de: "+ jugadorEnTurno.getNombre());
        jugadorEnTurno.mostrarCartas();
        System.out.println();
        System.out.println("Que carta quiere poner? (1-"+ jugadorEnTurno.getNumeroCartas()+ ")");
        System.out.println("si quieres tomar una carta teclea 100");

        System.out.println("si quieres pasar escribe 99");
        int indiceCarta=scan.nextInt()-1;
        if(indiceCarta+1==100){
            jugadorEnTurno.agregarCarta(mazo.getUltimaCarta());
            mazo.eliminarUltimaCarta();
            return;
        }else if(indiceCarta+1==99){
            return;
        }

        if(indiceCarta>jugadorEnTurno.getNumeroCartas()){
            System.out.println("No tienes tantas cartas ");
            hacerJugada(turno);
        }else{
            if(verificarSiSePuedePoner(jugadorEnTurno.getCartaEn(indiceCarta))||jugadorEnTurno.getCartaEn(indiceCarta).getColor().equals("⚫")){
                cartaTablero=jugadorEnTurno.getCartaEn(indiceCarta);
                jugadorEnTurno.eliminarCartasEn(indiceCarta);
            }else{
                System.out.println("esa no se puede poner ");
            }

        }

    }

    public void jugar(){
        while(!verificarGanador()){
            hacerJugada(turno);
            verificarSentido();

        }
    }

    public void verificarSentido(){




        if(sentidoTurnos.equals("derecho")){
            this.turno++;
        }else{
            System.out.println("turnos invertidos");
            this.turno--;
        }

        if(turno==numJugadores){
            this.turno=0;
        }
        if(turno<0){
            this.turno=numJugadores-1;
        }

    }

    public void aplicarEfectos(Carta cartaPuesta){
        String efecto= cartaPuesta.getFuncion();
        switch (efecto){
            case " 🚫":
                System.out.println("se salta un turno");
                if(sentidoTurnos.equals("derecho")){
                    this.turno++;
                }else{
                    System.out.println("turnos invertidos");
                    this.turno--;
                }
                break;
            case " 🔄":
                System.out.println("se invierte el sentido de turnos");
                if(sentidoTurnos.equals("derecho")){
                    this.sentidoTurnos="reves";
                }else if(sentidoTurnos.equals("reves")){
                    this.sentidoTurnos="derecho";
                }
                break;
            case " 2️⃣":
                System.out.println("se suman cartas al siguiente jugador");
                for(int i=0; i<2; i++){
                    jugadores.get(turno+1).agregarCarta(mazo.getUltimaCarta());
                    mazo.eliminarUltimaCarta();
                }
                break;
            case "🌈4️":
                Scanner scan = new Scanner(System.in);
                System.out.println("QUE COLOR SERA LA SIGUIENTE CARTA?");
                String color=scan.nextLine();
                cartaTablero.setColor(color);

                break;
            case "🌈":
                System.out.println("QUE COLOR SERA LA SIGUIENTE CARTA?");
                break;
        }
    }



    public static void main(String [] args){
        JuegoUNO tester = new JuegoUNO(4);
    }
}
