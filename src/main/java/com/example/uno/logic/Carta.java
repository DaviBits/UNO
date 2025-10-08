package com.example.uno.logic;

public class Carta {
    private String color;
    private int numero;
    private String funcion;
    private boolean esEspecial;

    //tabla de funciones de las cartas
    // funcion de la carta -> valor del string  String [] especiales={" 🚫", " 🔄", " 2️⃣"};
    //      normal -> " "
    //      +2 -> "2️⃣"
    //      +4 -> "🌈4️"          TAMBIEN ES UN CAMBIO DE COLOR
    //      cc -> "🌈"
    //      Giro -> "🔄"
    //      Salto -> "🚫"


    public Carta (int numero, String color, String funcion, boolean esEspecial){
        this.numero=numero;
        this.color=color;
        this.funcion=funcion;
        this.esEspecial=esEspecial;

    }

    public void setColor(String color){this.color=color;}
    public void setNumero(int numero){this.numero=numero;}
    public void setFuncion(String funcion){this.funcion=funcion;}
    public void setEsEspecial(boolean esEspecial){this.esEspecial=esEspecial;}

    public String getColor(){return color;}
    public String getFuncion(){return funcion;}
    public int getNumero() {return numero;}
    public boolean esEspecial() {return esEspecial;}

   // public String toString(){return "["+color+"|"+ numero +"|"+ funcion+"]";}
   public String toString(){
       if(!esEspecial){
           return "["+ color+ numero+ funcion+ "]";
       }
       return "["+ color+ funcion+ "]";
   }
}
