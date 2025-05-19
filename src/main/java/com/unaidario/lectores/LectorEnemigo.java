package com.unaidario.lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.unaidario.Modelo.Enemigo;

public class LectorEnemigo {
    public static ArrayList<Enemigo> leerEnemigos() {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        String archivoCsv = "src/main/resources/com/unaidario/files/ArchivoEnemigos.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCsv))) {
            String linea;
            br.readLine(); 

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                int tipo = Integer.parseInt(valores[0]);
                int salud = Integer.parseInt(valores[1]);
                int ataque = Integer.parseInt(valores[2]);
                int defensa = Integer.parseInt(valores[3]);
                int evasion = Integer.parseInt(valores[4]);
                int velocidad = Integer.parseInt(valores[5]);
                int posicionX = Integer.parseInt(valores[6]);
                int posicionY = Integer.parseInt(valores[7]);

                Enemigo enemigo = new Enemigo(tipo, salud, ataque, defensa, evasion, velocidad, posicionX, posicionY);
                enemigos.add(enemigo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enemigos;
    }
}
