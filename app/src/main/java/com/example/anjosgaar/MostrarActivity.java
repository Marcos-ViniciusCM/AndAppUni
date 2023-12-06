package com.example.anjosgaar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MostrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        new MostrarActivity.FetchDataFromDatabaseTask(MostrarActivity.this).execute();
    }

    protected List<Map<String, Object>> doInBackground(Void... voids) {
        List<Map<String, Object>> cachorros = new ArrayList<>();

        try {
            cachorros = CachorroDB.obterCao();
        } catch (Exception e) {
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
            try {
                Log.e("TAG", "era para retornar");
                return CachorroDB.obterCao();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TAG", "era para retornar lista vazia");
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> cachorros) {
            Context context = contextReference.get();
            if (context != null) {
                ListView seuListView = findViewById(R.id.seuListView);
                seuListView.setAdapter(new CachorroAdapter(context, cachorros));
            }
        }
    }

}