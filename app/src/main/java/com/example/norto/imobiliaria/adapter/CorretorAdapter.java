package com.example.norto.imobiliaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.models.Corretor;

import java.util.List;

public class CorretorAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Corretor> corretorList;

    public CorretorAdapter(Context context, List<Corretor> corretorList) {
        this.corretorList = corretorList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return corretorList.size();
    }

    @Override
    public Object getItem(int position) {
        return corretorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Corretor corretor = corretorList.get(position);
        convertView = inflater.inflate(R.layout.item_corretor, null);
        ((TextView) convertView.findViewById(R.id.tvIdCorretor)).setText(String.valueOf(corretor.getCodigo()));
        ((TextView) convertView.findViewById(R.id.tvNomeCorretor)).setText(corretor.getNome());
        return convertView;
    }
}
