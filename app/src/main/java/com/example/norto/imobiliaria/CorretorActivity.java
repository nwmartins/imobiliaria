package com.example.norto.imobiliaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.norto.imobiliaria.consulta.ConsultaCorretorActivity;
import com.example.norto.imobiliaria.models.Corretor;
import com.example.norto.imobiliaria.models.Endereco;
import com.example.norto.imobiliaria.models.Imovel;

import java.util.ArrayList;
import java.util.List;

public class CorretorActivity extends AppCompatActivity {

    private EditText etId, etNome, etRG, etCPF, etComplemento,etEmail, etCreci;
    private Button btSave, btCancel;

    private Corretor corretor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corretor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(CorretorActivity.this, ImovelActivity.class);
                startActivity(intent);
            }
        });

        loadComponets();

    }

    private void loadComponets() {
        etId = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etRG = findViewById(R.id.etRg);
        etCPF = findViewById(R.id.etCpf);
        etComplemento = findViewById(R.id.etComplemento);
        etEmail = findViewById(R.id.etEmail);
        etCreci = findViewById(R.id.etCreci);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);

        loadEvents();
    }

    private void loadEvents() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        try {
            if ((int) getIntent().getExtras().get("EDICAO") == 1){ //Quer dizer que veio cliente em Edição
                corretor = (Corretor) getIntent().getExtras().get("CORRETOR");
                etNome.setText(corretor.getNome());
                etId.setText(String.valueOf(corretor.getCodigo()));
            } else {
                getLastId();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void save() {
        try {
            if (corretor != null) {
                corretor.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
                corretor.setCpf(etCPF.getText().toString().replace(".", "").replace("-", "").trim());
                corretor.setEmail(etEmail.getText().toString().trim());
                corretor.setRg(etRG.getText().toString().replace(".", "").replace("-", "").trim());
                corretor.setCreci(Integer.parseInt(etCreci.getText().toString().trim()));
                corretor.update();
            } else {
                corretor = new Corretor();

                corretor.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
                corretor.setCpf(etCPF.getText().toString().replace(".", "").replace("-", "").trim());
                corretor.setEmail(etEmail.getText().toString().trim());
                corretor.setRg(etRG.getText().toString().replace(".", "").replace("-", "").trim());
                corretor.setNome(etNome.getText().toString().trim());
                corretor.setCreci(Integer.parseInt(etCreci.getText().toString().trim()));
                corretor.save();

                Intent intent = new Intent(CorretorActivity.this, ConsultaCorretorActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getLastId() {
        Corretor last = Corretor.last(Corretor.class);

        int codigo = last != null ? last.getCodigo() + 1 : 1;
        etId.setText(String.valueOf(codigo));
    }

}
