package Model;

//Consulta C7
public class EmpresaTecnologias {
    private Empresa empresa;
    private Empresa tecnologias;
    public EmpresaTecnologias(Model.Empresa empresa, Model.Empresa tecnologias) {
        this.empresa = empresa;
        this.tecnologias = tecnologias;
    }

    public Model.Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Model.Empresa getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(Empresa tecnologias) {
        this.tecnologias = tecnologias;
    }


}
