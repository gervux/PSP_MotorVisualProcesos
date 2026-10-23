package procesia.gestorprocesos;

import procesia.auxiliares.Comandos;
import procesia.auxiliares.Redirecciones;
import procesia.registroproceso.ConfiguracionRedirecciones;
import procesia.registroproceso.RegistroProceso;

import java.io.IOException;

/**
 * Lanza un proceso individual de Text Analyzer o de Prime Benchmark.
 */
public class LanzadorProcesos {

    /**
     * Crea el ProcessBuilder, aplica las redirecciones, inicia el proceso
     * y devuelve su registro para el monitor.
     *
     * @param id         identificador de la fila en el monitor
     * @param tarea      Constantes.TAREA_TEXT_ANALYZER o Constantes.TAREA_PRIME_BENCHMARK
     * @param argumentos argumentos del programa (ruta y palabra, solo palabra, o el numero N)
     * @param modo       Constantes.MODO_INDIVIDUAL, MODO_SECUENCIAL o MODO_PARALELO
     * @param config     redirecciones elegidas por el usuario
     * @return el registro del proceso, en estado "Ejecutando"
     * @throws IOException si el proceso no se puede iniciar
     */
    public static RegistroProceso iniciar(int id, String tarea, String[] argumentos, String modo,
                                          ConfiguracionRedirecciones config) throws IOException {
        String clase = Comandos.obtenerClase(tarea);
        ProcessBuilder pb = new ProcessBuilder(Comandos.crearComando(clase, argumentos));
        Redirecciones.aplicar(pb, config);

        RegistroProceso reg = new RegistroProceso();
        reg.setId(id);
        reg.setTarea(tarea);
        reg.setParametros(String.join(" ", argumentos));
        reg.setModo(modo);
        reg.setConfiguracion(config);

        reg.marcarInicio();
        Process p = pb.start();
        reg.setProceso(p);
        reg.setPid(p.pid());

        Redirecciones.enviarStdin(p, config);
        return reg;
    }
}
