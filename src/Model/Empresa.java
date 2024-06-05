package Model;

public class Empresa {
    private String CIF;

    private String nombre;
    private String direccion;
    private String tecnologias;
    private String sector;
    private String telefono;
    private int num_empleados;
    private int ult_anio_colab;
    public Empresa(String CIF, String nombre, String direccion, String tecnologias, String sector, String telefono, int num_empleados, int ult_anio_colab) {
        this.CIF = CIF;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tecnologias = tecnologias;
        this.sector = sector;
        this.telefono = telefono;
        this.num_empleados = num_empleados;
        this.ult_anio_colab = ult_anio_colab;
    }


    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNum_empleados() {
        return num_empleados;
    }

    public void setNum_empleados(int num_empleados) {
        this.num_empleados = num_empleados;
    }

    public int getUlt_anio_colab() {
        return ult_anio_colab;
    }

    public void setUlt_anio_colab(int ult_anio_colab) {
        this.ult_anio_colab = ult_anio_colab;
    }
}
