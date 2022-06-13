package com.mycompany.grupo1estructuras;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        /*
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window stage = null;
        File file = directoryChooser.showDialog(stage);
        String path = file.getPath();
        findAllFilesInFolder(file);
        */
        
        
        
    }
    //codigo para recorrer los archivos de alguna carpeta
    public static void findAllFilesInFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                System.out.println(file.getName());
            } else {
                findAllFilesInFolder(file);
            }
        }
    }
}
