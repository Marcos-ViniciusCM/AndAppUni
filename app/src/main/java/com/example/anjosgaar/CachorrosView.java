package com.example.anjosgaar;

import static com.example.anjosgaar.CachorroDB.*;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import android.os.AsyncTask;
import android.widget.ListView;

public class CachorrosView extends AppCompatActivity {

    public void segundaTela(View view){
        Intent in =  new Intent(CachorrosView.this,MainActivity.class );
        startActivity(in);
    }

    public void addCao(View view){
        Intent in =  new Intent(CachorrosView.this,addCao.class );
        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);

        Button remove = findViewById(R.id.bt_removeCao);
        Button update = findViewById(R.id.bt_updateCao);
        Button mostrar = findViewById(R.id.bt_showCao);


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CachorroDB.delete(1);
                        Log.e("TAG", "removeuuuu");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }).start();

            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchDataFromDatabaseTask(CachorrosView.this).execute();
            }
        });


    }





    protected List<Map<String, Object>> doInBackground(Void... voids) {
        List<Map<String, Object>> cachorros = new ArrayList<>();

        // Conectar ao servidor e obter dados
        // Exemplo fictício, você precisa substituir isso com sua lógica real
        try {
            // Conectar ao servidor, obter dados e preencher a lista de cachorros
            cachorros = CachorroDB.obterCao();
        } catch (Exception e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
        }

        return cachorros;
    }

    private class FetchDataFromDatabaseTask extends AsyncTask<Void, Void, List<Map<String, Object>>> {

        private WeakReference<Context> contextReference;

        FetchDataFromDatabaseTask(Context context) {
            contextReference = new WeakReference<>(context);
        }

        @Override
        protected List<Map<String, Object>> doInBackground(Void... voids) {
            // Conectar ao servidor e obter dados
            // Exemplo fictício, você precisa substituir isso com sua lógica real
            try {
                // Conectar ao servidor, obter dados e preencher a lista de cachorros
                Log.e("TAG", "era para retornar");
                return CachorroDB.obterCao();

            } catch (Exception e) {
                // Lidar com exceções, se necessário
                e.printStackTrace();
                Log.e("TAG", "era para retornar lista vazia");
                return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
            }
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> cachorros) {
            Context context = contextReference.get();
            if (context != null) {
                // Atualize a interface do usuário com os dados obtidos do banco
                ListView seuListView = findViewById(R.id.seuListView);
                seuListView.setAdapter(new CachorroAdapter(context, cachorros));
            }
        }
    }

}
