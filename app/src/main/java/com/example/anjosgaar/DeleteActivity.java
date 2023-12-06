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

public class DeleteActivity extends AppCompatActivity {

    public void segundaTela(View view){
        Intent in =  new Intent(DeleteActivity.this,CachorrosView.class );
        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        new DeleteActivity.FetchDataFromDatabaseTask(DeleteActivity.this).execute();
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
                seuListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                seuListView.setOnItemClickListener((parent, view, position, id) -> {
                    SparseBooleanArray selectedItems = seuListView.getCheckedItemPositions();
                    for (int i = 0; i < selectedItems.size(); i++) {
                        int key = selectedItems.keyAt(i);
                        if (selectedItems.get(key)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String, Object> selectedItemData = CachorroAdapter.getItemData(key);
                                    String id = selectedItemData.get("id").toString();
                                    CachorroDB.delete(Integer.parseInt(id));
                                    Log.e("TAG", "removeuuuu");
                                    new DeleteActivity.FetchDataFromDatabaseTask(DeleteActivity.this).execute();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                });
            }
        }
    }

}