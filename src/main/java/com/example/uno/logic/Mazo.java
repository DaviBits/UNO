package com.example.uno.logic;

import java.util.ArrayList;

public class Mazo {
    ArrayList<Carta> mazo;

    public Mazo (){
        mazo = new ArrayList<>();
    }

    public void llenar(){
        String [] colores= {" 🔴", " 🟠", " 🟢", " 🔵"};
        String [] especiales={" 🚫", " 🔄", " 2️⃣"};
            //cartas del 0 al 9 de todos los colores
        for(int i=0; i<4; i++){
            for(int j=0;  j<9; j++){
                mazo.add(new Carta(j, colores[i], " ", false));
            }

            //Cartas de funciones especiales a excepcion del +4
            for(int k=0; k<3; k++){
                mazo.add(new Carta(10,colores[i], especiales[k], true ));
            }

            //cartas de cambio de color
            for(int d=0; d<4; d++){
                mazo.add(new Carta(10,"⚫",  "🌈", true));
            }

            mazo.add(new Carta(10, "⚫", "🌈4️", true));

        }
        mazo.forEach(System.out::println);
    }


    public static void main(String [] args){
        Mazo tester = new Mazo();
        tester.llenar();
    }
}
