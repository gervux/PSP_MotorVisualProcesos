package procesia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Clase principal de Procesia con JavaFX.
 * De momento solo abre una ventana vacia para comprobar que JavaFX funciona;
 * la interfaz completa se hara mas adelante.
 */
public class ProcesiaApp extends Application {

    @Override
    public void start(Stage stage) {
        Label texto = new Label("Procesia - interfaz en construccion");
        StackPane raiz = new StackPane(texto);
        Scene escena = new Scene(raiz, 600, 400);

        stage.setTitle("Procesia");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
