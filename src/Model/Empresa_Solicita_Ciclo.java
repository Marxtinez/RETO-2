package Model;

public class Empresa_Solicita_Ciclo {
    private  String CIF;
    private String id_ciclo;
    private int num_alumnos;

    public Empresa_Solicita_Ciclo(String CIF, String id_ciclo, int num_alumnos) {
        this.CIF = CIF;
        this.id_ciclo = id_ciclo;
        this.num_alumnos = num_alumnos;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public int getNum_alumnos() {
        return num_alumnos;
    }

    public void setNum_alumnos(int num_alumnos) {
        this.num_alumnos = num_alumnos;
    }

    @Override
    public String toString() {
        return "Empresa_Solicita_Ciclo{" +
                "CIF='" + CIF + '\'' +
                ", id_ciclo='" + id_ciclo + '\'' +
                ", num_alumnos=" + num_alumnos +
                '}';
    }
}
