package com.example.anjosgaar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CachorrosView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);
        Button seuBotao = findViewById(R.id.bt_addCao);
        seuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aqui você pode executar a função desejada quando o botão for clicado
                inserir();
            }
        });
    }


 public static void inserir(){
        Cachorro cachorro = new Cachorro("Pipoca","femea","animada");
        CachorroDB.save(cachorro);
 }

}