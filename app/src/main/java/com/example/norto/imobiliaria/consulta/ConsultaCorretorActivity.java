package com.example.norto.imobiliaria.consulta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.norto.imobiliaria.CorretorActivity;
import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.adapter.CorretorAdapter;
import com.example.norto.imobiliaria.models.Corretor;

import java.util.List;

public class ConsultaCorretorActivity extends AppCompatActivity {

    private EditText etBuscaCorretor;
    private Button btNovo, btExcluir;
    private ImageButton btBuscaCorretor;
    private ListView lvCorretor;

    private CorretorAdapter corretorAdapter;
    private Corretor corretor;

    private final int CORRETOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_corretor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadList();
    }

    private void loadList() {
        List<Corretor> corretorList = Corretor.listAll(Corretor.class);
        corretorAdapter = new CorretorAdapter(ConsultaCorretorActivity.this, corretorList);
        lvCorretor.setAdapter(corretorAdapter);
    }

    private void loadComponents() {
        etBuscaCorretor = findViewById(R.id.etBuscaCorretor);
        btBuscaCorretor = findViewById(R.id.btBuscaCorretor);
        btNovo = findViewById(R.id.btNovoCorretor);
        btExcluir = findViewById(R.id.btExcluirCorretor);
        lvCorretor = findViewById(R.id.lvCorretor);

        loadEvents();
    }

    private void loadEvents() {
        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaCorretorActivity.this, CorretorActivity.class);
                intent.putExtra("EDICAO", 0);
                startActivityForResult(intent, CORRETOR);
            }
        });
        lvCorretor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                corretor = (Corretor) lvCorretor.getItemAtPosition(position);
                Intent intent = new Intent(ConsultaCorretorActivity.this, CorretorActivity.class);
                intent.putExtra("EDICAO", 1);
                intent.putExtra("ID", corretor.getId());
                startActivityForResult(intent, CORRETOR);
                return true;
            }
        });
        lvCorretor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                corretor = (Corretor) lvCorretor.getItemAtPosition(position);
            }
        });
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(corretor);
            }
        });
    }

    private void delete(final Corretor corretor) {
        AlertDialog.Builder alertConfirmacao = new AlertDialog.Builder(ConsultaCorretorActivity.this);
        alertConfirmacao.setTitle(R.string.lbTitleExclusao);
        alertConfirmacao.setMessage(R.string.lbMessageExclusao);
        alertConfirmacao.setNeutralButton(R.string.lbOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                corretor.delete();
                loadList();
            }
        });
        alertConfirmacao.setNegativeButton(R.string.lbCancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertConfirmacao.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadList();
        }
    }

}
