/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import TDAS.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author User
 */
public class Imagen implements Serializable{
    private File foto;
    private String descripcion;
    private String lugar;
    private LocalDate fecha;
    private String personas;
    private String nombreAlbum;
    //agregue un nuevo atributo tipo file que son las Imagen jpg para asociarlas luego con la descripcion
    public Imagen(File foto,String descripcion, String lugar, 
                LocalDate fecha, 
                String persona, 
                String nombreAlbum) {
        this.foto= foto;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas =persona;
        this.nombreAlbum = nombreAlbum;
        
        guardarFotoRegistro(this);
        
        
    }
    
    
    
    
    
    public static void guardarFotoRegistro(Imagen imagen){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("src/main/resources/Albunes/"+imagen.getNombreAlbum()+"/"+imagen.getNombreFoto()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(imagen);
            out.flush();

        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra archivo");
        } catch (IOException ex) {
           System.err.println(ex.getMessage());
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                 System.err.println("Error al cerrar archivo");
            }
        }
    }

    public File getFoto() {
        return foto;
    }
    
    
    
    
    
    
    
    
    
    
    
    public String getNombreFoto(){
        return this.foto.getName();
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }
    

    public void añadirPersona(String persona){
        this.personas+=","+persona;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }
     
}