package com.example.anjosgaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.io.FileNotFoundException;
import java.io.InputStream;

public class addCao extends AppCompatActivity {
    public void segundaTela(View view){
        Intent in =  new Intent(addCao.this,CachorrosView.class );
        startActivity(in);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cao);
        Button adicionar = findViewById(R.id.bt_addDog);
        adicionar.setOnClickListener(new View.OnClickListener() {
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

            Uri selectedImageUri = data.getData();

            new SaveDataTask().execute(selectedImageUri);
        }
    }
    private class SaveDataTask extends AsyncTask<Uri, Void, Void> {
        @Override
        protected Void doInBackground(Uri... uris) {
            Uri selectedImageUri = uris[0];
            try {
                InputStream imageInputStream = getContentResolver().openInputStream(selectedImageUri);
                EditText nameInput = findViewById(R.id.nameInput);
                String name = nameInput.getText().toString();
                EditText sexInput = findViewById(R.id.sexInput);
                String sex = sexInput.getText().toString();
                EditText descInput = findViewById(R.id.descInput);
                String desc = descInput.getText().toString();
                if (imageInputStream != null) {

                    CachorroDB.save(name, sex, desc, imageInputStream);
                } else {

                    Log.e("TAG", "Falha ao abrir o InputStream da imagem");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}