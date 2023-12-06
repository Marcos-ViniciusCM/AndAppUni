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
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class VolunAdapter extends BaseAdapter {

    public static Map<String, Object> getItemData(int position) {
        return volun.get(position);
    }
    private static List<Map<String, Object>> volun;
    private LayoutInflater inflater;

    public VolunAdapter(Context context, List<Map<String, Object>> volun) {
        this.volun = volun;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return volun.size();
    }

    @Override
    public Object getItem(int position) {
        return volun.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_volun, parent, false);
        }


        Map<String, Object> voluns = volun.get(position);

        // Preenche os dados nos componentes de layout
        TextView idTextView = view.findViewById(R.id.idTextView);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView telefoneTextView = view.findViewById(R.id.telefoneTextView);
        TextView enderecoTextView = view.findViewById(R.id.enderecoTextView);
        TextView aboutTextView = view.findViewById(R.id.aboutTextView);
        if (voluns != null) {
            idTextView.setText("" + (voluns.get("id") != null ? voluns.get("id") : ""));
            nameTextView.setText("" + (voluns.get("name") != null ? voluns.get("name") : ""));
            telefoneTextView.setText("" + (voluns.get("telefone") != null ? voluns.get("telefone") : ""));
            enderecoTextView.setText("" + (voluns.get("endereco") != null ? voluns.get("endereco") : ""));
            aboutTextView.setText("" + (voluns.get("about") != null ? voluns.get("about") : ""));
        }

        return view;
    }
}