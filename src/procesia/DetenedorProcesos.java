package procesia;

/**
 * Detiene procesos en ejecucion con destroy().
 */
public class DetenedorProcesos {

    /**
     * Llama a destroy() sobre el proceso (y sobre el primero si es un pipeline),
     * espera a que termine de verdad y marca la fila como "Cancelado".
     *
     * @param reg registro del proceso que se quiere detener
     * @throws InterruptedException si se interrumpe waitFor()
     */
    public static void detener(RegistroProceso reg) throws InterruptedException {
        if (!reg.estaEjecutando()) {
            return; // no se puede cancelar algo que ya ha terminado
        }

        Process p = reg.getProceso();
        Process primero = reg.getProcesoInicialPipeline();

        if (primero != null && primero.isAlive()) {
            primero.destroy();
        }
        if (p.isAlive()) {
            p.destroy();
        }

        // destroy() solo pide que el proceso termine; con waitFor() esperamos
        // a que termine de verdad para conocer su codigo de salida
        int codigo = p.waitFor();
        if (primero != null) {
            primero.waitFor();
        }

        reg.marcarFin(codigo);
        reg.setEstado(Constantes.ESTADO_CANCELADO);
        reg.setSalidaStdout(reg.getSalidaStdout() + "Proceso cancelado por el usuario (destroy())\n");
    }
}
