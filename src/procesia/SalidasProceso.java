package procesia;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Obtiene el texto que se muestra en los paneles stdout y stderr:
 * la salida real si se capturo en Procesia, o un aviso si se envio
 * a un fichero o se descarto.
 */
public class SalidasProceso {

    /**
     * Devuelve el texto para el panel stdout.
     *
     * @param p      proceso que ya ha terminado
     * @param config opciones elegidas por el usuario
     * @return la salida capturada o el aviso correspondiente
     * @throws IOException si falla la lectura
     */
    public static String obtenerStdout(Process p, ConfiguracionRedirecciones config) throws IOException {
        if (Constantes.SALIDA_FICHERO.equals(config.getDestinoStdout())) {
            return "stdout redirigido al fichero: " + new File(config.getRutaStdout()).getAbsolutePath();
        }
        if (Constantes.SALIDA_DISCARD.equals(config.getDestinoStdout())) {
            return "stdout -> DISCARD: salida descartada";
        }
        return leerFlujo(p.getInputStream());
    }

    /**
     * Devuelve el texto para el panel stderr.
     *
     * @param p      proceso que ya ha terminado
     * @param config opciones elegidas por el usuario
     * @return la salida de error capturada o el aviso correspondiente
     * @throws IOException si falla la lectura
     */
    public static String obtenerStderr(Process p, ConfiguracionRedirecciones config) throws IOException {
        if (Constantes.SALIDA_FICHERO.equals(config.getDestinoStderr())) {
            return "stderr redirigido al fichero: " + new File(config.getRutaStderr()).getAbsolutePath();
        }
        if (Constantes.SALIDA_DISCARD.equals(config.getDestinoStderr())) {
            return "stderr -> DISCARD: salida descartada";
        }
        return leerFlujo(p.getErrorStream());
    }

    /**
     * Lee todo el contenido de un flujo y lo devuelve como texto.
     *
     * @param flujo getInputStream() o getErrorStream() del proceso
     * @return el texto leido
     * @throws IOException si falla la lectura
     */
    public static String leerFlujo(InputStream flujo) throws IOException {
        StringBuilder texto = new StringBuilder();
        BufferedReader lector = new BufferedReader(new InputStreamReader(flujo, StandardCharsets.UTF_8));
        String linea;
        while ((linea = lector.readLine()) != null) {
            texto.append(linea).append("\n");
        }
        lector.close();
        return texto.toString();
    }
}
