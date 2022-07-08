 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import Clases.Imagen;
import Clases.reacciones;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Grupo_1
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
    @FXML
    private Text tDescripcion;
    @FXML
    private Text tLugar;
    @FXML
    private Text tFecha;
    @FXML
    private Text tPersona;
    @FXML
    private Text tImport;
    @FXML
    private Text tFotof;
    @FXML
    private TabPane tabpane;
    @FXML
    private Tab tabImportar;
    @FXML
    private Tab tabAvanzado;
    
    private reacciones reaccion;
    @FXML
    private TextField comentario;
    @FXML
    private TextField hashtags;
    @FXML
    private Pane like;
    @FXML
    private Pane love;
    @FXML
    private Pane meImporta;
    @FXML
    private Pane hahah;
    @FXML
    private Pane sorprende;
    @FXML
    private Pane sad;
    @FXML
    private Pane angry;
    @FXML
    private TextField camara;
    
    private ObservableList<Album> albume;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtPersonas.setText("");
        txtDescripcion.setText("");
        path.setText("");
        txtLugar.setText("");
        comentario.setText("");
        hashtags.setText("");
        camara.setText("");
        fecha.setValue(LocalDate.now());
        tImport.setVisible(false);
        path.setEditable(false);
        tFotof.setVisible(false);
        reaccion=reaccion.like;
        
        
        angry.setOnMouseClicked(e->{
            reaccion=reaccion.angry;
        }
                
        );
        sad.setOnMouseClicked(e->{
            reaccion=reaccion.sad;
        }
        );;
        sorprende.setOnMouseClicked(e->{
            reaccion=reaccion.wow;
        }
        );;
        hahah.setOnMouseClicked(e->{
            reaccion=reaccion.haha;
        }
        );;
        meImporta.setOnMouseClicked(e->{
            reaccion=reaccion.appreciation;
        }
        );;
        love.setOnMouseClicked(e->{
            reaccion=reaccion.love;
        }
        );;
        like.setOnMouseClicked(e->{
            reaccion=reaccion.like;
        }
        );;
    }
    
    public void initAtribut(ObservableList<Album> albumes){
        this.albume=albumes;
        for(Album album : albume){
            this.albumes.getItems().add(album.getNombre());
        }
        
    }
    
    
    //hace que el usuario busque una foto en su dispositivo y la guarda en el path
    @FXML
    public void buscarFoto(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
        Window stage = null;
        this.file = fileChooser.showOpenDialog(stage);
        this.path.setText(file.getName());
    }
    
    //Importa la foto y revisa que no exista algun error
    @FXML
    public void importaFoto(){
        ChronoLocalDate dt= LocalDate.from(ZonedDateTime.now());
        tImport.setVisible(false);
        tFotof.setVisible(false);
        tDescripcion.setFill(Color.BLACK);
        tLugar.setFill(Color.BLACK);
        tImport.setFill(Color.BLACK);
        tFecha.setFill(Color.BLACK);
        if(txtDescripcion.getText().equals("")){
            tDescripcion.setFill(Color.RED);
        }
        if(txtLugar.getText().equals("")){
            tLugar.setFill(Color.RED);
        }
        if(path.getText().equals("")){
            tImport.setFill(Color.RED);
            tImport.setVisible(true);
        }
        if(fecha.getValue().isAfter(dt)){
            tFotof.setVisible(true);
            tFotof.setText("¿Foto del futuro? "+fecha.getValue().toString());
            tFotof.setFill(Color.RED);
            tFecha.setFill(Color.RED);
            
        }
        
        if(!(path.getText().equals("")||txtLugar.getText().equals("")||txtDescripcion.getText().equals("")||fecha.getValue().isAfter(dt))){
            Stage stage=(Stage) this.importar.getScene().getWindow();
            stage.close();
        }
        
    }

    public Button getBotonBuscar() {
        return botonBuscar;
    }

    public reacciones getReaccion() {
        return reaccion;
    }

    public void setReaccion(reacciones reaccion) {
        this.reaccion = reaccion;
    }

    public TextField getHashtags() {
        return hashtags;
    }

    public void setHashtags(TextField hashtags) {
        this.hashtags = hashtags;
    }

    public TextField getCamara() {
        return camara;
    }

    public void setCamara(TextField camara) {
        this.camara = camara;
    }

    public TextField getComentario() {
        return comentario;
    }

    public void setComentario(TextField comentario) {
        this.comentario = comentario;
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

    public TextField getPath() {
        return path;
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

    public ComboBox<String> getAlbumes() {
        return albumes;
    }
    
    public String getTxtLugar() {
        return txtLugar.getText();
    }

    public Button getImportar() {
        return importar;
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
