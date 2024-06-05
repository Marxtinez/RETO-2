package Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoBD {
private static Connection conex;

    public static Connection getConex(String url, String username, String password) throws SQLException {
        if (conex == null || conex.isClosed()) {
            conex = DriverManager.getConnection(url, username, password);
        }
        return conex;
    }
}

