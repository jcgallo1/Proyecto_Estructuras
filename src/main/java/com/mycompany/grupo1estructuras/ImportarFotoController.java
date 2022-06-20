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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private ComboBox<String> albumes;
    @FXML
    private Text txtInicio;
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
            for(Album album : controlador.getAlbumes()){
                albumes.getItems().add(album.getNombre());
            }
            
            
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

    public Button getBotonBuscar() {
        return botonBuscar;
    }
    
    
    
    public String getAlbum(){
        return albumes.getValue();
    }
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setPath(String path) {
        this.path.setText(path);
    }

    public void setBotonBuscar(Button botonBuscar) {
        this.botonBuscar = botonBuscar;
    }

    public void setTxtDescripcion(TextField txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public void setTxtLugar(TextField txtLugar) {
        this.txtLugar = txtLugar;
    }

    public void setFecha(DatePicker fecha) {
        this.fecha = fecha;
    }

    public void setTxtPersonas(TextField txtPersonas) {
        this.txtPersonas = txtPersonas;
    }

    public void setFoto(Imagen foto) {
        this.foto = foto;
    }

    public void setImportar(Button importar) {
        this.importar = importar;
    }

    public void setAlbumes(ComboBox<String> albumes) {
        this.albumes = albumes;
    }

    public String getTxtDescripcion() {
        return txtDescripcion.getText();
    }
    
    public String getTxtLugar() {
        return txtLugar.getText();
    }
   
    
    public LocalDate getFecha() {
        return fecha.getValue();
    }

    public Text getTxtInicio() {
        return txtInicio;
    }
    
    public String getTxtPersonas() {
        return txtPersonas.getText();
    }
    
    public Imagen getFoto() {
        return foto;
    }
    
    public TextField getTxtPersonasM() {
        return txtPersonas;
    }
    public DatePicker getFechaM() {
        return fecha;
    } 
    public TextField getTxtLugarM() {
        return txtLugar;
    }
    public TextField getTxtDescripcionM() {
        return txtDescripcion;
    }
    
}
