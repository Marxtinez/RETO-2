package Model;
//CORRESPONDE CON LA CONSULTA C1:
public class EmpresaTutorTrabajador {
   private Empresa empresa;
   private TutorFCT tutorFCT;
   private Trabajador trabajador;

    public EmpresaTutorTrabajador(Empresa empresa, TutorFCT tutorFCT, Trabajador trabajador) {
        this.empresa = empresa;
        this.tutorFCT = tutorFCT;
        this.trabajador = trabajador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TutorFCT getTutorFCT() {
        return tutorFCT;
    }

    public void setTutorFCT(TutorFCT tutorFCT) {
        this.tutorFCT = tutorFCT;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
