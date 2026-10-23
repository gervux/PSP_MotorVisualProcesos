package procesia.gestorprocesos;

import procesia.registroproceso.ConfiguracionRedirecciones;
import procesia.registroproceso.RegistroProceso;

import java.io.IOException;

/**
 * Punto de entrada para gestionar procesos desde la interfaz.
 * La interfaz solo necesita usar esta clase; cada metodo llama
 * a la clase pequena que hace el trabajo.
 *
 * No se usan hilos (Thread, Runnable, ExecutorService, Future...):
 * el paralelismo se consigue lanzando varios procesos independientes.
 */
public class GestorProcesos {

    /**
     * Lanza un proceso de Text Analyzer o de Prime Benchmark.
     * Ver {@link LanzadorProcesos#iniciar}.
     */
    public static RegistroProceso iniciarProcesoIndividual(int id, String tarea, String[] argumentos, String modo,
                                                           ConfiguracionRedirecciones config) throws IOException {
        return LanzadorProcesos.iniciar(id, tarea, argumentos, modo, config);
    }

    /**
     * Lanza el pipeline Prime Benchmark -> Text Analyzer.
     * Ver {@link LanzadorPipeline#iniciar}.
     */
    public static RegistroProceso iniciarPipeline(int id, String numeroN, String palabraBuscar) throws IOException {
        return LanzadorPipeline.iniciar(id, numeroN, palabraBuscar);
    }

    /**
     * Actualiza la fila si el proceso ya ha terminado.
     * Ver {@link MonitorProcesos#comprobarYActualizar}.
     */
    public static boolean comprobarYActualizarProceso(RegistroProceso reg) throws IOException, InterruptedException {
        return MonitorProcesos.comprobarYActualizar(reg);
    }

    /**
     * Espera a que el proceso termine (modo secuencial).
     * Ver {@link MonitorProcesos#esperar}.
     */
    public static void esperarProceso(RegistroProceso reg) throws IOException, InterruptedException {
        MonitorProcesos.esperar(reg);
    }

    /**
     * Detiene el proceso con destroy().
     * Ver {@link DetenedorProcesos#detener}.
     */
    public static void detenerProceso(RegistroProceso reg) throws InterruptedException {
        DetenedorProcesos.detener(reg);
    }
}
