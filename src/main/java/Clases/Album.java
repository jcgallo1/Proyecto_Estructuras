/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Imagen;
import TDAS.ArrayList;
import TDAS.CircularLinkedList;
import java.io.File;
import java.util.Date;

/**
 *
 * @author User
 */
public class Album {
    String nombre;
    String descripcion;
    CircularLinkedList<Imagen> fotos;
    
    
    //crea un album localmente en la carpeta picspol en el disco local c;
    
    public Album(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos=new CircularLinkedList<>();
        String ruta = "C:\\Picspol\\"+nombre;
        File D = new File(ruta);
        boolean D1 = D.mkdir();
        if (D1) {
            System.out.println("Directory is created successfully");
        } else {
            System.out.println("Error el album ya existe!");
        }
        System.out.println(D1);
        
    }
    
    public boolean agregarFotos(File foto,String descripcion, String lugar,Date fecha,ArrayList<Personas> persona,
            Album nombreAlbum) {

        if (foto == null) {
            return false;
        } else {
            this.fotos.addLast(new Imagen(foto, descripcion, lugar, fecha, persona, nombreAlbum));
        }
        return false;
        
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
