package procesia;

/**
 * Representa una fila del Monitor de Procesos.
 * Guarda todos los datos de un proceso lanzado por Procesia:
 * identificador, PID, tarea, estado, tiempos, codigo de salida,
 * salidas stdout/stderr y la configuracion de redirecciones elegida.
 */
public class RegistroProceso {

    private int id;
    private long pid;
    private String tarea;
    private String parametros;
    private String modo;
    private String estado;

    private long tiempoInicioMs;
    private String horaInicio;
    private String horaFin;
    private long duracionMs;
    private Integer codigoSalida; // null mientras el proceso se esta ejecutando

    private String salidaStdout;
    private String salidaStderr;

    // Proceso en ejecucion (en un pipeline es el ultimo proceso: Text Analyzer)
    private Process proceso;
    // Solo se usa en el pipeline: el primer proceso (Prime Benchmark)
    private Process procesoInicialPipeline;

    // Redirecciones elegidas por el usuario (para saber que aviso mostrar)
    private ConfiguracionRedirecciones configuracion;

    /**
     * Crea un registro vacio en estado "Ejecutando".
     * Los datos se rellenan con los setters.
     */
    public RegistroProceso() {
        this.estado = Constantes.ESTADO_EJECUTANDO;
        this.salidaStdout = "";
        this.salidaStderr = "";
    }

    /**
     * Devuelve true si el proceso sigue en estado "Ejecutando".
     *
     * @return true si todavia no ha terminado ni se ha cancelado
     */
    public boolean estaEjecutando() {
        return Constantes.ESTADO_EJECUTANDO.equals(estado);
    }

    /**
     * Guarda la hora de inicio del proceso. Se llama justo antes de start().
     */
    public void marcarInicio() {
        tiempoInicioMs = System.currentTimeMillis();
        horaInicio = Tiempo.horaActual();
    }

    /**
     * Guarda el codigo de salida, la hora de fin y la duracion.
     * Se llama cuando ya se sabe que el proceso ha terminado.
     *
     * @param codigo codigo de salida del proceso
     */
    public void marcarFin(int codigo) {
        codigoSalida = codigo;
        horaFin = Tiempo.horaActual();
        duracionMs = System.currentTimeMillis() - tiempoInicioMs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getTiempoInicioMs() {
        return tiempoInicioMs;
    }

    public void setTiempoInicioMs(long tiempoInicioMs) {
        this.tiempoInicioMs = tiempoInicioMs;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public long getDuracionMs() {
        return duracionMs;
    }

    public void setDuracionMs(long duracionMs) {
        this.duracionMs = duracionMs;
    }

    public Integer getCodigoSalida() {
        return codigoSalida;
    }

    public void setCodigoSalida(Integer codigoSalida) {
        this.codigoSalida = codigoSalida;
    }

    public String getSalidaStdout() {
        return salidaStdout;
    }

    public void setSalidaStdout(String salidaStdout) {
        this.salidaStdout = salidaStdout;
    }

    public String getSalidaStderr() {
        return salidaStderr;
    }

    public void setSalidaStderr(String salidaStderr) {
        this.salidaStderr = salidaStderr;
    }

    public Process getProceso() {
        return proceso;
    }

    public void setProceso(Process proceso) {
        this.proceso = proceso;
    }

    public Process getProcesoInicialPipeline() {
        return procesoInicialPipeline;
    }

    public void setProcesoInicialPipeline(Process procesoInicialPipeline) {
        this.procesoInicialPipeline = procesoInicialPipeline;
    }

    public ConfiguracionRedirecciones getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionRedirecciones configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public String toString() {
        return "[" + id + "] PID=" + pid + " | " + tarea + " | " + parametros + " | " + modo
                + " | " + estado + " | inicio=" + horaInicio + " | fin=" + horaFin
                + " | " + duracionMs + " ms | codigo=" + codigoSalida;
    }
}
