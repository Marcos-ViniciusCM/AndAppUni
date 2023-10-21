package com.example.anjosgaar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CachorroDB {
    public static void save(Cachorro cachorro) {
        String sql = "INSERT INTO anjoos_test.cachorros (nome, sexo, descricao) VALUES ('" + cachorro.getNome() + "', '" + cachorro.getSexo() + "','" + cachorro.getDescricao() + "')";
        Connection conn = ConexaoFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            ConexaoFactory.close(conn, stmt);
            System.out.println("registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
