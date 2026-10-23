package procesia;

import java.io.File;
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
     * @return el nombre completo de la clase
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
     * Crea la lista con el comando completo.
     * Se usa el java de la JVM actual (java.home) para no depender del PATH,
     * y el classpath actual (java.class.path), que es donde estan los .class.
     * Se fuerza UTF-8 en stdout/stderr del proceso hijo para que Procesia
     * lea bien el texto.
     *
     * @param clase      clase a ejecutar, por ejemplo "procesia.TextAnalyzer"
     * @param argumentos argumentos que recibe el programa
     * @return la lista para pasar a new ProcessBuilder(...)
     */
    public static List<String> crearComando(String clase, String[] argumentos) {
        String rutaJava = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        List<String> comando = new ArrayList<>();
        comando.add(rutaJava);
        comando.add("-Dstdout.encoding=UTF-8");
        comando.add("-Dstderr.encoding=UTF-8");
        comando.add("-cp");
        comando.add(classpath);
        comando.add(clase);
        for (String argumento : argumentos) {
            comando.add(argumento);
        }
        return comando;
    }
}
