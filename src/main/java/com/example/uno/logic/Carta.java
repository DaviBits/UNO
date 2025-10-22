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

    public void setColor(String color){
        color.toLowerCase();
        switch (color){
            case "rojo":
                this.color=" 🔴";
                break;
            case "azul":
                this.color=" 🔵";
                break;
            case "amarillo":
                this.color=" 🟠";
                break;
            case "verde":
                this.color=" 🟢";
                break;
        }
    }
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

    public String getNombreImagen() {
        String nombreColor = "";
        String nombreValor = "";

        // Quitar el emoji del color para tener solo el texto (opcionalmente puedes tener otro atributo sin emoji)
        switch (color.trim()) {
            case "🔴": nombreColor = "rojo"; break;
            case "🟠": nombreColor = "amarilla"; break;
            case "🟢": nombreColor = "verde"; break;
            case "🔵": nombreColor = "azul"; break;
            case "⚫": nombreColor = "negra"; break;
            default: nombreColor = "desconocido";
        }


        if (!esEspecial) {
            nombreValor = String.valueOf(numero);
        } else {
            switch (funcion.trim()) {
                case "🚫": nombreValor = "skip"; break;
                case "🔄": nombreValor = "reverse"; break;
                case "2️⃣": nombreValor = "+2"; break;
                case "🌈": nombreValor = "cambioDeColor";
                    nombreColor= "negra";break;
                case "🌈4️⃣": nombreValor = "+4";
                    nombreColor= "negra";break;
                default: nombreValor = "especial";
            }
        }
        //System.out.println(nombreValor);
        //System.out.println(nombreColor);

        return "carta_"+nombreColor+"_"+nombreValor+".PNG";
    }

}
