package com.unaidario.Modelo;

import java.util.ArrayList;

public class Movimientos {
    
    private ArrayList<int[]> direcciones;

    public Movimientos(){
        direcciones = new ArrayList<>();
        direcciones.add(new int[]{-1,0});   //Arriba    W   0
        direcciones.add(new int[]{0,-1});   //Izquierda A   1
        direcciones.add(new int[]{1,0});   //Abajo      S   2
        direcciones.add(new int[]{0,1});   //Derecha    D   3
    }


    public ArrayList<int[]> getDirecciones() {
        return this.direcciones;
    }


    public int[] sumPosiciones(int[] posicion1, int[] posicion2){
        return new int[] {posicion1[0]+posicion2[0], posicion1[1]+ posicion2[1]};
    }


    public int[] getDireccion(int x){//Devuelve la dirección correspondiente con su posición en el Array
        if (x < 0 || x >= direcciones.size()) {
            return direcciones.get(x);
        }
        else {
            return new int[] {0,0};
        }
    }


}
