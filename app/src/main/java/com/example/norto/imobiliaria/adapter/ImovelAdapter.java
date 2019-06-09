package com.example.norto.imobiliaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.models.Imovel;

import org.w3c.dom.Text;

import java.util.List;

public class ImovelAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Imovel> imovelList;

    public ImovelAdapter (Context context, List<Imovel> imovelList) {
        this.imovelList = imovelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imovelList.size();
    }

    @Override
    public Object getItem(int position) {
        return imovelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Imovel imovel = imovelList.get(position);
        convertView = inflater.inflate(R.layout.item_imovel, null);
        ((ImageView) convertView.findViewById(R.id.ivImovel)).setBackgroundResource(R.mipmap.ic_home);
        ((TextView) convertView.findViewById(R.id.tvIdImovel)).setText(String.valueOf(imovel.getCodigo()));
        ((TextView) convertView.findViewById(R.id.tvDescImovel)).setText(imovel.getDescricao());
        return convertView;
    }
}
