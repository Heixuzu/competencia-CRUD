/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Deportista {
    private int ID;
    private String nombre;
    private String apellido;
    private String deporte;

    public Deportista(int ID, String nombre, String apellido, String deporte) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.deporte = deporte;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
    
    
}
