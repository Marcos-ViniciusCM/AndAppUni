package com.example.anjosgaar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VolunDel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volun_del);
        new VolunDel.FetchDataFromDatabaseTask(VolunDel.this).execute();
    }
    public void segundaTela(View view){
        Intent in =  new Intent(VolunDel.this,MainActivity.class );
        startActivity(in);
    }

    protected List<Map<String, Object>> doInBackground(Void... voids) {
        List<Map<String, Object>> volun = new ArrayList<>();

        try {
            volun = VolunDB.obterVolun();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return volun;
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
                return VolunDB.obterVolun();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TAG", "era para retornar lista vazia");
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> volun) {
            Context context = contextReference.get();
            if (context != null) {
                ListView seuListView = findViewById(R.id.seuListView);
                seuListView.setAdapter(new VolunAdapter(context, volun));
                seuListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                seuListView.setOnItemClickListener((parent, view, position, id) -> {
                    SparseBooleanArray selectedItems = seuListView.getCheckedItemPositions();
                    for (int i = 0; i < selectedItems.size(); i++) {
                        int key = selectedItems.keyAt(i);
                        if (selectedItems.get(key)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String, Object> selectedItemData = VolunAdapter.getItemData(key);
                                    String id = selectedItemData.get("id").toString();
                                    VolunDB.delete(Integer.parseInt(id));
                                    Log.e("TAG", "removeuuuu");
                                    new VolunDel.FetchDataFromDatabaseTask(VolunDel.this).execute();
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