package Model;

public class TutorFCT {
    private int id_tutor;
    private String email;
    private String nombre;
    private String telefono;

    public TutorFCT(int id_tutor, String email, String nombre, String telefono) {
        this.id_tutor = id_tutor;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public TutorFCT() {

    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
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

    @Override
    public String toString() {
        return nombre;
    }
}
