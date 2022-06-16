/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import static Clases.Album.guardarAlbumRegistro;
import Clases.Imagen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PrimaryController implements Initializable {

   
    @FXML
    private BorderPane panel;
    @FXML
    private MenuItem opcion_ImportarF;
    @FXML
     private Button BotonMover;
    @FXML
    private Button BotonEliminar;
    
    private ObservableList<Album> albumes;
    @FXML
    private ListView<Album> listViewAlbum;
    @FXML
    private MenuItem NuevoAlbum;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listViewAlbum=new ListView<>();
        this.albumes=FXCollections.observableArrayList();
        cargarAlbumRegistro();
        System.out.println(albumes.get(0).getFotos().get(0).getDescripcion());
        
    }  
    
    @FXML
    public void añadirAlbumes(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("AñadirAlbumDialog.fxml"));
            Parent root= loader.load();
            AñadirAlbumDialogController controlador= loader.getController();
            controlador.initAtribut(albumes);
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Album nuevoA= controlador.getNuevoA();
            if(nuevoA != null){
                if(!this.albumes.contains(nuevoA)){
                    guardarAlbumRegistro(nuevoA);
                    this.albumes.add(nuevoA);
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    
    @FXML
    public void cargarAlbumRegistro(){
        ObjectInputStream in=null;
        File folder=new File("src/main/resources/Albunes/");
        try {
            for (File file : folder.listFiles()) {
                if(!file.isDirectory()){
                    in = new ObjectInputStream(new FileInputStream(file));
                    Album albumss = (Album) in.readObject();
                    albumes.add(albumss);
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
        
    }
    @FXML
    public void importarFoto(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ImportarFoto.fxml"));
            Parent root= loader.load();
            ImportarFotoController controlador= loader.getController();
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            Imagen imagen= new Imagen(controlador.getFile(),controlador.getTxtDescripcion(),controlador.getTxtLugar(),controlador.getFecha(),controlador.getTxtPersonas(),"Juan");
           
            for(Album album : albumes){
                
                if(album.getNombre().equals(imagen.getNombreAlbum())){
                    System.out.println("hola");
                    album.agregarFotos(imagen);
                    guardarAlbumRegistro(album);
                }
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
   
    public ObservableList<Album> getAlbumes(){
        return this.albumes;
    }
}
        
    
/*
codigo para recorrer los archivos de alguna carpeta
    public static void findAllFilesInFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                System.out.println(file.getName());
            } else {
                findAllFilesInFolder(file);
            }
        }
       
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window stage = null;
        File file = directoryChooser.showDialog(stage);
        String path = file.getPath();
        findAllFilesInFolder(file);
        */