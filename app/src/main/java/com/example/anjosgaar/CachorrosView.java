package com.example.anjosgaar;

import static com.example.anjosgaar.CachorroDB.*;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CachorrosView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);
        Button seuBotao = findViewById(R.id.bt_addCao);
        seuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar uma nova thread para realizar a consulta
                new Thread(() -> {
                    List<Cachorro> compradorList = selectALL();
                    // Qualquer manipulação dos dados obtidos deve ser feita na thread principal
                    runOnUiThread(() -> {
                        // Aqui você pode trabalhar com os dados obtidos, por exemplo:
                        // Exibir os dados em um RecyclerView, ListView, etc.
                        // Lembre-se de considerar as operações de interface do usuário apenas na thread principal
                    });
                }).start();
            }
        });

// Método para realizar a consulta SQL
        public List<Cachorro> selectALL() {
            String sql = "SELECT id, nome, descricao , sexo FROM anjoos_test.cachorro";
            Connection conn = ConexaoFactory.getConexao();
            List<Cachorro> compradorList = new ArrayList<>();

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    compradorList.add(new Cachorro(rs.getInt("id"), rs.getString("nome"), rs.getString("sexo")));
                }
                ConexaoFactory.close(conn, stmt, rs);
                return compradorList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public static void inserir(){
        Cachorro cachorro = new Cachorro("pipocando","femea","animada e descriiva");
        save(cachorro);
    }
}

/*
 public static void inserir() throws ExecutionException, InterruptedException {
     ConexaoBancoDados conexaoTask = new ConexaoBancoDados();
     Connection conn = conexaoTask.execute().get();
     if (conn != null) {
         Cachorro cachorro = new Cachorro("Pipoca", "femea", "animada");
         CachorroDB.save(cachorro, conn);
         Log.e("MeuApp", "Cachorro inserido.");
         ConexaoFactory.close(conn); // Feche a conexão quando terminar.
     }
     Log.e("MeuApp", "cachorro inserido: ");

 }

}

/*public class CachorrosView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);
        Button seuBotao = findViewById(R.id.bt_addCao);
        seuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chame a AsyncTask para inserir os dados no banco de dados
                new InserirCachorroTask().execute();
            }
        });
    }

    private class InserirCachorroTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Realize a operação de inserção de cachorro no banco de dados aqui
            Cachorro cachorro = new Cachorro("Pipoca", "femea", "animada");
            CachorroDB.save(cachorro);
            return null;
        }
    }
} */