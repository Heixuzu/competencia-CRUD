/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controlador.DeportistasControlador;
import vista.VentanaRegistro;


public class OpcionalEventos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaRegistro ventana = new VentanaRegistro();
        DeportistasControlador controlador = new DeportistasControlador(ventana);
        ventana.setVisible(true);
    }
    
}
