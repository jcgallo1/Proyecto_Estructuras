/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class Usuario implements Serializable{
    
    private String Nick;
    private String contraseña;

    public Usuario(String Nick, String contraseña) {
        this.Nick = Nick;
        this.contraseña = contraseña;
    }
    
    //guarda la informacion del usario
    public static void guardarUsuario(Usuario usuario){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("src/main/resources/Albunes/"+usuario.getNick()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(usuario);
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
    
    //encuentra el objeto y lo deserealiza si existe
    public static Boolean cargarUsuario(Usuario init){
        ObjectInputStream in=null;
        File folder=new File("src/main/resources/Albunes/");
        try {
            for (File file : folder.listFiles()) {
                if(!file.isDirectory()){
                    in = new ObjectInputStream(new FileInputStream(file));
                    Usuario user = (Usuario) in.readObject();
                    if(init.getNick().equals(user.Nick) && init.getContraseña().equals(user.contraseña)){
                        in.close();
                        return true;
                    }
                    
                    in.close();
                }
                
        }   
        } catch (FileNotFoundException ex) {
             System.err.println("No se encuentra archivo");
        } catch (IOException ex) {
           System.err.println("Error"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
           System.err.println("Error"+ex.getMessage());
        }
        return false;
        
    }
    public String getNick() {
        return Nick;
    }

    public void setNick(String Nick) {
        this.Nick = Nick;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
