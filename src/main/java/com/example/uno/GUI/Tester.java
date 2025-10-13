package com.example.uno.GUI;

import com.example.uno.logic.Carta;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) {

        // --- Creamos cartas de ejemplo ---
        ArrayList<Carta> cartasTurno = new ArrayList<>();
        cartasTurno.add(new Carta(5, "🔴", " ", false));      // Rojo 5
        cartasTurno.add(new Carta(2, "🔵", " ", false));      // Azul 2
        cartasTurno.add(new Carta(0, "🟢", "🔄", true));      // Verde Giro
        cartasTurno.add(new Carta(0, "⚫", "🌈4️⃣", true));   // +4 Negro
        cartasTurno.add(new Carta(9, "🟠", " ", false));      // Amarilla 9

        // --- Carta que está en el tablero ---
        Carta cartaTablero = new Carta(7, "🔴", " ", false); // Rojo 7

        // --- Nombre de ejemplo ---
        String nombreJugador = "Jugador 1";

        // --- Creamos la pantalla de juego ---
        PantallaJuego pantallaJuego = new PantallaJuego(cartasTurno, nombreJugador, cartaTablero);

        // --- Escena y Stage ---
        Scene scene = new Scene(pantallaJuego, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tester PantallaJuego UNO");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
