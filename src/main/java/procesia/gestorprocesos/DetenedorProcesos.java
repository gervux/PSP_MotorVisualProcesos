package procesia.gestorprocesos;

import procesia.auxiliares.Constantes;
import procesia.registroproceso.RegistroProceso;

import java.util.concurrent.TimeUnit;

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

        p.destroy();
        if (primero != null) {
            primero.destroy();
        }

        // Como en clase: si en 5 segundos no ha terminado, se cierra a la fuerza
        forzarSiNoTermina(p);
        if (primero != null) {
            forzarSiNoTermina(primero);
        }

        // Ya ha terminado: waitFor() devuelve su codigo de salida
        int codigo = p.waitFor();

        reg.marcarFin(codigo);
        reg.setEstado(Constantes.ESTADO_CANCELADO);
        reg.setSalidaStdout(reg.getSalidaStdout() + "Proceso cancelado por el usuario (destroy())\n");
    }

    /**
     * Espera como mucho 5 segundos a que el proceso termine.
     * Si no ha terminado, lo cierra a la fuerza con destroyForcibly().
     */
    private static void forzarSiNoTermina(Process p) throws InterruptedException {
        if (!p.waitFor(5, TimeUnit.SECONDS)) {
            p.destroyForcibly();
            p.waitFor();
        }
    }
}
