package com.example.norto.imobiliaria.util;

import android.app.AlertDialog;
import android.content.Context;

import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.TipoMensagem;

public class Mensagem {
    public static void ExibirMensagem(Context context, String msg, TipoMensagem tipo) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        if (tipo == TipoMensagem.ALERTA) {
            alert.setTitle("Atenção");
            alert.setIcon(R.drawable.ic_alert);
        } else if (tipo == TipoMensagem.ERRO) {
            alert.setTitle("Erro");
            alert.setIcon(R.drawable.ic_error);
        } else if (tipo == TipoMensagem.SUCESSO) {
            alert.setTitle("Sucesso");
            alert.setIcon(R.drawable.ic_done);
        }
        alert.setMessage(msg);
        alert.setNeutralButton("Ok", null);
        alert.show();
    }
}
