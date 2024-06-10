package Model;

public class FCT {
  private String id_grupo;
  private String CIF;
  private String curso;
  private int num_alumnos;
    public FCT(String id_grupo, String CIF, String curso, int num_alumnos) {
        this.id_grupo = id_grupo;
        this.CIF = CIF;
        this.curso = curso;
        this.num_alumnos = num_alumnos;
    }
    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getNum_alumnos() {
        return num_alumnos;
    }

    public void setNum_alumnos(int num_alumnos) {
        this.num_alumnos = num_alumnos;
    }

    @Override
    public String toString() {
        return id_grupo;
    }
}
