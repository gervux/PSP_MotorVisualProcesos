module procesia {
    requires javafx.controls;
    requires javafx.fxml;

    opens procesia to javafx.fxml;
    exports procesia;
}
