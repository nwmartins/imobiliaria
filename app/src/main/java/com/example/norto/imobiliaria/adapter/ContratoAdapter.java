package com.example.norto.imobiliaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.models.Contrato;

import java.text.SimpleDateFormat;
import java.util.List;

    public class ContratoAdapter extends BaseAdapter {

        SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy");

        LayoutInflater inflater;
        List<Contrato> contratoList;

        public ContratoAdapter(Context context, List<Contrato> contratoList) {
            this.contratoList = contratoList;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return contratoList.size();
        }

        @Override
        public Object getItem(int position) {
            return contratoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Contrato contrato = contratoList.get(position);
            convertView = inflater.inflate(R.layout.item_consulta_contrato, null);
            ((TextView) convertView.findViewById(R.id.tvId)).setText(String.valueOf(contrato.getCodigo()));
            ((TextView) convertView.findViewById(R.id.tvDescImovel)).setText(contrato.getImovel().toString());
            ((TextView) convertView.findViewById(R.id.tvDescCliente)).setText(contrato.getCliente().toString());
            ((TextView) convertView.findViewById(R.id.tvDescCorretor)).setText(contrato.getCorretor().toString());
            ((TextView) convertView.findViewById(R.id.tvContatro)).setText(contrato.getTpContrato().toString());
            ((TextView) convertView.findViewById(R.id.tvDtContrato)).setText(String.valueOf(df.format(contrato.getDtContrato())));
            return convertView;
        }
    }
