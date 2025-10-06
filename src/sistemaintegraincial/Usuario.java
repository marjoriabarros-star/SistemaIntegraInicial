/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaintegraincial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Marjori Barros
 */
public class Usuario {
    private static final String URL = "jdbc:mysql://localhost:3306/integra_inicial";
    private static final String USER = "root";
    private static final String PASSWORD = "sua_senha";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void desconectar(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    
}
