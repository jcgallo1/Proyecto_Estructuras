module com.mycompany.grupo1estructuras {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.grupo1estructuras to javafx.fxml;
    exports com.mycompany.grupo1estructuras;
}
