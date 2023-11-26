package com.example.anjosgaar;

import static com.example.anjosgaar.ConexaoFactory.getConexao;

import android.util.Log;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class CachorroDB {
    public static void save(String nome, String sexo, String descricao, InputStream imagemInputStream ) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConexaoFactory.getConexao();
            String sql = "INSERT INTO anjoos_test.cachorros (nome, sexo, descricao,foto) VALUES (?, ?, ? , ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            stmt.setString(2,sexo);
            stmt.setString(3,descricao);
            stmt.setBlob(4, imagemInputStream);
            stmt.executeUpdate();
            Log.e("TAG", "adicionouuuuuu");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("TAG", "nao adicionouuuuuu");
        } finally {
            // Fechando recursos (statement e conexão)
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConexaoFactory.getConexao();
            String sql = "DELETE FROM cachorros WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            int colunasAfetadas = stmt.executeUpdate();
            if(colunasAfetadas > 0){
                Log.e("exclusao", "registro excluido");
            }else{
                Log.e("erro_exclusao", "nenhum registro excluido");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
      }

      public static void update(int id,String novoSexo,String novoNome,String novaDescricao) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConexaoFactory.getConexao();
            String sql = "UPDATE cachorros SET nome = ?, sexo = ?, descricao = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1,novoNome);
            stmt.setString(2,novoSexo);
            stmt.setString(3,novaDescricao);
            stmt.setInt(4,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(con != null){
                con.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
      }



}





