package procesia.registroproceso;

import procesia.auxiliares.Constantes;

/**
 * Guarda lo que el usuario ha elegido para stdin, stdout y stderr
 * de un proceso, junto con las rutas de los ficheros cuando hacen falta.
 */
public class ConfiguracionRedirecciones {

    private String destinoStdout;
    private String rutaStdout;
    private String destinoStderr;
    private String rutaStderr;
    private String origenStdin;   // null si el proceso no usa stdin
    private String rutaStdin;
    private String textoStdin;    // texto que se envia si origenStdin es "Desde Procesia"

    /**
     * Crea una configuracion que captura stdout y stderr en Procesia
     * y no usa stdin. Se puede cambiar despues con los setters.
     */
    public ConfiguracionRedirecciones() {
        this.destinoStdout = Constantes.SALIDA_CAPTURAR;
        this.destinoStderr = Constantes.SALIDA_CAPTURAR;
    }

    public String getDestinoStdout() {
        return destinoStdout;
    }

    public void setDestinoStdout(String destinoStdout) {
        this.destinoStdout = destinoStdout;
    }

    public String getRutaStdout() {
        return rutaStdout;
    }

    public void setRutaStdout(String rutaStdout) {
        this.rutaStdout = rutaStdout;
    }

    public String getDestinoStderr() {
        return destinoStderr;
    }

    public void setDestinoStderr(String destinoStderr) {
        this.destinoStderr = destinoStderr;
    }

    public String getRutaStderr() {
        return rutaStderr;
    }

    public void setRutaStderr(String rutaStderr) {
        this.rutaStderr = rutaStderr;
    }

    public String getOrigenStdin() {
        return origenStdin;
    }

    public void setOrigenStdin(String origenStdin) {
        this.origenStdin = origenStdin;
    }

    public String getRutaStdin() {
        return rutaStdin;
    }

    public void setRutaStdin(String rutaStdin) {
        this.rutaStdin = rutaStdin;
    }

    public String getTextoStdin() {
        return textoStdin;
    }

    public void setTextoStdin(String textoStdin) {
        this.textoStdin = textoStdin;
    }
}
