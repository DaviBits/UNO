package com.example.uno.logic;

import java.util.ArrayList;

public class Jugador {
    private final String nombre;
    private int contadorDeCartas;
    private ArrayList <Carta> mano;

    public Jugador(String nombre, int numeroDeCartas){
        this.contadorDeCartas=numeroDeCartas;
        this.nombre=nombre;
        mano=new ArrayList<>();
    }

    public void agregarCarta(Carta carta){
        mano.add(carta);
        contadorDeCartas++;
    }

    public void eliminarCarta(Carta carta){
        mano.remove(carta);
    }

    public void mostrarCartas(){
        for(Carta cartas: mano){
            System.out.print(cartas);
        }
        System.out.println();
    }

    public void sumarCartas(int numero){
        this.contadorDeCartas+=numero;
    }

    public void restarCartas(int numero){
        this.contadorDeCartas-=numero;
    }

    public void eliminarCartasEn(int idx){
        mano.remove(idx);
        contadorDeCartas--;
    }

    public Carta getCartaEn(int indice){return mano.get(indice);}

    public int getNumeroCartas(){
        return contadorDeCartas;
    }

    public String getNombre(){
        return nombre;
    }

    public ArrayList<Carta> getCartas(){return mano;}

    public void tomarCartas(ArrayList<Carta> cartas){
        this.mano=cartas;
    }
}
