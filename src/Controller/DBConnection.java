package Controller;

import java.sql.*;

public class DBConnection {
    private static DBConnection instance;
    private static Connection connection;
    private DBConnection(String url, String user, String password){
        setConnection(url, user, password);
    }

    // Esto tiene que ser público y estático
    public static Connection getConnection(String url, String user, String password){
        if (instance == null){
            instance = new DBConnection(url, user, password);
        }else {
            try {
                if (connection == null || connection.isClosed()) {
                    setConnection(url, user, password);
                }
            } catch (java.sql.SQLException e) {
                System.out.println("Connecting error: isClosed() " + e.getMessage());
            }
        }
        return connection;
    }
    private static void setConnection(String url, String user, String password){
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión realizada");
        }catch(java.sql.SQLException e){
            System.out.println("Connecting error: getConnection() " + e.getMessage());
        }

    }
}
