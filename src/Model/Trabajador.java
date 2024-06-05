package Model;

public class Trabajador {
    private int id_trabajador;

    private String email;
    private String nombre;
    private String telefono;
    private String cargo;
    private String persona_contacto;
    private String CIF;



    public Trabajador(int id_trabajador, String email, String nombre, String telefono, String cargo, String persona_contacto, String CIF){
        this.id_trabajador=id_trabajador;
        this.email=email;
        this.nombre=nombre;
        this.telefono=telefono;
        this.cargo=cargo;
        this.persona_contacto=persona_contacto;
        this.CIF=CIF;

    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }
    @Override
    public String toString() {
        return "Trabajador{" +
                "id_trabajador=" + id_trabajador +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cargo='" + cargo + '\'' +
                ", persona_contacto='" + persona_contacto + '\'' +
                ", CIF='" + CIF + '\'' +
                '}';
    }
}
