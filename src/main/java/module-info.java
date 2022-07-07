module com.mycompany.grupo1estructuras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens com.mycompany.grupo1estructuras to javafx.fxml;
    exports com.mycompany.grupo1estructuras;
}
