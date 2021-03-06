package com.example.norto.imobiliaria.consulta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.norto.imobiliaria.ClienteActivity;
import com.example.norto.imobiliaria.R;
import com.example.norto.imobiliaria.adapter.ClienteAdapter;
import com.example.norto.imobiliaria.models.Cliente;
import com.example.norto.imobiliaria.models.Endereco;

import java.util.List;

public class ConsultaClienteActivity extends AppCompatActivity {

    private EditText etBuscaCliente;
    private Button btNovo, btDelete;
    private ImageButton btBuscaCliente;
    private ListView lvCliente;

    private ClienteAdapter clienteAdapter;
    private Cliente cliente;

    private final int CLIENTE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadList();
    }

    private void loadList() {
        List<Cliente> clienteList = Cliente.listAll(Cliente.class);
        clienteAdapter = new ClienteAdapter(ConsultaClienteActivity.this, clienteList);
        lvCliente.setAdapter(clienteAdapter);
    }

    private void loadComponents() {
        etBuscaCliente = findViewById(R.id.etBuscaCliente);
        btBuscaCliente = findViewById(R.id.btBuscaCliente);
        btNovo = findViewById(R.id.btNovoCliente);
        btDelete = findViewById(R.id.btExcluirCliente);
        lvCliente = findViewById(R.id.lvCliente);

        loadEvents();
    }

    private void loadEvents() {
        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaClienteActivity.this, ClienteActivity.class);
                intent.putExtra("EDICAO", 0);
                startActivityForResult(intent, CLIENTE);
            }
        });

        lvCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) lvCliente.getItemAtPosition(position);
                Intent intent = new Intent(ConsultaClienteActivity.this, ClienteActivity.class);
                intent.putExtra("EDICAO", 1);
                intent.putExtra("CLIENTE", cliente.getId());
                startActivityForResult(intent, CLIENTE);
                return true;
            }
        });

        lvCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cliente = (Cliente) lvCliente.getItemAtPosition(position);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(cliente);
            }
        });

        btBuscaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etBuscaCliente.getText().toString().trim().length() > 0) {
                    List<Cliente> filterCliente = Cliente.find(Cliente.class,
                            "UPPER(NOME) LIKE UPPER('%" + etBuscaCliente.getText().toString() + "%') " +
                                    "OR CAST(ID AS VARCHAR(3)) LIKE '%" + etBuscaCliente.getText().toString() + "%'");

                    clienteAdapter = new ClienteAdapter(ConsultaClienteActivity.this, filterCliente);
                    lvCliente.setAdapter(clienteAdapter);
                } else
                    loadList();
            }
        });

    }

    private void delete(final Cliente cliente) {
        AlertDialog.Builder alertConfirmacao = new AlertDialog.Builder(ConsultaClienteActivity.this);
        alertConfirmacao.setTitle(R.string.lbTitleExclusao);
        alertConfirmacao.setMessage(R.string.lbMessageExclusao);
        alertConfirmacao.setNeutralButton(R.string.lbOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cliente.delete();
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
