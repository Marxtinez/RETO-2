package Model;

public class IncidenciaGrupo {
    private Incidencia incidencia;
    private Grupo grupo;

    public IncidenciaGrupo(Incidencia incidencia, Grupo grupo) {
        this.incidencia = incidencia;
        this.grupo = grupo;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "IncidenciaGrupo{" +
                "incidencia=" + incidencia +
                ", grupo=" + grupo +
                '}';
    }
}

