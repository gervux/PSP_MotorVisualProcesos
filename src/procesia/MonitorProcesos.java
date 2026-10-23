package procesia;

import java.io.IOException;

/**
 * Comprueba si los procesos han terminado y actualiza su fila del monitor.
 */
public class MonitorProcesos {

    /**
     * Si el proceso ya ha terminado, guarda hora de fin, duracion, codigo
     * de salida, estado y salidas stdout/stderr. Si sigue vivo no hace nada.
     * Se puede llamar muchas veces (por ejemplo, cada cierto tiempo desde la interfaz).
     *
     * @param reg registro a comprobar
     * @return true si el registro se ha actualizado en esta llamada
     * @throws IOException si falla la lectura de las salidas
     * @throws InterruptedException si se interrumpe waitFor()
     */
    public static boolean comprobarYActualizar(RegistroProceso reg) throws IOException, InterruptedException {
        if (!reg.estaEjecutando()) {
            return false; // ya estaba finalizado, con error o cancelado
        }

        Process p = reg.getProceso();
        Process primero = reg.getProcesoInicialPipeline();
        if (p.isAlive()) {
            return false;
        }
        if (primero != null && primero.isAlive()) {
            return false;
        }

        // El proceso ya termino: waitFor() devuelve el codigo de inmediato
        int codigo = p.waitFor();
        reg.marcarFin(codigo);
        reg.setSalidaStdout(SalidasProceso.obtenerStdout(p, reg.getConfiguracion()));
        reg.setSalidaStderr(SalidasProceso.obtenerStderr(p, reg.getConfiguracion()));

        boolean hayError = codigo != 0;
        if (primero != null && comprobarPrimeroPipeline(reg, primero)) {
            hayError = true;
        }

        if (hayError) {
            reg.setEstado(Constantes.ESTADO_ERROR);
        } else {
            reg.setEstado(Constantes.ESTADO_FINALIZADO);
        }
        return true;
    }

    /**
     * Espera con waitFor() a que el proceso termine y despues actualiza su registro.
     * Sirve para el modo secuencial: el siguiente proceso no se lanza hasta que
     * este metodo termina.
     *
     * @param reg registro del proceso a esperar
     * @throws IOException si falla la lectura de las salidas
     * @throws InterruptedException si se interrumpe la espera
     */
    public static void esperar(RegistroProceso reg) throws IOException, InterruptedException {
        if (reg.getProcesoInicialPipeline() != null) {
            reg.getProcesoInicialPipeline().waitFor();
        }
        reg.getProceso().waitFor();
        comprobarYActualizar(reg);
    }

    /**
     * Comprueba el codigo de salida de Prime Benchmark dentro del pipeline.
     * Si no es 0, anade el aviso y su stderr al panel stderr.
     *
     * @return true si Prime Benchmark termino con error
     */
    private static boolean comprobarPrimeroPipeline(RegistroProceso reg, Process primero)
            throws IOException, InterruptedException {
        int codigoPrimero = primero.waitFor();
        String errorPrimero = SalidasProceso.leerFlujo(primero.getErrorStream());
        if (codigoPrimero == 0) {
            return false;
        }
        reg.setSalidaStderr(reg.getSalidaStderr()
                + "Prime Benchmark termino con codigo " + codigoPrimero + "\n" + errorPrimero);
        return true;
    }
}
