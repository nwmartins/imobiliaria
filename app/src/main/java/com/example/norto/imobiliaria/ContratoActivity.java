package com.example.norto.imobiliaria;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.norto.imobiliaria.models.Cliente;
import com.example.norto.imobiliaria.models.Contrato;
import com.example.norto.imobiliaria.models.Corretor;
import com.example.norto.imobiliaria.models.Imovel;
import com.example.norto.imobiliaria.models.TipoContrato;
import com.example.norto.imobiliaria.util.Mensagem;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContratoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner spCorretor, spCliente, spTpContrato, spImovel;
    private EditText etId, etValor, etMeses;
    private TextView tvDtContrato;
    private Button btSave, btCancel;
    private DatePickerDialog dtPickerDialog;
    private Calendar calendar = Calendar.getInstance();
    private int day, month, year;

    private Contrato contrato;
    private Date dtSelecionada;

    private ArrayAdapter<Cliente> clienteArrayAdapter;
    private ArrayAdapter<Corretor> corretorArrayAdapter;
    private ArrayAdapter<Imovel> imovelArrayAdapter;
    private ArrayAdapter<TipoContrato> tpContratoAdapter;
    private String msg = "";

    SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();

    }

    private void loadComponents() {
        spCorretor = findViewById(R.id.spCorretor);
        spCliente = findViewById(R.id.spCliente);
        spImovel = findViewById(R.id.spImovel);
        spTpContrato = findViewById(R.id.spTpContrato);
        tvDtContrato = findViewById(R.id.tvDtContrato);
        etId = findViewById(R.id.etCodigo);
        etMeses = findViewById(R.id.etMeses);
        etValor = findViewById(R.id.etValor);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        dtPickerDialog = new DatePickerDialog(ContratoActivity.this, this, year, month, day);

        loadEvents();
    }

    private void loadEvents() {
        refreshSpCorretor();
        spCorretor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refreshSpImovel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvDtContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtPickerDialog.show();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        spTpContrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spTpContrato.getSelectedItem().equals(TipoContrato.VENDA)) {
                    etMeses.setEnabled(false);
                } else
                    etMeses.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        refreshSpImovel();
        refreshSpCliente();
        refreshSpTpContrato();

        try {
            if (getIntent().getExtras().getInt("EDICAO") == 1){ //Quer dizer que veio corretor em Edição
                long id = getIntent().getExtras().getLong("ID");
                contrato = Contrato.findById(Contrato.class, id);
                btSave.setHint(R.string.lbAtualizar);
                spCorretor.setSelection(corretorArrayAdapter.getPosition(contrato.getCorretor()));
                spImovel.setSelection(imovelArrayAdapter.getPosition(contrato.getImovel()));
                spCliente.setSelection(clienteArrayAdapter.getPosition(contrato.getCliente()));
                spTpContrato.setSelection(tpContratoAdapter.getPosition(contrato.getTpContrato()));
                etValor.setText(String.valueOf(contrato.getValor()));
                etMeses.setText(String.valueOf(contrato.getMeses()));
                tvDtContrato.setText(String.valueOf(df.format(contrato.getDtContrato())));
                etId.setText(String.valueOf(contrato.getCodigo()));
            } else {
                getLastId();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Boolean validaCampos() {
        Date date;
        if (spCorretor.getSelectedItemPosition() == -1) {
                msg = "Corretor não pode ser vazio";
                return false;
        } else
            if (spImovel.getSelectedItemPosition() == -1) {
                msg = "Imóvel não pode ser vazio";
                return  false;
        } else
            if (spCliente.getSelectedItemPosition() == -1) {
                msg = "Cliente não pode ser vazio";
                return false;
        } else
            if (spTpContrato.getSelectedItemPosition() == -1) {
                msg = "Tipo do contrato não pode ser vazio";
                return false;
        } else
            if (etValor.getText().toString().trim().length() == 0) {
                msg = "Campo VALOR está vazio";
                return false;
        } else
            if (spTpContrato.getSelectedItem().equals(TipoContrato.ALUGUEL)) {
                if (etMeses.getText().toString().trim().length() == 0) {
                    msg = "Campo MESES está vazio";
                    return false;
                }
            }
        try {
            df.parse(tvDtContrato.getText().toString().trim());
        } catch (Exception e) {
            msg = "Data do CONTRATO não foi selecionada";
            return false;
        }


        return true;
    }

    private void save() {
        try {
            if (validaCampos()) {
                if (contrato != null) {
                    setContrato(contrato);
                    contrato.update();
                } else {
                    contrato = new Contrato();
                    setContrato(contrato);
                    contrato.save();
                }
//                Mensagem.ExibirMensagem(ContratoActivity.this, "Contrato Gravado Com Sucesso", TipoMensagem.SUCESSO);
                setResult(RESULT_OK);
                finish();
            } else
                if (msg.trim().length() > 0)
                    Mensagem.ExibirMensagem(ContratoActivity.this, msg, TipoMensagem.ALERTA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setContrato(Contrato contrato) {
        if (spTpContrato.getSelectedItem().equals(TipoContrato.VENDA))
            contrato.setMeses(-1);
        else
            contrato.setMeses(Integer.parseInt(etMeses.getText().toString().trim()));
        contrato.setDtContrato(dtSelecionada);
        contrato.setCliente((Cliente) spCliente.getSelectedItem());
        contrato.setCorretor((Corretor) spCorretor.getSelectedItem());
        contrato.setImovel((Imovel) spImovel.getSelectedItem());
        contrato.setTpContrato((TipoContrato) spTpContrato.getSelectedItem());
        contrato.setCodigo(Integer.parseInt(etId.getText().toString().trim()));
        contrato.setValor(Double.parseDouble(etValor.getText().toString().trim()));
    }

    private void refreshSpTpContrato() {
        List<TipoContrato> tipoContratoList = new ArrayList<>();
        tipoContratoList.add(TipoContrato.ALUGUEL);
        tipoContratoList.add(TipoContrato.VENDA);
        tpContratoAdapter = new ArrayAdapter<>(ContratoActivity.this, R.layout.support_simple_spinner_dropdown_item, tipoContratoList);
        spTpContrato.setAdapter(tpContratoAdapter);
    }

    private void refreshSpCliente() {
        clienteArrayAdapter = new ArrayAdapter<>(ContratoActivity.this, R.layout.support_simple_spinner_dropdown_item, Cliente.listAll(Cliente.class));
        spCliente.setAdapter(clienteArrayAdapter);
    }

    private void refreshSpImovel() {
        Corretor corretor = (Corretor) spCorretor.getSelectedItem();
        List<Imovel> imovelList = Imovel.find(Imovel.class, " corretor = " + corretor.getId());
        imovelArrayAdapter = new ArrayAdapter<>(ContratoActivity.this, R.layout.support_simple_spinner_dropdown_item, imovelList);
        spImovel.setAdapter(imovelArrayAdapter);
    }

    private void refreshSpCorretor() {
        corretorArrayAdapter = new ArrayAdapter<>(ContratoActivity.this, R.layout.support_simple_spinner_dropdown_item, Corretor.listAll(Corretor.class));
        spCorretor.setAdapter(corretorArrayAdapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //month = month + 1;
        tvDtContrato.setText(day + "/" + (month + 1) + "/" + year);
        dtSelecionada = new Date();
        dtSelecionada.setYear(year);
        dtSelecionada.setMonth(month + 1);
        dtSelecionada.setDate(day);
    }

    private void getLastId() {
        Contrato last = Contrato.last(Contrato.class);
        int codigo = last != null ? last.getCodigo() + 1 : 1;
        etId.setText(String.valueOf(codigo));
    }
}
