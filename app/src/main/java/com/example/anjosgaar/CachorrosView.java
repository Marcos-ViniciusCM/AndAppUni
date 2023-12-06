package com.example.anjosgaar;

import static com.example.anjosgaar.CachorroDB.*;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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

    public void delCao(View view){
        Intent in =  new Intent(CachorrosView.this,DeleteActivity.class );
        startActivity(in);
    }

    public void addCao(View view){
        Intent in =  new Intent(CachorrosView.this,addCao.class );
        startActivity(in);
    }

    public void mostrar(View view){
        Intent in =  new Intent(CachorrosView.this,MostrarActivity.class );
        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachorros_view);
    }


}
