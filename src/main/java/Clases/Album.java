/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.fotos;
import TDAS.CircularLinkedList;

/**
 *
 * @author User
 */
public class Album {
    String nombre;
    String direccion;
    CircularLinkedList<fotos> fotos;

    public Album(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public void importarFoto(){
        
    }
    public void importarTodasLasFotos(){
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public CircularLinkedList<fotos> getFotos() {
        return fotos;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFotos(CircularLinkedList<fotos> fotos) {
        this.fotos = fotos;
    }
    
    
    
    
    
}
