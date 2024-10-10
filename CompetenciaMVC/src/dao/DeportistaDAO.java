/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Deportista;


public class DeportistaDAO {
    private List<Deportista> deportistas;
    
    public DeportistaDAO() {
        this.deportistas = new ArrayList<>();
    }
    
    public void crear(Deportista deportista) {
        deportistas.add(deportista);
    }
    
    public void crearVarios(List<Deportista> deportistas_nuevos) {
        deportistas = deportistas_nuevos;
    }
    
    public Deportista obtener(int index){
        return deportistas.get(index);
    }
    
    public List<Deportista> obtenerTodos(){
        return deportistas;
    }
    
    public void eliminar(int index){
        deportistas.remove(index);
    }
}
