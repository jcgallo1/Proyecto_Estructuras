/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AñadirAlbumDialogController implements Initializable {

    @FXML
    private TextField TxtNuevoAlbum;
    @FXML
    private Button crearAlbum;
    @FXML
    private Button cancelar;
    @FXML
    private TextField descripcionA;
    
    private Album nuevoA;
    
    private ObservableList<Album> albumes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void initAtribut(ObservableList<Album> albumes){
        this.albumes=albumes;
    }
    
    @FXML
    public Album crearAlbum(ActionEvent event){
        String nombreAlbum=TxtNuevoAlbum.getText();
        String descripcion= descripcionA.getText();
        
        Album nuevoA=new Album(nombreAlbum,descripcion);
        
        
        
        if(!this.albumes.contains(nuevoA)){
            this.nuevoA=nuevoA;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("se ha añadido correctamente");
            alert.showAndWait();
            Stage stage=(Stage) this.crearAlbum.getScene().getWindow();
            stage.close();
        }
        
        return nuevoA;
    }
    
    @FXML
    public void cerrar(ActionEvent event){
        Stage stage=(Stage) this.crearAlbum.getScene().getWindow();
        stage.close();
    }
    public Album getNuevoA() {
        
        return nuevoA;
    }
    
}
