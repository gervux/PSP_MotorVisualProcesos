package procesia.auxiliares;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para obtener la hora actual con el formato del monitor.
 */
public class Tiempo {

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * Devuelve la hora actual con formato HH:mm:ss.SSS.
     *
     * @return la hora actual como texto
     */
    public static String horaActual() {
        return LocalTime.now().format(FORMATO_HORA);
    }
}
