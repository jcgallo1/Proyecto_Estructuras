/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import Clases.Imagen;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ImportarFotoController implements Initializable {

    private File file;
    @FXML
    private TextField path;
    @FXML
    private Button botonBuscar;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtLugar;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField txtPersonas;
    
    
    private Imagen foto;
    @FXML
    private Button importar;
    @FXML
    private ChoiceBox<Album> albumes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
            Parent root= loader.load();
            PrimaryController controlador= loader.getController();
            albumes = new ChoiceBox (controlador.getAlbumes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }    
    
    
    
    @FXML
    public void buscarFoto(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        this.file = fileChooser.showOpenDialog(stage);
        this.path.setText(file.getName());
    }
    
    @FXML
    public void importaFoto(){
        Stage stage=(Stage) this.importar.getScene().getWindow();
        stage.close();
    }
    public File getFile() {
        return file;
    }

    public TextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public TextField getTxtLugar() {
        return txtLugar;
    }

    public DatePicker getFecha() {
        return fecha;
    }

    public TextField getTxtPersonas() {
        return txtPersonas;
    }

    public Imagen getFoto() {
        return foto;
    }
    
}
