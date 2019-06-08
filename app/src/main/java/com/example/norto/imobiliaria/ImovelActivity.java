package com.example.norto.imobiliaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.norto.imobiliaria.adapter.ImovelAdapter;
import com.example.norto.imobiliaria.models.Corretor;
import com.example.norto.imobiliaria.models.Endereco;
import com.example.norto.imobiliaria.models.Imovel;

import java.util.ArrayList;
import java.util.List;

public class ImovelActivity extends AppCompatActivity {

    private EditText etCodigo, etDescricao, etLogradouro, etBairro,
            etNumero, etComplemento, etComodos, etTerreno;
    private Button btAdd, btSave, btCancel;
    private CheckBox cbMobiliada;
    private ListView lvImovel;

    private Corretor corretor;
    private ImovelAdapter imovelAdapter;
    private Imovel imovel;
    private List<Imovel> imovelList = new ArrayList<Imovel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponets();

        loadList();

    }

    private void loadComponets() {
        etCodigo = findViewById(R.id.etCodigo);
        etDescricao = findViewById(R.id.etDescricao);
        etLogradouro = findViewById(R.id.etLogradouro);
        etBairro = findViewById(R.id.etBairro);
        etNumero = findViewById(R.id.etNumero);
        etComplemento = findViewById(R.id.etComplemento);
        etComodos = findViewById(R.id.etComodos);
        etTerreno = findViewById(R.id.etTerreno);
        btAdd = findViewById(R.id.btAdd);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
        cbMobiliada = findViewById(R.id.cbMobiliada);
        lvImovel = findViewById(R.id.lvImovel);
        
        loadEvents();
        getLastId();
    }

    private void loadEvents() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ouput = new Intent();
                ouput.putExtra("CORRETORID", corretor.getId());
                setResult(RESULT_OK, ouput);
                finish();
            }
        });
        lvImovel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                imovel = (Imovel) lvImovel.getItemAtPosition(position);
                btAdd.setHint(R.string.lbAtualizar);
                setFields(imovel);
                return true;
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ouput = new Intent();
                ouput.putExtra("CORRETORID", corretor.getId());
                setResult(RESULT_OK, ouput);
                finish();
            }
        });
        try {
            long id = getIntent().getExtras().getLong("CORRETORID");
            corretor = Corretor.findById(Corretor.class, id);
            corretor.setImovelList(corretor.getImovelList());
            imovelList.addAll(corretor.getImovelList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setFields(Imovel imovel) {
        etCodigo.setText(String.valueOf(imovel.getCodigo()));
        etDescricao.setText(imovel.getDescricao());
        etLogradouro.setText(imovel.getEndereco().getLogradouro());
        etBairro.setText(imovel.getEndereco().getBairro());
        etNumero.setText(String.valueOf(imovel.getEndereco().getNumero()));
        etComplemento.setText(imovel.getEndereco().getComplemento());
        etComodos.setText(String.valueOf(imovel.getQteComodos()));
        etTerreno.setText(String.valueOf(imovel.getTamTerreno()));
        cbMobiliada.setChecked(imovel.getMobiliada());
    }

    private void cleanFields() {
        getLastId();
        etDescricao.setText("");
        etLogradouro.setText("");
        etBairro.setText("");
        etNumero.setText("");
        etComplemento.setText("");
        etComodos.setText("");
        etTerreno.setText("");
        cbMobiliada.setChecked(false);
    }

    private void save() {
        try {
            if (imovel != null) {
                setImovel(imovel);
                imovelList.remove(imovel);
                imovel.update();
                imovelList.add(imovel);
                cleanFields();
                loadList();
            } else {
                imovel = new Imovel();
                setImovel(imovel);
                imovel.save();
                imovelList.add(imovel);
                cleanFields();
                loadList();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setImovel(Imovel imovel) {
        imovel.setCodigo(Integer.parseInt(etCodigo.getText().toString().trim()));
        imovel.setDescricao(etDescricao.getText().toString().trim());
        imovel.setCorretor(corretor);
        Endereco endereco = new Endereco();
        endereco.setLogradouro(etLogradouro.getText().toString().trim());
        endereco.setBairro(etBairro.getText().toString().trim());
        endereco.setNumero(Integer.parseInt(etNumero.getText().toString().trim()));
        endereco.setComplemento(etComplemento.getText().toString().trim());
        endereco.setCodigo(getLastIdEndereco());
        imovel.setEndereco(endereco);
        imovel.setQteComodos(Integer.parseInt(etComodos.getText().toString().trim()));
        imovel.setTamTerreno(Integer.parseInt(etTerreno.getText().toString().trim()));
        imovel.setMobiliada(cbMobiliada.isChecked());

    }

    private void getLastId() {
        Imovel last = Imovel.last(Imovel.class);

        int codigo = last != null ? last.getCodigo() + 1 : 1;
        etCodigo.setText(String.valueOf(codigo));
    }

    private int getLastIdEndereco() {
        Endereco last = Endereco.last(Endereco.class);

        int codigo = last != null ? last.getCodigo() + 1 : 1;
        return codigo;
    }

    private void loadList() {
        imovelAdapter = new ImovelAdapter(ImovelActivity.this, imovelList);
        lvImovel.setAdapter(imovelAdapter);
    }

}
