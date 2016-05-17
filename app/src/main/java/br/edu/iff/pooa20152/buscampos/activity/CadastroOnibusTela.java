package br.edu.iff.pooa20152.buscampos.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.iff.pooa20152.buscampos.R;
import br.edu.iff.pooa20152.buscampos.domain.Onibus;
import br.edu.iff.pooa20152.buscampos.domain.OnibusService;
import br.edu.iff.pooa20152.buscampos.helper.RestFullHelper;

public class CadastroOnibusTela extends AppCompatActivity {

    private EditText edCodigoOnibus;
    private EditText edCodigoEmpresa;
    private EditText edPlaca;
    private EditText edNumAssento;

    private final String TAG = "MAIN";

    private Button btSalvar;
    private Button btMostrar;
    private Button btDeletar;
    private Button btLimpar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_onibus_tela);

        edCodigoOnibus = (EditText) findViewById(R.id.edCodigoOnibus);
        edCodigoEmpresa = (EditText) findViewById(R.id.edCodigoEmpresa);
        edPlaca = (EditText) findViewById(R.id.edPlaca);
        edNumAssento = (EditText) findViewById(R.id.edNumAssento);

        durl = getString(R.string.URL);

        btLimpar = (Button) findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                limpar();
            }
        });

        btMostrar = (Button) findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtro = edCodigoOnibus.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edCodigoOnibus.getText().toString()))
                    postInformationtoAPI();
                else
                    putInformationtoAPI();
            }
        });

        btDeletar = (Button) findViewById(R.id.btDeletar);
        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarInformationtoAPI();
            }
        });
    }

    private void limpar() {
        edCodigoOnibus.setText("");
        edCodigoEmpresa.setText("");
        edPlaca.setText("");
        edNumAssento.setText("");
    }

    private void deletarInformationtoAPI() {

        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        OnibusTask bgtDel = new OnibusTask(
                durl + "/onibus",
                edCodigoOnibus.getText().toString(),
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
        limpar();
    }

    private void getInformationtoAPI() {

        JSONObject params = null;

        OnibusTask bgtGet = new OnibusTask(
                durl + "/onibus",
                edCodigoOnibus.getText().toString(),
                RestFullHelper.GET, params);

        bgtGet.execute();
    }

    private void postInformationtoAPI() {

        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("empresa_id", edCodigoEmpresa.getText().toString());
            params.put("placa", edPlaca.getText().toString());
            params.put("num_assento", edNumAssento.getText().toString());

        } catch (JSONException e) {

            e.printStackTrace();
        }

        OnibusTask bgtPost = new OnibusTask(
                durl + "/onibus", null, RestFullHelper.POST, params);
        bgtPost.execute();

    }

    private void putInformationtoAPI() {

        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("id", edCodigoOnibus.getText().toString());
            params.put("empresa_id", edCodigoEmpresa.getText().toString());
            params.put("placa", edPlaca.getText().toString());
            params.put("num_assento", edNumAssento.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OnibusTask bgtPut = new OnibusTask(
                durl + "/onibus", edCodigoOnibus.getText().toString(),
                RestFullHelper.PUT, params);
        bgtPut.execute();
        limpar();

    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public class OnibusTask extends AsyncTask<String, String, Onibus> {

        String url = null;
        String method = null;
        String id = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public OnibusTask(String url, String id, String method, JSONObject params) {

            this.url = url;
            this.method = method;
            this.params1 = params;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CadastroOnibusTela.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Onibus jsonObject) {

            if (jsonObject != null) {

                edCodigoOnibus.setText(jsonObject.getId().toString());
                edCodigoEmpresa.setText(jsonObject.getEmpresa());
                edPlaca.setText(jsonObject.getPlaca());
                edNumAssento.setText(jsonObject.getNum_assento());
            }

            dialog.dismiss();
        }

        @Override
        protected Onibus doInBackground(String... params) {

            OnibusService onibusService = new OnibusService(url, id, method, params1);
            Onibus onibus = onibusService.execute();

            return onibus;

        }
    }
}
