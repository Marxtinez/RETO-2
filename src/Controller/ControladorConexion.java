package Controller;

import java.sql.Connection;
/*
* La conexión standard es:
* miConexion =DBConnection.getConnection("jdbc:postgresql://192.168.109.1:5432/DBRETO", "postgres", "Virtual01");
*
 */

public class ControladorConexion {
    public static String url = "jdbc:postgresql://192.168.109.1:5432/DBRETO";
    public static String user = "postgres";
    public static String password = "Virtual01";
    public static Connection miConexion = DBConnection.getConnection(url, user, password);
    public static void nuevaConexion(String urlNueva, String nameNuevo, String pswdNuevo){
        url=urlNueva;
        user=nameNuevo;
        password=pswdNuevo;
        miConexion = DBConnection.getConnection(urlNueva, nameNuevo, pswdNuevo);
    }
}
