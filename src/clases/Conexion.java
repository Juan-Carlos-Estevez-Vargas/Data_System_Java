package clases;

import java.sql.*;

public class Conexion {
    //Conexion con base de datos local

    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/data_system", "root", "");
            return cn;
        } catch (SQLException ex) {
            System.out.println("Error en la conexion local" + ex);
        }
        return (null);
    }
}
