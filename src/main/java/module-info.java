module com.mycompany.grupo1estructuras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.grupo1estructuras to javafx.fxml;
    exports com.mycompany.grupo1estructuras;
}
