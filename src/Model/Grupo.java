package Model;

public class Grupo {
    private String id_grupo;
    private int num_alumnos;
    private String id_ciclo;


    public Grupo(String id_grupo, int num_alumnos, String id_ciclo) {
        this.id_grupo = id_grupo;
        this.num_alumnos = num_alumnos;
        this.id_ciclo = id_ciclo;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getNum_alumnos() {
        return num_alumnos;
    }

    public void setNum_alumnos(int num_alumnos) {
        this.num_alumnos = num_alumnos;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    @Override
    public String toString() {
        return "Grupo{id_grupo='" + id_grupo + "', num_alumnos=" + num_alumnos + ", id_ciclo='" + id_ciclo + "'}";
    }
}
