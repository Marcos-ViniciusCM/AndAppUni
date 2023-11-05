package com.example.anjosgaar;

import static com.example.anjosgaar.CachorroDB.*;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.os.AsyncTask;

public class CachorrosView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);

        Button seuBotao = findViewById(R.id.bt_addCao);
        seuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Código a ser executado em segundo plano
                        // Exemplo: Salvando dados no banco de dados
                        CachorroDB.save("pipoca", "femea", "argumento3");

                        // Se precisar atualizar a interface após a execução em segundo plano,
                        // use runOnUiThread para realizar operações na thread principal.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Código para atualizar a interface após a execução em segundo plano
                            }
                        });
                    }
                }).start();
            }
        });

    }


    public static void inserir() {
        CachorroDB.save("pipoca", "femea", "animada");
    }

   }


