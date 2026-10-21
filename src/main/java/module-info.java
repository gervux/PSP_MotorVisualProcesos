module org.example.proyectopsp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.proyectopsp to javafx.fxml;
    exports org.example.proyectopsp;
}