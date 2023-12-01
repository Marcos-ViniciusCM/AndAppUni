package com.example.anjosgaar;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CachorroAdapter extends BaseAdapter {
    private List<Map<String, Object>> cachorros;
    private LayoutInflater inflater;

    public CachorroAdapter(Context context, List<Map<String, Object>> cachorros) {
        this.cachorros = cachorros;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cachorros.size();
    }

    @Override
    public Object getItem(int position) {
        return cachorros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_cachorro, parent, false);
        }

        // Obtém o cachorro na posição atual
        Map<String, Object> cachorro = cachorros.get(position);

        // Preenche os dados nos componentes de layout
        TextView nomeTextView = view.findViewById(R.id.nomeTextView);
        TextView sexoTextView = view.findViewById(R.id.sexoTextView);
        TextView descricaoTextView = view.findViewById(R.id.descricaoTextView);
        ImageView imagemImageView = view.findViewById(R.id.imagemImageView);
        Log.d("CachorroAdapter", "Posição: " + position);
        // Verifica se os dados não são nulos antes de definir os textos e a imagem
        if (cachorro != null) {
            nomeTextView.setText("Nome: " + (cachorro.get("nome") != null ? cachorro.get("nome") : ""));
            sexoTextView.setText("Sexo: " + (cachorro.get("sexo") != null ? cachorro.get("sexo") : ""));
            descricaoTextView.setText("Descrição: " + (cachorro.get("descricao") != null ? cachorro.get("descricao") : ""));
            Log.d("CachorroAdapter", "Nome: " + cachorro.get("nome"));
            Log.d("CachorroAdapter", "Sexo: " + cachorro.get("sexo"));
            Log.d("CachorroAdapter", "Descrição: " + cachorro.get("descricao"));

            // Verifica se a imagem não é nula antes de configurar no ImageView
            Object imagemObject = cachorro.get("foto");
            if (imagemObject instanceof InputStream) {
                imagemImageView.setImageBitmap(getBitmapFromInputStream((InputStream) imagemObject));
            }
        }

        return view;
    }

    // Método fictício para converter InputStream em Bitmap
    private Bitmap getBitmapFromInputStream(InputStream inputStream) {
        // Implemente a lógica real para converter InputStream em Bitmap
        // Retorna um bitmap ou null, dependendo da lógica implementada
        return null;
    }
}

