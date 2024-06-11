package Model;

public class EmpresaFCT {

    private Empresa empresa;
    private int num_practicas; //CANTIDAD DE GRUPOS QUE HAN HECHO PRÁCTICAS.
    private int num_total_alumnos_FCT; //CANTIDAD DE ALUMNOS QUE HAN HECHO PRÁCTCAS EN ESA EMPRESA.


    public EmpresaFCT(Empresa empresa, int num_practicas, int num_total_alumnos_FCT) {
        this.empresa = empresa;
        this.num_practicas  =num_practicas;
        this.num_total_alumnos_FCT = num_total_alumnos_FCT;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    public int getNum_practicas() {
        return num_practicas;
    }

    public void setNum_practicas(int num_practicas) {
        this.num_practicas = num_practicas;
    }

    public int getNum_total_alumnos_FCT() {
        return num_total_alumnos_FCT;
    }

    public void setNum_total_alumnos_FCT(int num_total_alumnos_FCT) {
        this.num_total_alumnos_FCT = num_total_alumnos_FCT;
    }

    @Override
    public String toString() {
        return "EmpresaFCT{" +
                "empresa=" + empresa +
                ", num_practicas=" + num_practicas +
                ", num_total_alumnos_FCT=" + num_total_alumnos_FCT +
                '}';
    }
}
