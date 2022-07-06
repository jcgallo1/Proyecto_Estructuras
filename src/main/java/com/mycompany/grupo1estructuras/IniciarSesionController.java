/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo1estructuras;

import Clases.Usuario;
import static Clases.Usuario.cargarUsuario;
import static Clases.Usuario.guardarUsuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private Text titulotx;
    @FXML
    private TextField campoUser;
    @FXML
    private TextField campoContra;
    @FXML
    private Button botonIngresar;
    @FXML
    private Button botonRegistrar;
    private Usuario user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user=null;
    }    
    
    
    public void crearUser(){
        titulotx.setText("REGISTRAR");
        botonRegistrar.setVisible(false);
        botonIngresar.setText("Crear");
    }
    
    public void iniciarSesion() throws IOException{
        Usuario user=new Usuario(campoUser.getText(),campoContra.getText());
        Boolean iniciar=cargarUsuario(user);
        if(iniciar){
            this.user=user;
             Stage stage = (Stage) this.botonIngresar.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("IniciarSesionController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,911,565);
            Stage stasge = new Stage();
            stasge.setScene(scene);
            stasge.showAndWait();
        }else{
            titulotx.setText("Usuario no encontrado");
        }
        if(botonIngresar.getText().equals("Crear")){
            guardarUsuario(user);
            botonIngresar.setText("ingresar");
            botonRegistrar.setVisible(true);
            titulotx.setText("iniciar sesion");
        }
    }

    public Usuario getUser() {
        return user;
    }
    
}
