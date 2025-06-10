package com.unaidario.Modelo;

import java.util.ArrayList;

public class Movimientos {
    
    private ArrayList<int[]> direcciones;

    public Movimientos(){
        direcciones = new ArrayList<>();
        direcciones.add(new int[]{0, -1});   // Arriba    W   0
        direcciones.add(new int[]{-1, 0});   // Izquierda A   1
        direcciones.add(new int[]{0, 1});    // Abajo     S   2
        direcciones.add(new int[]{1, 0});    // Derecha   D   3
    }

    /** 
     * @return ArrayList<int[]>
     */
    // mapa[fila][columna] = mapa[Y][X]

    public ArrayList<int[]> getDirecciones() {
        return this.direcciones;
    }


    /** 
     * @param posicion1
     * @param posicion2
     * @return int[]
     */
    public int[] sumPosiciones(int[] posicion1, int[] posicion2){
        return new int[] {posicion1[0]+posicion2[0], posicion1[1]+ posicion2[1]};
    }


    /** 
     * @param direcciones.size()
     * @return int[]
     */
    public int[] getDireccion(int tecla){//Devuelve la dirección correspondiente con su posición en el Array
        if (tecla >= 0 || tecla <= direcciones.size()) {
            return direcciones.get(tecla);
        }
        else {
            return new int[] {0,0};
        }
    }


}
