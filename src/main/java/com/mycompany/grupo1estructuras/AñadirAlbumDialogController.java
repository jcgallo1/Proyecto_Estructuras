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
import javafx.scene.text.Text;
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
    @FXML
    private Text campoIngresarnombre;
    @FXML
    private Text campoIngresarDescripcion;
    @FXML
    private Text txtAlbum;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoIngresarnombre.setVisible(false);
        campoIngresarDescripcion.setVisible(false);
        TxtNuevoAlbum.setText("");
        descripcionA.setText("");
        
        
    } 
    public void initAtribut(ObservableList<Album> albumes){
        this.albumes=albumes;
    }
    
    //BOTON CREAR ALBUM 
    
    @FXML
    public Album crearAlbum(){
        boolean valor=true;
        campoIngresarnombre.setVisible(false);
        campoIngresarDescripcion.setVisible(false);
        campoIngresarnombre.setText("campo vacio!");
        if (descripcionA.getText().equals("")) {
            campoIngresarDescripcion.setVisible(true);
        }
        if(TxtNuevoAlbum.getText().equals("")) {
            campoIngresarnombre.setVisible(true);
        }
        if(!(descripcionA.getText().equals("") || TxtNuevoAlbum.getText().equals(""))){
            Album nuevoA = new Album(TxtNuevoAlbum.getText(), descripcionA.getText(),true);
            for(Album album : albumes){
                if(album.getNombre().equals(nuevoA.getNombre())){
                   campoIngresarnombre.setVisible(true);
                   campoIngresarnombre.setText("Ya existe!!");
                   valor=false;
                   
                }
                
            }
            if(valor){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Album creado con exito");
                alert.showAndWait();
                Stage stage = (Stage) this.crearAlbum.getScene().getWindow();
                stage.close();
            }
            
            this.nuevoA = nuevoA;

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

    public Text getTxtAlbum() {
        return txtAlbum;
    }

    public TextField getTxtNuevoAlbum() {
        return TxtNuevoAlbum;
    }

    public TextField getDescripcionA() {
        return descripcionA;
    }
    
}
