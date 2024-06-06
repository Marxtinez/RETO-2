package Controller;

import java.sql.Connection;
import java.sql.SQLException;

public class ControladorEmpresa {
    private static Connection miConexion;

    public static void cargaContenidoEmpresas(){

            miConexion =DBConnection.getConnection("jdbc:postgres://192.168.109.1:6128/DBRETO", "postgres", "Virtual01");

    }

}
