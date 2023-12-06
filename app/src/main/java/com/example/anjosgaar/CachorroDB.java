package com.example.anjosgaar;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

      public static void update(int id,String novoSexo,String novoNome,String novaDescricao,InputStream imagemInputStream) throws SQLException {
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
            stmt.setBlob(4, imagemInputStream);
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

    public static List<Map<String, Object>> obterCao() {
        List<Map<String, Object>> cachorros = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexaoFactory.getConexao();
            String sql = "Select id, nome, sexo, descricao, foto FROM anjoos_test.cachorros";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Map<String, Object> cachorro = new HashMap<>();
                cachorro.put("id", rs.getString("id"));
                cachorro.put("nome", rs.getString("nome"));
                cachorro.put("sexo", rs.getString("sexo"));
                cachorro.put("descricao", rs.getString("descricao"));
                Blob imagem = rs.getBlob("foto");

                // Verifica se a imagem não é nula antes de adicionar ao Map
                if (imagem != null) {
                    InputStream imagemInputStream = imagem.getBinaryStream();
                    cachorro.put("imagem", imagemInputStream);

                }
                cachorros.add(cachorro);
                Log.d("CachorroDB", "Id: " + cachorro.get("id"));
                Log.d("CachorroDB", "Nome: " + cachorro.get("nome"));
                Log.d("CachorroDB", "Sexo: " + cachorro.get("sexo"));
                Log.d("CachorroDB", "Descricao: " + cachorro.get("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                if(stmt != null)
                    stmt.close();
                if(conn != null){
                    conn.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return cachorros;
    }



}





