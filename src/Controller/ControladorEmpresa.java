package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ControladorEmpresa {

    public static void cargaContenidoEmpresas() throws SQLException {
        Statement st = ControladorConexion.miConexion.createStatement();
        String scriptLeeEmpresas = "SELECT * FROM EMPRESA";
    }


}
