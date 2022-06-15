/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Imagen;
import TDAS.CircularLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
public class Album implements Serializable{
    String nombre;
    String descripcion;
    CircularLinkedList<Imagen> fotos;
    
    
    //crea un album localmente en la carpeta picspol en el disco local c;
    
    public Album(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos=new CircularLinkedList<>();
        String ruta = "src/main/resources/Albunes/"+nombre;
        File D = new File(ruta);
        boolean D1 = D.mkdir();
        if (D1) {
            System.out.println("Directory is created successfully");
        } else {
            System.out.println("Error el album ya existe!");
        }
        System.out.println(D1);
        
    }
    
    public boolean agregarFotos(Imagen imagen) {

        if (imagen == null) {
            return false;
        } else {
            this.fotos.addLast(imagen);
        }
        return false;
        
    }

    
    public static void guardarAlbumRegistro(Album album){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("src/main/resources/Albunes/"+album.getNombre()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(album);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CircularLinkedList<Imagen> getFotos() {
        return fotos;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFotos(CircularLinkedList<Imagen> fotos) {
        this.fotos = fotos;
    }
    
    
    
    
    
}
