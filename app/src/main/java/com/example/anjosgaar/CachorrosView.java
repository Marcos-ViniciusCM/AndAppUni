package com.example.anjosgaar;

import static com.example.anjosgaar.CachorroDB.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // A imagem foi selecionada com sucesso
            Uri selectedImageUri = data.getData();

            // Inicie uma AsyncTask ou uma Thread para operações em segundo plano
            new SaveDataTask().execute(selectedImageUri);
        }
    }


    private class SaveDataTask extends AsyncTask<Uri, Void, Void> {
        @Override
        protected Void doInBackground(Uri... uris) {
            Uri selectedImageUri = uris[0];


            try {
                InputStream imageInputStream = getContentResolver().openInputStream(selectedImageUri);

                if (imageInputStream != null) {
                    // Agora você pode chamar o método save com a imagem
                    CachorroDB.save("pipoca", "femea", "argumento3", imageInputStream);
                } else {
                    // Trate o caso em que o InputStream é nulo
                    Log.e("TAG", "Falha ao abrir o InputStream da imagem");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // Trate a exceção de arquivo não encontrado
            } catch (Exception e) {
                e.printStackTrace();
                // Trate outras exceções
            }

            return null;
        }
    }
}