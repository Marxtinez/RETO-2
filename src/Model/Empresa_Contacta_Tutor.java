package Model;

public class Empresa_Contacta_Tutor {
    private int id_tutor;
    private  String CIF;
    private String fecha;


    public Empresa_Contacta_Tutor(int id_tutor, String CIF, String fecha) {
        this.id_tutor = id_tutor;
        this.CIF = CIF;
        this.fecha = fecha;

    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "Empresa_Contacta_Tutor{" +
                "id_tutor=" + id_tutor +
                ", CIF='" + CIF + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
