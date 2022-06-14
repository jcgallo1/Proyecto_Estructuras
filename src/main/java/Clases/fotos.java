/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import TDAS.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class fotos {
    
    private String descripcion;
    private String lugar;
    private Date fecha;
    private ArrayList<Personas> persona;
    private Album nombreAlbum;

    public fotos(String descripcion, String lugar, 
                Date fecha, 
                ArrayList<Personas> persona, 
                Album nombreAlbum) {
        
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.persona = persona;
        this.nombreAlbum = nombreAlbum;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Personas> getPersona() {
        return persona;
    }

    public void setPersona(ArrayList<Personas> persona) {
        this.persona = persona;
    }

    public Album getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(Album nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }
     
}
