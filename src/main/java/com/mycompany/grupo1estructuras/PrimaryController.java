/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import static Clases.Album.guardarAlbumRegistro;
import Clases.Imagen;
import TDAS.CircularNode;
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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PrimaryController implements Initializable {

    private String imagenclic;
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
    private ListView<String> listViewAlbum;
    @FXML
    private MenuItem NuevoAlbum;
    @FXML
    private Button botonAbirA;
    @FXML
    private Button botonEliminarA;
    @FXML
    private Text albumAbierto;
    @FXML
    private TilePane imagenesPane;
    
    private ImageView imagenAbierta;
    
    private CircularNode<Imagen> Imagen;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Button botonAtras;
    @FXML
    private Button regresar;
    @FXML
    private MenuBar menubar;
    @FXML
    private Pane panedeFoto;
    @FXML
    private ScrollPane scrollImagen;
    @FXML
    private ComboBox<String> albumesBox;
    
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.albumes=FXCollections.observableArrayList();
        cargarAlbumRegistro();
        cargarAlbum();
        albumAbierto.setText("Abra un album");
        BotonMover.setVisible(false);
        BotonEliminar.setVisible(false);
        botonSiguiente.setVisible(false);
        botonAtras.setVisible(false);
        albumesBox.setVisible(false);
    }   
    
    public void cargarAlbum(){
        listViewAlbum.getItems().clear();
        for(Album album : albumes){
                listViewAlbum.getItems().add(album.getNombre());
            }
        
        
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
                    cargarAlbum();
                    
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    public void abriAlbum(){
        String nombreAlbum=listViewAlbum.getSelectionModel().getSelectedItem();
        albumAbierto.setText(nombreAlbum);
        BotonMover.setVisible(true);
        BotonEliminar.setVisible(true);
        botonSiguiente.setVisible(false);
        botonAtras.setVisible(false);
        
        cargarFotosPane(nombreAlbum);
    }
    public void cargarFotosPane(String nombreAlbum){
        imagenesPane.getChildren().clear();
        for (Album album : albumes) {
            if (album.getNombre().equals(nombreAlbum)) {
                if (!album.getFotos().isEmpty()) {
                    for (Imagen imag : album.getFotos()) {
                        Image img = new Image(imag.getFoto().toURI().toString());
                        ImageView nodo = new ImageView(img);
                        nodo.setId(imag.getNombreFoto());
                        nodo.setOnMouseClicked(e -> {
                            Imagen = album.getFotos().getNode(album.getFotos().indexOf(imag));
                            
                            nodo.setOnMouseClicked(a -> {
                                imagenesPane.getChildren().clear();
                                botonSiguiente.setVisible(true);
                                botonAtras.setVisible(true);
                                regresar.setVisible(true);
                                desactivarEntornoFoto(false);
                                Image imageGrande = new Image(Imagen.getContent().getFoto().toURI().toString());
                                ImageView nodoImagenGrande = new ImageView(imageGrande);
                                nodoImagenGrande.setFitHeight(368);
                                nodoImagenGrande.setFitWidth(349);
                                imagenesPane.getChildren().add(nodoImagenGrande);
                            });
                            
                        });  
                        nodo.setFitHeight(150);
                        nodo.setFitWidth(95);
                        imagenesPane.getChildren().add(nodo);
                        
                        
                    }
                }
            }
        }
        imagenesPane.setOrientation(Orientation.HORIZONTAL);
        imagenesPane.setHgap(4);
        imagenesPane.setVgap(4);
    }
    
    
    public void desactivarEntornoFoto(Boolean valor){
        menubar.setVisible(valor);
        listViewAlbum.setVisible(valor);
        botonAbirA.setVisible(valor);
        botonEliminarA.setVisible(valor);
        
    }
    @FXML
    public void regresar(ActionEvent event){
        abriAlbum();
        regresar.setVisible(false);
        menubar.setVisible(true);
        desactivarEntornoFoto(true);
       
        
       
    }
    @FXML
    public void siguienteFoto(ActionEvent event){
        imagenesPane.getChildren().clear();
        Imagen = Imagen.getNextNode();
        Image imageGrande = new Image(Imagen.getContent().getFoto().toURI().toString());
        ImageView nodoImagenGrande = new ImageView(imageGrande);

        nodoImagenGrande.setFitHeight(368);
        nodoImagenGrande.setFitWidth(349);
        
        imagenesPane.getChildren().add(nodoImagenGrande);
    }
    
    @FXML
    public void anteriorFoto(ActionEvent event){
        imagenesPane.getChildren().clear();
        Imagen=Imagen.getPrevNode();
        Image imageGrande = new Image(Imagen.getContent().getFoto().toURI().toString());
        ImageView nodoImagenGrande = new ImageView(imageGrande);
        nodoImagenGrande.setFitHeight(368);
        nodoImagenGrande.setFitWidth(349);
        imagenesPane.getChildren().add(nodoImagenGrande);
    }
    
    @FXML
    public void EliminarFoto(){
       
        String nombreAlbum = listViewAlbum.getSelectionModel().getSelectedItem();
        for (Album album : albumes) {
            if (album.getNombre().equals(nombreAlbum)) {
                album.getFotos().removerNodo(Imagen);
                File fotoser = new File("src/main/resources/Albunes/" + album.getNombre()+"/" +Imagen.getContent().getNombreFoto() + ".ser");
                System.out.println(fotoser.delete());
                guardarAlbumRegistro(album);
            }
        }
        abriAlbum();
    }
    @FXML
    public void moverFoto(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ImportarFoto.fxml"));
            Parent root= loader.load();
            ImportarFotoController controlador= loader.getController();
            controlador.getBotonBuscar().setVisible(false);
            controlador.getTxtLugarM().setText(Imagen.getContent().getLugar());
            controlador.getTxtPersonasM().setText(Imagen.getContent().getPersonas());
            controlador.getTxtDescripcionM().setText(Imagen.getContent().getDescripcion());
            controlador.getTxtInicio().setText("Modificar Foto");
            controlador.setPath(Imagen.getContent().getNombreFoto());
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            for(Album album : albumes){
                if(album.getNombre().equals(Imagen.getContent().getNombreAlbum())){
                    if(!album.getFotos().contains(Imagen.getContent())){
                        album.agregarFotos(Imagen.getContent());
                        guardarAlbumRegistro(album);
                        cargarFotosPane(album.getNombre());
                        albumAbierto.setText(album.getNombre());
                    }
                    
                    
                }
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    @FXML
    public void eliminarAlbum(ActionEvent event){
        String nombreAlbum=listViewAlbum.getSelectionModel().getSelectedItem();
        albumAbierto.setText(nombreAlbum);
        imagenesPane.getChildren().clear();
        for(Album albume : albumes){
            if(albume.getNombre().equals(nombreAlbum)){
                albumes.remove(albume);
                File directorio = new File("src/main/resources/Albunes/" + albume.getNombre());
                File albumSer = new File("src/main/resources/Albunes/" + albume.getNombre() + ".ser");
                if (directorio.delete() && albumSer.delete()) {
                    System.out.println("Eliminado");
                } else {
                    System.out.println("Error");
                }
                cargarAlbum();
                
            }
        }
        
        
    }
    
    
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
    public void importarFoto(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ImportarFoto.fxml"));
            Parent root= loader.load();
            ImportarFotoController controlador= loader.getController();
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            Imagen imagen= new Imagen(controlador.getFile(),controlador.getTxtDescripcion(),controlador.getTxtLugar(),controlador.getFecha(),controlador.getTxtPersonas(),controlador.getAlbum());
           
            for(Album album : albumes){
                if(album.getNombre().equals(imagen.getNombreAlbum())){
                    album.agregarFotos(imagen);
                    guardarAlbumRegistro(album);
                    cargarFotosPane(album.getNombre());
                    albumAbierto.setText(album.getNombre());
                    
                }
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
   
    public ObservableList<Album> getAlbumes(){
        return this.albumes;
    }
    public String getImagenclic() {
        return this.imagenclic;
    }
    
}
        
    
