package Model;

public class Tutor_Responsable_Grupo {
    private int id_tutor;
    private String id_grupo;
    private String curso;

    public Tutor_Responsable_Grupo(int id_tutor, String id_grupo, String curso) {
        this.id_tutor = id_tutor;
        this.id_grupo = id_grupo;
        this.curso = curso;
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Tutor_Responsable_Grupo{" +
                "id_tutor=" + id_tutor +
                ", id_grupo='" + id_grupo + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
