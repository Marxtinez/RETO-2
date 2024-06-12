package Model;

public class Incidencia {
    private int id_incidencia;
    private String descripcion;
    private String fecha;
    private String CIF;

    public Incidencia(int id_incidencia, String descripcion, String fecha, String CIF) {
        this.id_incidencia = id_incidencia;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.CIF = CIF;
    }

    public int getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(int id_incidencia) {
        this.id_incidencia = id_incidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    @Override
    public String toString() {
        return "Incidencia{id_incidencia=" + id_incidencia + ", descripcion='" + descripcion + "', fecha='" + fecha + "', CIF='" + CIF + "'}";
    }
}
