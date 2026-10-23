package procesia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Configura las redirecciones de stdin, stdout y stderr de un proceso
 * segun lo que haya elegido el usuario.
 */
public class Redirecciones {

    /**
     * Aplica las redirecciones al ProcessBuilder. Se llama antes de start().
     *
     * @param pb     ProcessBuilder del proceso
     * @param config opciones elegidas por el usuario
     */
    public static void aplicar(ProcessBuilder pb, ConfiguracionRedirecciones config) {
        // stdout
        if (Constantes.SALIDA_FICHERO.equals(config.getDestinoStdout())) {
            pb.redirectOutput(ProcessBuilder.Redirect.to(new File(config.getRutaStdout())));
        } else if (Constantes.SALIDA_DISCARD.equals(config.getDestinoStdout())) {
            pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
        } else {
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
        }

        // stderr
        if (Constantes.SALIDA_FICHERO.equals(config.getDestinoStderr())) {
            pb.redirectError(ProcessBuilder.Redirect.to(new File(config.getRutaStderr())));
        } else if (Constantes.SALIDA_DISCARD.equals(config.getDestinoStderr())) {
            pb.redirectError(ProcessBuilder.Redirect.DISCARD);
        } else {
            pb.redirectError(ProcessBuilder.Redirect.PIPE);
        }

        // stdin desde fichero
        if (Constantes.ENTRADA_FICHERO.equals(config.getOrigenStdin())) {
            pb.redirectInput(ProcessBuilder.Redirect.from(new File(config.getRutaStdin())));
        }
    }

    /**
     * Envia el texto por stdin si el usuario eligio "Desde Procesia".
     * Se llama despues de start(). Siempre se cierra el flujo: asi el proceso
     * recibe EOF y no se queda esperando mas datos.
     *
     * @param p      proceso ya iniciado
     * @param config opciones elegidas por el usuario
     * @throws IOException si no se puede escribir en el proceso
     */
    public static void enviarStdin(Process p, ConfiguracionRedirecciones config) throws IOException {
        BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(p.getOutputStream(), StandardCharsets.UTF_8));
        if (Constantes.ENTRADA_PROCESIA.equals(config.getOrigenStdin()) && config.getTextoStdin() != null) {
            escritor.write(config.getTextoStdin());
        }
        escritor.flush();
        escritor.close();
    }
}
