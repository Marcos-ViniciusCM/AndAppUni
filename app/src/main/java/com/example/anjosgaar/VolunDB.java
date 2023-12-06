package com.example.anjosgaar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
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

public class VolunDB{

    public static void delete(int id){
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConexaoFactory.getConexao();
            String sql = "DELETE FROM volun WHERE id = ?";
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


    public static List<Map<String, Object>> obterVolun() {
        List<Map<String, Object>> volun = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexaoFactory.getConexao();
            String sql = "Select name, telefone, endereco, id, about FROM anjoos_test.volun";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Map<String, Object> voluns = new HashMap<>();
                voluns.put("name", rs.getString("name"));
                voluns.put("telefone", rs.getString("telefone"));
                voluns.put("endereco", rs.getString("endereco"));
                voluns.put("id", rs.getString("id"));
                voluns.put("about", rs.getString("about"));

                volun.add(voluns);
                Log.d("CachorroDB", "Id: " + voluns.get("name"));
                Log.d("CachorroDB", "Nome: " + voluns.get("telefone"));
                Log.d("CachorroDB", "Sexo: " + voluns.get("endereco"));
                Log.d("CachorroDB", "Descricao: " + voluns.get("id"));
                Log.d("CachorroDB", "Descricao: " + voluns.get("about"));
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


        return volun;
    }

}