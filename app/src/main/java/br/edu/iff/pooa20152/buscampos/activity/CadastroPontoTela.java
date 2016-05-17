package br.edu.iff.pooa20152.buscampos.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import br.edu.iff.pooa20152.buscampos.domain.Ponto;
import br.edu.iff.pooa20152.buscampos.domain.PontoService;
import br.edu.iff.pooa20152.buscampos.helper.RestFullHelper;

public class CadastroPontoTela extends AppCompatActivity {

    private EditText edCodPonto;
    private EditText edRua;
    private EditText edBairro;
    private EditText edLatitude;
    private EditText edLongitude;

    private final String TAG = "MAIN";

    private Button btSalvar;
    private Button btMostrar;
    private Button btDeletar;
    private Button btLimpar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_ponto_tela);

        edCodPonto = (EditText) findViewById(R.id.edCodPonto);
        edRua = (EditText) findViewById(R.id.edRua);
        edBairro = (EditText) findViewById(R.id.edBairro);
        edLatitude = (EditText) findViewById(R.id.edLatitude);
        edLongitude = (EditText) findViewById(R.id.edLongitude);

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
                String filtro = edCodPonto.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edCodPonto.getText().toString()))
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
        edCodPonto.setText("");
        edRua.setText("");
        edBairro.setText("");
        edLatitude.setText("");
        edLongitude.setText("");
    }

    private void deletarInformationtoAPI() {

        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        PontoTask bgtDel = new PontoTask(
                durl + "/pontos",
                edCodPonto.getText().toString(),
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
        limpar();
    }

    private void getInformationtoAPI() {

        JSONObject params = null;

        PontoTask bgtGet = new PontoTask(
                durl + "/pontos",
                edCodPonto.getText().toString(),
                RestFullHelper.GET, params);

        bgtGet.execute();
    }

    private void postInformationtoAPI() {

        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("rua", edRua.getText().toString());
            params.put("bairro", edBairro.getText().toString());
            params.put("latitude", edLatitude.getText().toString());
            params.put("longitude", edLongitude.getText().toString());

        } catch (JSONException e) {

            e.printStackTrace();
        }

        PontoTask bgtPost = new PontoTask(
                durl + "/pontos", null, RestFullHelper.POST, params);
        bgtPost.execute();
    }

    private void putInformationtoAPI() {

        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("id", edCodPonto.getText().toString());
            params.put("rua", edRua.getText().toString());
            params.put("bairro", edBairro.getText().toString());
            params.put("latitude", edLatitude.getText().toString());
            params.put("longitude", edLongitude.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        PontoTask bgtPut = new PontoTask(
                durl + "/pontos", edCodPonto.getText().toString(),
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


    public class PontoTask extends AsyncTask<String, String, Ponto> {

        String url = null;
        String method = null;
        String id = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public PontoTask(String url, String id, String method, JSONObject params) {
            this.url = url;
            this.method = method;
            this.params1 = params;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CadastroPontoTela.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Ponto jsonObject) {

            if (jsonObject != null) {

                edCodPonto.setText(jsonObject.getId().toString());
                edRua.setText(jsonObject.getRua());
                edBairro.setText(jsonObject.getBairro());
                edLatitude.setText(jsonObject.getLatitude());
                edLongitude.setText(jsonObject.getLongitude());

            }

            dialog.dismiss();
        }

        @Override
        protected Ponto doInBackground(String... params) {

            PontoService pontoService = new PontoService(url, id, method, params1);
            Ponto ponto = pontoService.execute();

            return ponto;

        }
    }
}