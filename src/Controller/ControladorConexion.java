package Controller;

import java.sql.Connection;
/*
* La conexión standard es:
* miConexion =DBConnection.getConnection("jdbc:postgresql://192.168.109.1:5432/DBRETO", "postgres", "Virtual01");
*
 */

public class ControladorConexion {
    public static Connection miConexion = DBConnection.getConnection("jdbc:postgresql://192.168.109.1:5432/DBRETO", "postgres", "Virtual01");
    public static void nuevaConexion(String url, String name, String pswd){

            miConexion = DBConnection.getConnection(url, name, pswd);
    }
}
