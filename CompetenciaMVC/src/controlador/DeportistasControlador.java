/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DeportesDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.VentanaRegistro;

public class DeportistasControlador {
    
    private VentanaRegistro ventana;
    private static final String ruta = "src/txt";
    private DeportesDAO deportes = new DeportesDAO();
    private String deporte;
    private boolean verificarid;
    
    public DeportistasControlador(VentanaRegistro ventana){
        this.ventana = ventana;
        setDeportes();
        deporte = deportes.obtener(0).getNombre();
        this.ventana.getBtnGuardar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ventana.getNombres().equalsIgnoreCase("") ||ventana.gettxtID().equalsIgnoreCase("") || ventana.getApellidos().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(ventana, "Por favor ingrese los valores faltantes", "Error", JOptionPane.ERROR_MESSAGE); 
                } else {
                    VerificarID();
                    if (verificarid){
                    JOptionPane.showMessageDialog(ventana, "Esta identificación ya se encuentra registrada.", "Error", JOptionPane.ERROR_MESSAGE); 
                    } else {
                        guardarDatos();
                    try{
                        crearArchivo();
                    } catch (IOException ex){
                        Logger.getLogger(DeportistasControlador.class.getName()).log(Level.SEVERE, null, ex);  
                    }
                    } 
                }

            }
            
        });
        
        this.ventana.getBtnListar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listarDatos();
                } catch (IOException ex) {
                    Logger.getLogger(DeportistasControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        this.ventana.getBtnEditar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ventana.getNombres().equalsIgnoreCase("") || ventana.getApellidos().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(ventana, "Por favor ingrese los valores faltantes", "Error", JOptionPane.ERROR_MESSAGE); 
                } else {
                
                    editarDatos();
                    actualizarArchivo();
                }  
                }
     
            
            
        });
        
        this.ventana.getBtnEliminar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDatos();
                
            }
            
        });
        
        this.ventana.getComboBoxDeporte().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               deporte = (String) ventana.getComboBoxDeporte().getSelectedItem();
            }
        
        
        });
        
        
    }
    
    private void guardarDatos(){
        int ID = ventana.getID();
        String nombre = ventana.getNombres();
        String apellido = ventana.getApellidos();
        
        DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
        
        model.addRow(
                new Object[]{ID,nombre,apellido,deporte}
        );
        
        ventana.settxtID("");
        ventana.setNombres("");
        ventana.setApellidos("");
        
    }
    
    private void crearArchivo() throws IOException{
        String nombreArchivo = "/deportistas.txt";
        String rutaArchivo = ruta + nombreArchivo;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))){
            
            DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();
            
            for (int i = 0; i < columnCount; i++) {
                writer.write(model.getColumnName(i));
                if (i < columnCount - 1) {
                    writer.write("\t");
                }
            }
            writer.newLine();
            
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Object value = model.getValueAt(row, col);
                    writer.write(value.toString());
                    if (col < columnCount - 1) {
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }
            System.out.println("Archivo creado.");
        }
    }
    
    public void VerificarID() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta + "/deportistas.txt"))) {
            
            String id = String.valueOf(ventana.getID());  
            
            String line;
            while ((line = reader.readLine()) != null) {
                String [] lines = line.split("\t");
               if(line.contains(id)){
                  verificarid = true;  
                  break;
               } else {
                   verificarid = false; 
               }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeportistasControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeportistasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listarDatos() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta + "/deportistas.txt"))) {
            DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
            model.setRowCount(0); 

            
            String columnLine = reader.readLine();
            String[] columnNames = columnLine.split("\t");
            model.setColumnIdentifiers(columnNames);
            

          
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                model.addRow(data);
                
            }
        }
    }
    
    
    private void editarDatos() {
        int selectedRow = ventana.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(ventana, "Selecciona una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //int ID = ventana.getID();
        String nombre = ventana.getNombres();
        String apellido = ventana.getApellidos();
        
        DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
        
        //model.setValueAt(ID, selectedRow,0);
        model.setValueAt(nombre, selectedRow,1);
        model.setValueAt(apellido, selectedRow,2);
        model.setValueAt(deporte, selectedRow,3);
        

    }
    
    private void actualizarArchivo() {
        String rutaArchivo = ruta + "/deportistas.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();


            for (int i = 0; i < columnCount; i++) {
                writer.write(model.getColumnName(i));
                if (i < columnCount - 1) {
                    writer.write("\t");
                }
            }
            writer.newLine();


            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Object value = model.getValueAt(row, col);
                    writer.write(value.toString());
                    if (col < columnCount - 1) {
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }

            System.out.println("Archivo actualizado ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void eliminarDatos(){
        int selectedRow = ventana.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(ventana, "Selecciona una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ventana.getTable().getModel();
        model.removeRow(selectedRow);

        actualizarArchivo();
    }
    
        
    private void setDeportes(){
        int i;
        for(i = 0; i < deportes.obtenerTodos().size(); i++){
            ventana.addComboBoxDeporte(deportes.obtener(i).getNombre());           
        }
    }
}
