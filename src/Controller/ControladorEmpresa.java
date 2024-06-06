package Controller;

import java.sql.Connection;
import java.sql.SQLException;

public class ControladorEmpresa {
    private static Connection miConexion;

    public static void cargaContenidoEmpresas(){

            miConexion =DBConnection.getConnection("jdbc:postgresql://192.168.109.1:5432/DBRETO", "postgres", "Virtual01");

    }


}
