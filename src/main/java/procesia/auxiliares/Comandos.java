package procesia.auxiliares;

import java.util.ArrayList;
import java.util.List;

/**
 * Construye el comando para lanzar un programa externo:
 * java -cp classpath procesia.Clase argumentos...
 */
public class Comandos {

    /**
     * Devuelve la clase que hay que ejecutar para una tarea.
     *
     * @param tarea Constantes.TAREA_TEXT_ANALYZER o Constantes.TAREA_PRIME_BENCHMARK
     * @return el nombre completo de la clase (paquete.Clase)
     */
    public static String obtenerClase(String tarea) {
        if (Constantes.TAREA_TEXT_ANALYZER.equals(tarea)) {
            return Constantes.CLASE_TEXT_ANALYZER;
        }
        if (Constantes.TAREA_PRIME_BENCHMARK.equals(tarea)) {
            return Constantes.CLASE_PRIME_BENCHMARK;
        }
        throw new IllegalArgumentException("Tarea no valida: " + tarea);
    }

    /**
     * Devuelve la ruta donde estan los .class del proyecto, para la opcion -cp.
     * Se pide a la propia aplicacion para no tener que escribir la ruta a mano.
     *
     * @return el classpath que se pasa a "java -cp"
     */
    public static String obtenerClasspath() {
        String classpath = System.getProperty("java.class.path");
        // Una aplicacion JavaFX con module-info.java se ejecuta con el "module path"
        // en vez del classpath, y en ese caso los .class estan ahi
        if (classpath == null || classpath.isEmpty()) {
            classpath = System.getProperty("jdk.module.path");
        }
        return classpath;
    }

    /**
     * Crea la lista con el comando completo, igual que en clase:
     * "java", "-cp", classpath, "paquete.Clase", argumentos...
     *
     * @param clase      clase a ejecutar, por ejemplo "procesia.TextAnalyzer"
     * @param argumentos argumentos que recibe el programa
     * @return la lista para pasar a new ProcessBuilder(...)
     */
    public static List<String> crearComando(String clase, String[] argumentos) {
        List<String> comando = new ArrayList<>();
        comando.add("java");
        comando.add("-cp");
        comando.add(obtenerClasspath());
        comando.add(clase);
        for (String argumento : argumentos) {
            comando.add(argumento);
        }
        return comando;
    }
}
