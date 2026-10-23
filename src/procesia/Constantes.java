package procesia;

/**
 * Textos fijos que usa Procesia: tareas, modos, estados y opciones de redireccion.
 * Si hay que cambiar un texto, solo se cambia aqui.
 */
public class Constantes {

    // Tareas que puede elegir el usuario
    public static final String TAREA_TEXT_ANALYZER = "Text Analyzer";
    public static final String TAREA_PRIME_BENCHMARK = "Prime Benchmark";
    public static final String TAREA_PIPELINE = "Pipeline: Prime Benchmark -> Text Analyzer";

    // Clases de los programas externos
    public static final String CLASE_TEXT_ANALYZER = "procesia.TextAnalyzer";
    public static final String CLASE_PRIME_BENCHMARK = "procesia.PrimeBenchmark";

    // Modos de ejecucion
    public static final String MODO_INDIVIDUAL = "INDIVIDUAL";
    public static final String MODO_SECUENCIAL = "SECUENCIAL";
    public static final String MODO_PARALELO = "PARALELO";

    // Estados de un proceso
    public static final String ESTADO_EJECUTANDO = "Ejecutando";
    public static final String ESTADO_FINALIZADO = "Finalizado";
    public static final String ESTADO_ERROR = "Error";
    public static final String ESTADO_CANCELADO = "Cancelado";

    // Opciones para stdout y stderr
    public static final String SALIDA_CAPTURAR = "Capturar en Procesia";
    public static final String SALIDA_FICHERO = "Fichero";
    public static final String SALIDA_DISCARD = "DISCARD";

    // Opciones para stdin
    public static final String ENTRADA_PROCESIA = "Desde Procesia";
    public static final String ENTRADA_FICHERO = "Desde fichero";
}
