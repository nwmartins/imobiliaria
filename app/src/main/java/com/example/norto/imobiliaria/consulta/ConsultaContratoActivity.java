package com.example.norto.imobiliaria.consulta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.norto.imobiliaria.ContratoActivity;
import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.TipoMensagem;
import com.example.norto.imobiliaria.adapter.ContratoAdapter;
import com.example.norto.imobiliaria.models.Contrato;
import com.example.norto.imobiliaria.util.Mensagem;

public class ConsultaContratoActivity extends AppCompatActivity {

    private Button btNew, btDelete;
    private ListView lvContrato;

    private ContratoAdapter contratoAdapter;
    private Contrato contrato;

    private final int CONTRATO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contrato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        loadComponents();

    }

    private void loadComponents() {

        btNew = findViewById(R.id.btNewCorretor);
        btDelete = findViewById(R.id.btDeleteCorretor);
        lvContrato = findViewById(R.id.lvConsultaCorretor);

        loadEvents();
    }

    private void loadEvents() {
        refreshList();

        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaContratoActivity.this, ContratoActivity.class);
                intent.putExtra("EDICAO", 0);
                startActivityForResult(intent, CONTRATO);
            }
        });

        lvContrato.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contrato = (Contrato) lvContrato.getItemAtPosition(position);
                Intent intent = new Intent(ConsultaContratoActivity.this, ContratoActivity.class);
                intent.putExtra("EDICAO", 1);
                intent.putExtra("ID", contrato.getId());
                startActivityForResult(intent, CONTRATO);
                return true;
            }
        });

        lvContrato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contrato = (Contrato) lvContrato.getItemAtPosition(position);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contrato.delete(contrato);
                refreshList();
                Mensagem.ExibirMensagem(ConsultaContratoActivity.this,"Registro Excluído com sucesso", TipoMensagem.SUCESSO);
            }
        });
    }

    private void refreshList() {
        contratoAdapter = new ContratoAdapter(ConsultaContratoActivity.this, Contrato.listAll(Contrato.class));
        lvContrato.setAdapter(contratoAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CONTRATO) {
            refreshList();
        }
    }

}
