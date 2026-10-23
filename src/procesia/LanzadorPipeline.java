package procesia;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Lanza el pipeline Prime Benchmark -> Text Analyzer con startPipeline().
 * La salida (stdout) de Prime Benchmark se conecta con la entrada (stdin)
 * de Text Analyzer.
 */
public class LanzadorPipeline {

    /**
     * Crea los dos ProcessBuilder, los inicia con startPipeline() y
     * devuelve el registro del pipeline.
     *
     * @param id            identificador de la fila en el monitor
     * @param numeroN       numero N para Prime Benchmark
     * @param palabraBuscar palabra que buscara Text Analyzer
     * @return el registro del pipeline, en estado "Ejecutando"
     * @throws IOException si no se pueden iniciar los procesos
     */
    public static RegistroProceso iniciar(int id, String numeroN, String palabraBuscar) throws IOException {
        String[] argumentosPrime = {numeroN};
        String[] argumentosText = {palabraBuscar};
        ProcessBuilder pbPrime = new ProcessBuilder(
                Comandos.crearComando(Constantes.CLASE_PRIME_BENCHMARK, argumentosPrime));
        ProcessBuilder pbText = new ProcessBuilder(
                Comandos.crearComando(Constantes.CLASE_TEXT_ANALYZER, argumentosText));

        List<ProcessBuilder> builders = Arrays.asList(pbPrime, pbText);

        RegistroProceso reg = new RegistroProceso();
        reg.setId(id);
        reg.setTarea(Constantes.TAREA_PIPELINE);
        reg.setParametros("N=" + numeroN + ", palabra=" + palabraBuscar);
        reg.setModo(Constantes.MODO_INDIVIDUAL);
        // En el pipeline la salida del ultimo proceso siempre se captura en Procesia
        reg.setConfiguracion(new ConfiguracionRedirecciones());

        reg.marcarInicio();
        List<Process> lista = ProcessBuilder.startPipeline(builders);

        Process primero = lista.get(0);
        Process ultimo = lista.get(lista.size() - 1);
        reg.setProcesoInicialPipeline(primero);
        reg.setProceso(ultimo);
        reg.setPid(ultimo.pid());
        return reg;
    }
}
