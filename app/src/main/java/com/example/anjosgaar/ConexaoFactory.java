package com.example.anjosgaar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {
    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost:3306/anjoos_test";
        String user = "root";
        String password = "";
        Connection conn = null;
        try {
            //  Log.d("MeuApp", "Conexão com o banco de dados bem-sucedida");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection connection , Statement stmt) {
        close(connection);
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection connection , Statement stmt, ResultSet rs){
        close(connection,stmt);
        try{
            if(rs != null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
