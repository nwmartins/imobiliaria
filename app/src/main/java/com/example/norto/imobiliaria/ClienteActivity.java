package com.example.norto.imobiliaria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.norto.imobiliaria.models.Cliente;
import com.example.norto.imobiliaria.models.Endereco;

public class ClienteActivity extends AppCompatActivity {

    private EditText etId, etNome, etRG, etCPF, etComplemento;
    private EditText etEmail, etLogradouro, etBairro, etNumero;
    private Button btSave, btCancel;

    private Cliente cliente;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponets();

    }

    private void loadComponets() {
        etId = findViewById(R.id.etCodigo);
        etNome = findViewById(R.id.etNome);
        etRG = findViewById(R.id.etRg);
        etCPF = findViewById(R.id.etCpf);
        etComplemento = findViewById(R.id.etComplemento);
        etEmail = findViewById(R.id.etEmail);
        etLogradouro = findViewById(R.id.etLogradouro);
        etBairro = findViewById(R.id.etBairro);
        etNumero = findViewById(R.id.etNumero);
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
                cliente = (Cliente) getIntent().getExtras().get("CLIENTE");
                etNome.setText(cliente.getNome());
                etId.setText(String.valueOf(cliente.getCodigo()));
                etNumero.setText(String.valueOf(cliente.getEnderco().getNumero()));
            } else {
                getLastId();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void save() {
        try {
            if (cliente != null) {
                endereco.setLogradouro(etLogradouro.getText().toString().trim());
                endereco.setBairro(etBairro.getText().toString().trim());
                endereco.setCodigo(Integer.parseInt(etId.getText().toString().trim())); //Tem q ver se vai funcionar, creio q sim
                endereco.setComplemento(etComplemento.getText().toString().trim());
                endereco.setNumero(Integer.parseInt(etNumero.getText().toString().trim()));
                //endereco.update();

                cliente.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
                cliente.setCpf(etCPF.getText().toString().replace(".", "").replace("-", "").trim());
                cliente.setEmail(etEmail.getText().toString().trim());
                cliente.setEnderco(endereco);
                cliente.setRg(etRG.getText().toString().replace(".", "").replace("-", "").trim());
                cliente.update();
            } else {
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                endereco.setLogradouro(etLogradouro.getText().toString().trim());
                endereco.setBairro(etBairro.getText().toString().trim());
                endereco.setCodigo(Integer.parseInt(etId.getText().toString().trim())); //Tem q ver se vai funcionar, creio q sim
                endereco.setComplemento(etComplemento.getText().toString().trim());
                endereco.setNumero(Integer.parseInt(etNumero.getText().toString().trim()));
                endereco.save();

                cliente.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
                cliente.setCpf(etCPF.getText().toString().replace(".", "").replace("-", "").trim());
                cliente.setEmail(etEmail.getText().toString().trim());
                cliente.setEnderco(endereco);
                cliente.setRg(etRG.getText().toString().replace(".", "").replace("-", "").trim());
                cliente.setNome(etNome.getText().toString().trim());
                cliente.save();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void getLastId() {
        Cliente last = Cliente.last(Cliente.class);

        int codigo = last != null ? last.getCodigo() + 1 : 1;
        etId.setText(String.valueOf(codigo));
    }

}
