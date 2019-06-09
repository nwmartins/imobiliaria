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

    private EditText etId, etNome, etRG, etCPF, etEmail, etCreci;
    private Button btSave, btCancel;
    private FloatingActionButton fab;

    private Corretor corretor;

    private final int IMOVEL = 1;

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
                intent.putExtra("CORRETORID", corretor.getId());
                startActivityForResult(intent, IMOVEL);
            }
        });

        loadComponets();

    }

    private void loadComponets() {
        etId = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etRG = findViewById(R.id.etRg);
        etCPF = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);
        etCreci = findViewById(R.id.etCreci);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
        fab = findViewById(R.id.fab);

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
                setResult(RESULT_OK);
                finish();
            }
        });
        try {
            if ((int) getIntent().getExtras().get("EDICAO") == 1){ //Quer dizer que veio corretor em Edição
                long id = getIntent().getExtras().getLong("ID");
                corretor = Corretor.findById(Corretor.class, id);
                btSave.setHint(R.string.lbAtualizar);
                setFields(corretor);
                fab.show();
            } else {
                getLastId();
                fab.hide();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setFields(Corretor corretor) {
        etId.setText(String.valueOf(corretor.getCodigo()));
        etNome.setText(corretor.getNome());
        etRG.setText(corretor.getRg());
        etCPF.setText(corretor.getCpf());
        etEmail.setText(corretor.getEmail());
        etCreci.setText(String.valueOf(corretor.getCreci()));
    }

    private void save() {
        try {
            if (corretor != null) {
                setCorretor(corretor);
                corretor.update();
            } else {
                corretor = new Corretor();
                setCorretor(corretor);
                corretor.save();
            }
            setResult(RESULT_OK);
            finish();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setCorretor(Corretor corretor) {
        corretor.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
        corretor.setCpf(etCPF.getText().toString().replace(".", "").replace("-", "").trim());
        corretor.setEmail(etEmail.getText().toString().trim());
        corretor.setRg(etRG.getText().toString().replace(".", "").replace("-", "").trim());
        corretor.setNome(etNome.getText().toString().trim());
        corretor.setCreci(Integer.parseInt(etCreci.getText().toString().trim()));
    }

    private void getLastId() {
        Corretor last = Corretor.last(Corretor.class);
        int codigo = last != null ? last.getCodigo() + 1 : 1;
        etId.setText(String.valueOf(codigo));
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMOVEL) {
            int id = (int) data.getExtras().get("CORRETORID");
            corretor = Corretor.findById(Corretor.class, id);
        }
    }*/

}
