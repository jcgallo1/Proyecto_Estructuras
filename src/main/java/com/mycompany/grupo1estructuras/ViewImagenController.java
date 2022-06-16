/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import Clases.Imagen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ViewImagenController implements Initializable {
    @FXML
    private Button atras;
    @FXML
    private Button siguiente;
    @FXML
    private ImageView imagenMostrar;
    
    private Album albumVisitado;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
            Parent root= loader.load();
            PrimaryController controlador= loader.getController();
            
            System.out.println(albumVisitado);
            
            /*
            for(Album album : controlador.getAlbumes()){
                if(album.getNombre().equals(fotocli[0])){
                    albumVisitado=album;
                }
            }
            try {
                                FXMLLoader loader = new FXMLLoader(App.class.getResource("viewImagen.fxml"));
                                Parent root;
                                root = loader.load();
                                ViewImagenController controlador = loader.getController();
                                
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(scene);
                                stage.showAndWait();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            
            System.out.println(albumVisitado.getFotos().toString());
            */
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }    
    
    public Button getAtras() {
        return atras;
    }

    public void setAtras(Button atras) {
        this.atras = atras;
    }

    public Button getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Button siguiente) {
        this.siguiente = siguiente;
    }

    public ImageView getImagenMostrar() {
        return imagenMostrar;
    }

    public void setImagenMostrar(ImageView imagenMostrar) {
        this.imagenMostrar = imagenMostrar;
    }

    public Album getAlbumVisitado() {
        return albumVisitado;
    }

    public void setAlbumVisitado(Album albumVisitado) {
        this.albumVisitado = albumVisitado;
    }
    
}
