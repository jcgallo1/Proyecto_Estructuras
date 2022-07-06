/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Album;
import static Clases.Album.guardarAlbumRegistro;
import Clases.Imagen;
import Clases.Usuario;
import TDAS.ArrayList;
import TDAS.CircularLinkedList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private Button botonEditar;
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
    
    private Usuario user;
    @FXML
    private MenuItem buscarAlbum;
    @FXML
    private MenuItem buscarGlobal;
    @FXML
    private TextField buscar;
    @FXML
    private Button buscarBoton;
    @FXML
    private Menu menuBusquedas;
    
    private String busqueda;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("IniciarSesionController.fxml"));
            Parent root = loader.load();
            ImportarFotoController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */
        String ruta = "src/main/resources/Albunes/";
        File D = new File(ruta);
        boolean D1 = D.mkdir();
        if (D1) {
            System.out.println("Directory is created successfully");
        } else {
            System.out.println("Error el album ya existe!");
        }
        System.out.println(D1);
        this.albumes = FXCollections.observableArrayList();
        cargarAlbumRegistro();
        cargarAlbum();
        albumAbierto.setText("Crea/abre un album");
        botonEditar.setVisible(false);
        BotonEliminar.setVisible(false);
        botonSiguiente.setVisible(false);
        botonAtras.setVisible(false);
        buscarBoton.setVisible(false);
        buscar.setVisible(false);
        
        buscarAlbum.setOnAction(e -> {
            
            if(listViewAlbum.getSelectionModel().getSelectedItem()!=null){
                imagenesPane.getChildren().clear();
                regresar.setVisible(true);
                buscarBoton.setVisible(true);
                buscar.setVisible(true);
                desactivarEntornoFoto(false);
                this.busqueda = "buscarAlbum";
            }else{
                albumAbierto.setText("Escoga un album");
            }
            
            
            
        });
        buscarGlobal.setOnAction(a -> {
            imagenesPane.getChildren().clear();
            regresar.setVisible(true);
            buscarBoton.setVisible(true);
            buscar.setVisible(true);
            desactivarEntornoFoto(false);
            this.busqueda="buscarGlobal";
            albumAbierto.setText("Busqueda Global");
            
        });
        
    }   
    // SECCION DE ALBUM
    
    
    
    // funcion cargarAlbum() elimina los items a de mi lisvView y vuelve agregarlos 
    //por si existe algun cambio eliminar/editar album
    public void cargarAlbum(){
        listViewAlbum.getItems().clear();
        for(Album album : albumes){
                listViewAlbum.getItems().add(album.getNombre());
            }
        
        listViewAlbum.setOnMouseClicked(e -> {
            
            abriAlbum();
        
        });
        
    }
    
    // crea una escena de tipo añadirAlbumDialog para extrar los datos y crear un nuevo album
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
            System.out.println(nuevoA.getNombre());
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
    //CARGA EL TILE PANE Y LO LLENA CON LAS FOTOS DEL ALBUM SELECCIONADO
   
    public void abriAlbum(){
        String nombreAlbum=listViewAlbum.getSelectionModel().getSelectedItem();
        albumAbierto.setText(nombreAlbum);
        botonEditar.setVisible(false);
        BotonEliminar.setVisible(false);
        botonSiguiente.setVisible(false);
        botonAtras.setVisible(false);
        buscarBoton.setVisible(false);
        buscar.setVisible(false);
        
        cargarFotosPane(nombreAlbum);
    }
    
    // crea una vista añadirAlbumDialog y guarda los cambios en el album seleccionado
    //borra los datos del alnterior album y los vuelve a crear con sus nuevos datos
    @FXML
    public void editarAlbum(){
         try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("AñadirAlbumDialog.fxml"));
            Parent root= loader.load();
            AñadirAlbumDialogController controlador= loader.getController();
            controlador.initAtribut(albumes);
            controlador.getTxtNuevoAlbum();
            controlador.getDescripcionA();
            controlador.getTxtAlbum().setText("Editar album");
            for(Album album: albumes){
                if(listViewAlbum.getSelectionModel().getSelectedItem().equals(album.getNombre())){
                    controlador.getTxtNuevoAlbum().setText(album.getNombre());
                    controlador.getDescripcionA().setText(album.getDescripcion());
                    
                }
            }
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            Album nuevoA= controlador.getNuevoA();
            for(Album album: albumes){
                if(listViewAlbum.getSelectionModel().getSelectedItem().equals(album.getNombre())){
                    System.out.println(album.getNombre());
                    File directorio = new File("src/main/resources/Albunes/" + album.getNombre());
                    File albumSer = new File("src/main/resources/Albunes/" + album.getNombre() + ".ser");
                    if (directorio.delete()) {
                        System.out.println("Eliminado directorio");
                    } else {
                        System.out.println("Error");
                    }
                    if (albumSer.delete()) {
                        System.out.println("Eliminado album");
                    } else {
                        System.out.println("Error");
                    }
                    
                    album.setDescripcion(nuevoA.getDescripcion());
                    album.setNombre(nuevoA.getNombre());
                    guardarAlbumRegistro(album);
                    cargarAlbum();
                   
                }
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @FXML
    public void eliminarAlbum(ActionEvent event){
        String nombreAlbum=listViewAlbum.getSelectionModel().getSelectedItem();
        albumAbierto.setText("Album borrado: "+ nombreAlbum+" ");
        imagenesPane.getChildren().clear();
        for(Album al : albumes){         
            if(al.getNombre().equals(nombreAlbum)){
                
                File directorio = new File("src/main/resources/Albunes/" + al.getNombre());
                File albumSer = new File("src/main/resources/Albunes/" + al.getNombre() + ".ser");
                 if (directorio.delete()) {
                        System.out.println("Eliminado directorio");
                    } else {
                        System.out.println("Error");
                    }
                    if (albumSer.delete()) {
                        System.out.println("Eliminado album");
                    } else {
                        System.out.println("Error");
                    }
                
                        
                
            }
        }
        
        cargarAlbumRegistro();
        cargarAlbum();
        
    }
    
    
    public void cargarAlbumRegistro(){
        albumes.clear();
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
    
    //el programa se pone en modo busqueda
    @FXML
    public void busquedaAlbum(){
        Album alb=new Album("tmp","null",false);
        Album albumselec=null;
        String[] str=buscar.getText().toLowerCase().split(",");
        
        if(busqueda.equals("buscarAlbum")){
           
            for(Album album: albumes){
                if(album.getNombre().equals(albumAbierto.getText())){
                    albumselec=album;
                    for(Imagen imag: album.getFotos()){
                        ArrayList<String> informacion=new ArrayList<>();
                        String[] fotoDes=imag.getDescripcion().toLowerCase().split(" ");
                        String[] personas=imag.getPersonas().toLowerCase().split(",");
                        String[] lugar=imag.getLugar().toLowerCase().split(" ");
                        informacion.addLast(imag.getFecha().toString());
                        informacion.agregarElemn(fotoDes);
                        informacion.agregarElemn(personas);
                        informacion.agregarElemn(lugar);
                        for(String sts : str){
                            if(informacion.contains(sts)){
                                Imagen imtmp=imag;
                                alb.agregarFotos(imtmp);
                            }
                        }
                       
                    }
                }
            }
            
            albumes.add(alb);
            cargarFotosPane(alb.getNombre());
            albumes.remove(alb);
            
            
        }
        if(busqueda.equals("buscarGlobal")){
            System.out.println("Hola");
            for(Album album: albumes){
                for (Imagen imag : album.getFotos()) {
                    ArrayList<String> informacion = new ArrayList<>();
                    String[] fotoDes = imag.getDescripcion().toLowerCase().split(" ");
                    String[] personas = imag.getPersonas().toLowerCase().split(",");
                    String[] lugar = imag.getLugar().toLowerCase().split(" ");
                    informacion.addLast(imag.getFecha().toString());
                    informacion.agregarElemn(fotoDes);
                    informacion.agregarElemn(personas);
                    informacion.agregarElemn(lugar);
                    for (String sts : str) {
                        if (informacion.contains(sts)) {
                            Imagen imtmp = imag;
                            alb.agregarFotos(imtmp);
                        }
                    }

                }
            }

            albumes.add(alb);
            cargarFotosPane(alb.getNombre());
            albumes.remove(alb);
        }
        buscar.clear();
    }
    
    
    
    // SECCION DE FOTOS 
    
    
    
    // carga las fotos al tile pane del album
    //permite que los nodos se pueda navegar entre ellos usando circularlinkedlist
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
                            botonEditar.setVisible(true);
                            BotonEliminar.setVisible(true);
                            nodo.setOnMouseClicked(a -> {
                                imagenesPane.getChildren().clear();
                                botonSiguiente.setVisible(true);
                                botonAtras.setVisible(true);
                                regresar.setVisible(true);
                                botonEditar.setVisible(false);
                                BotonEliminar.setVisible(false);
                                desactivarEntornoFoto(false);
                                Image imageGrande = new Image(Imagen.getContent().getFoto().toURI().toString());
                                ImageView nodoImagenGrande = new ImageView(imageGrande);
                                nodoImagenGrande.setFitHeight(368);
                                nodoImagenGrande.setFitWidth(349);
                                imagenesPane.getChildren().add(nodoImagenGrande);
                            });
                            
                        });  
                        nodo.setFitHeight(155);
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
    
    //desactiva la visualizacion del entorno si es requerido
    public void desactivarEntornoFoto(Boolean valor){
        menubar.setVisible(valor);
        listViewAlbum.setVisible(valor);
        botonAbirA.setVisible(valor);
        botonEliminarA.setVisible(valor);
        
    }
    //vuelve a abrir el album seleccionado
    @FXML
    public void regresar(ActionEvent event){
        abriAlbum();
        regresar.setVisible(false);
        menubar.setVisible(true);
        desactivarEntornoFoto(true);
        buscarBoton.setVisible(false);
        buscar.setVisible(false);
       
        
       
    }
    //usando el circular node para mover la visualizacion de las fotos al siguiente nodo
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
    //usando el circular node para mover la visualizacion de las fotos al anterior nodo
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
    //elimina las fotos del album y su archivo serializado
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
    //edita las fotos si han sido modificadas y guarda los cambios o mueve la foto a otro album
    @FXML
    public void editar(){
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
            controlador.getFechaM().setValue(Imagen.getContent().getFecha());
            controlador.getAlbumes().setValue(Imagen.getContent().getNombreAlbum());
            controlador.getImportar().setText("Guardar");
            controlador.getPath().setEditable(false);
            Scene  scene = new Scene(root);
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            if(controlador.getAlbum().equals(Imagen.getContent().getNombreAlbum())){
                Imagen.getContent().setDescripcion(controlador.getTxtDescripcion());
                Imagen.getContent().setFecha(controlador.getFecha());
                Imagen.getContent().setLugar(controlador.getTxtLugar());
                Imagen.getContent().setPersonas(controlador.getTxtPersonas());
                System.out.println("No moviste la foto solo editaste");
                
            }else{
                Imagen.getContent().setDescripcion(controlador.getTxtDescripcion());
                Imagen.getContent().setFecha(controlador.getFecha());
                Imagen.getContent().setLugar(controlador.getTxtLugar());
                Imagen.getContent().setNombreAlbum(controlador.getAlbum());
                Imagen.getContent().setPersonas(controlador.getTxtPersonas());
                Imagen image=null;
                for (Album album : albumes) {
                    if(album.getFotos().contains(Imagen.getContent())){
                        System.out.println("Se borro la imagen del album");
                        File fotoser = new File("src/main/resources/Albunes/" + album.getNombre() + "/" + Imagen.getContent().getNombreFoto() + ".ser");
                        System.out.println(fotoser.delete());
                        image = album.getFotos().removerNodo(Imagen);
                        guardarAlbumRegistro(album);
                    }
                }
                 for (Album album : albumes) {
                    if (album.getNombre().equals(controlador.getAlbum())) {
                        if (!album.getFotos().contains(Imagen.getContent())) {
                            album.agregarFotos(image);
                            guardarAlbumRegistro(album);
                            abriAlbum();
                            
                        }

                    }

                }

                
                
            }
            
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
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
            
            if( (controlador.getFile()==null || controlador.getTxtDescripcion()==null || controlador.getTxtLugar()==null ||controlador.getFecha()==null ||controlador.getTxtPersonas()==null || controlador.getAlbum()==null) ){
                
            }else{
                Imagen imagen= new Imagen(controlador.getFile(),controlador.getTxtDescripcion(),controlador.getTxtLugar(),controlador.getFecha(),controlador.getTxtPersonas(),controlador.getAlbum());
                for(Album album : albumes){
                if(album.getNombre().equals(imagen.getNombreAlbum())){
                    album.agregarFotos(imagen);
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
    
   
    public ObservableList<Album> getAlbumes(){
        return this.albumes;
    }
    public String getImagenclic() {
        return this.imagenclic;
    }
    
}
        
    
