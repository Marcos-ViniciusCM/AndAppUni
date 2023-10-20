package com.example.anjosgaar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CachorrosView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);
        bt_addCao.setOnClickListener{

        }
        inserir();
    }


 public static void inserir(){
        Cachorro cachorro = new Cachorro("Pipoca","femea","animada");
        CachorroDB.save(cachorro);
 }

}