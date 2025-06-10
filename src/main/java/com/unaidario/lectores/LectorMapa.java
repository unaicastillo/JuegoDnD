package com.unaidario.lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.unaidario.Modelo.Mapa;

public class LectorMapa {
    public static ArrayList<Mapa> leerMapa() {
        ArrayList<Mapa> listaMapas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/unaidario/files/Mapa.unai"))) {
            String linea;
            String suelo = null;
            String pared = null;
            String nivel = null;
            String maldicion = null;
            int tamanoX = 0, tamanoY = 0;
            int[][] mapa = null;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("#")) {
                    if (linea.startsWith("#suelo:")) {
                        suelo = linea.split(":")[1];
                    } else if (linea.startsWith("#pared:")) {
                        pared = linea.split(":")[1];
                    } else if (linea.startsWith("#maldicion:")) {
                        maldicion = linea.split(":")[1];
                    } else if (linea.startsWith("#nivel:")) {
                        nivel = linea.split(":")[1];
                    } else if (linea.startsWith("#tamanoX:")) {
                        tamanoX = Integer.parseInt(linea.split(":")[1]);
                    } else if (linea.startsWith("#tamanoy:")) {
                        tamanoY = Integer.parseInt(linea.split(":")[1]);
                        mapa = new int[tamanoY][tamanoX];
                    }
                } else if (!linea.isEmpty() && mapa != null) {
                    for (int i = 0; i < tamanoY; i++) {
                        char[] fila = linea.toCharArray();
                        for (int j = 0; j < tamanoX; j++) {
                            mapa[i][j] = Character.getNumericValue(fila[j]);
                        }
                        if (i < tamanoY - 1) {
                            linea = br.readLine();
                        }
                    }
                    listaMapas.add(new Mapa(suelo, pared,maldicion, nivel, mapa));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaMapas;
    }
}
