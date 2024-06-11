package Model;

public class EmpresaCursoFCT {
    private String id_grupo;
    private int numero_alumnos;
    private String id_ciclo;
    private int numero_practicas_acogidas;
    private  int cantidad_alumnos_realizado_FCT;

    public EmpresaCursoFCT(String id_grupo, int numero_alumnos, String id_ciclo, int numero_practicas_acogidas, int cantidad_alumnos_realizado_FCT) {
        this.id_grupo = id_grupo;
        this.numero_alumnos = numero_alumnos;
        this.id_ciclo = id_ciclo;
        this.numero_practicas_acogidas = numero_practicas_acogidas;
        this.cantidad_alumnos_realizado_FCT = cantidad_alumnos_realizado_FCT;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getNumero_alumnos() {
        return numero_alumnos;
    }

    public void setNumero_alumnos(int numero_alumnos) {
        this.numero_alumnos = numero_alumnos;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public int getNumero_practicas_acogidas() {
        return numero_practicas_acogidas;
    }

    public void setNumero_practicas_acogidas(int numero_practicas_acogidas) {
        this.numero_practicas_acogidas = numero_practicas_acogidas;
    }

    public int getCantidad_alumnos_realizado_FCT() {
        return cantidad_alumnos_realizado_FCT;
    }

    public void setCantidad_alumnos_realizado_FCT(int cantidad_alumnos_realizado_FCT) {
        this.cantidad_alumnos_realizado_FCT = cantidad_alumnos_realizado_FCT;
    }

    @Override
    public String toString() {
        return "EmpresaCursoFCT{" +
                "id_grupo='" + id_grupo + '\'' +
                ", numero_alumnos=" + numero_alumnos +
                ", id_ciclo='" + id_ciclo + '\'' +
                ", numero_practicas_acogidas=" + numero_practicas_acogidas +
                ", cantidad_alumnos_realizado_FCT=" + cantidad_alumnos_realizado_FCT +
                '}';
    }
}
