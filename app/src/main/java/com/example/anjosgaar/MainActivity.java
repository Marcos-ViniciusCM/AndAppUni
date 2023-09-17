package com.example.anjosgaar;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
   // criar o list view de add no banco public ListView listViewDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);]
        
        criarBancoDados();
        listarDados();
        
    }
     public void criarBancoDados(){
        try{
            bancoDados= openOrCreateDatabase("anjos",MODE_PRIVATE,null);//(nome do banco , private,  e null)
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS  coisas("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",nome VARCHAR)");
            bancoDados.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
     }
     public void listarDados(){

     }

}