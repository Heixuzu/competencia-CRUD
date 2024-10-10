/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Deporte;

public class DeportesDAO {
    private List<Deporte> deportes;
    private Deporte natacion = new Deporte("Natación");
    private Deporte futbol = new Deporte("Fútbol");
    private Deporte tenis = new Deporte("Tenis");
    
    
    public DeportesDAO() {
        this.deportes = new ArrayList<>();
        crear(natacion);
        crear(futbol);
        crear(tenis);
    }
    
    public void crear(Deporte deporte){
        deportes.add(deporte);
    }
    
    public Deporte obtener(int index){
        return deportes.get(index);
    }
    
    public List<Deporte> obtenerTodos(){
        return deportes;
    }
}
