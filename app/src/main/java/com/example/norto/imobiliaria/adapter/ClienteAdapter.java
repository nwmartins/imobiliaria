package com.example.norto.imobiliaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.norto.imobiliaria.models.Cliente;
import com.example.norto.imobiliaria.R;

import java.util.List;

public class ClienteAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Cliente> clienteList;

    public ClienteAdapter(Context context, List<Cliente> clienteList) {
        this.clienteList = clienteList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return clienteList.size();
    }

    @Override
    public Object getItem(int position) {
        return clienteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente cliente = clienteList.get(position);
        convertView = inflater.inflate(R.layout.item_cliente, null);
        ((TextView) convertView.findViewById(R.id.tvIdCliente)).setText(String.valueOf(cliente.getCodigo()));
        ((TextView) convertView.findViewById(R.id.tvNomeCliente)).setText(cliente.getNome());
        return convertView;
    }
}
