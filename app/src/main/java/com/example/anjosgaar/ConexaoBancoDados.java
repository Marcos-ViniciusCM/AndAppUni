package com.example.anjosgaar;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
public class ConexaoBancoDados extends AsyncTask<Void, Void, Connection> {

    @Override
    protected Connection doInBackground(Void... voids) {
        Connection conn = null;
        try {
            // Estabeleça a conexão com o banco de dados aqui
            String url = "jdbc:mysql://localhost:3306/anjoos_test";
            String user = "seuusuario";
            String password = "suasenha";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public Connection getConnection() {
        // Você pode chamar esse método para obter uma conexão.
        Connection conn = null;
        try {
            conn = execute().get(); // Execute a AsyncTask e obtenha a conexão.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
}

 */