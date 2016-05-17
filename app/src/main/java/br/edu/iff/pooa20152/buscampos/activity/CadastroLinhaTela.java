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
import br.edu.iff.pooa20152.buscampos.domain.Linha;
import br.edu.iff.pooa20152.buscampos.domain.LinhaService;
import br.edu.iff.pooa20152.buscampos.helper.RestFullHelper;

public class CadastroLinhaTela extends AppCompatActivity {

    private EditText edCodigoLinha;
    private EditText edNomeLinha;
    private EditText edcodigoPonto;

    private final String TAG = "MAIN";

    private Button btSalvar;
    private Button btMostrar;
    private Button btDeletar;
    private Button btLimpar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_linha_tela);

        edCodigoLinha = (EditText) findViewById(R.id.edCodigoLinha);
        edNomeLinha = (EditText) findViewById(R.id.edNomeLinha);
        edcodigoPonto = (EditText) findViewById(R.id.edcodigoPonto);

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
                String filtro = edCodigoLinha.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edCodigoLinha.getText().toString()))
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
        edCodigoLinha.setText("");
        edNomeLinha.setText("");
        edcodigoPonto.setText("");
    }

    private void deletarInformationtoAPI() {

        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        LinhaTask bgtDel = new LinhaTask(
                durl + "/linhas",
                edCodigoLinha.getText().toString(),
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
        limpar();
    }

    private void getInformationtoAPI() {

        JSONObject params = null;

        LinhaTask bgtGet = new LinhaTask(
                durl + "/linhas",
                edCodigoLinha.getText().toString(),
                RestFullHelper.GET, params);

        bgtGet.execute();
    }

    private void postInformationtoAPI() {

        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edNomeLinha.getText().toString());
            params.put("ponto_id", edcodigoPonto.getText().toString());

        } catch (JSONException e) {

            e.printStackTrace();
        }

        LinhaTask bgtPost = new LinhaTask(
                durl + "/linhas", null, RestFullHelper.POST, params);
        bgtPost.execute();

    }

    private void putInformationtoAPI() {

        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("id", edCodigoLinha.getText().toString());
            params.put("nome", edNomeLinha.getText().toString());
            params.put("ponto_id", edcodigoPonto.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinhaTask bgtPut = new LinhaTask(
                durl + "/linhas", edCodigoLinha.getText().toString(),
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


    public class LinhaTask extends AsyncTask<String, String, Linha> {

        String url = null;
        String method = null;
        String id = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public LinhaTask(String url, String id, String method, JSONObject params) {

            this.url = url;
            this.method = method;
            this.params1 = params;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CadastroLinhaTela.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Linha jsonObject) {

            if (jsonObject != null) {

                edCodigoLinha.setText(jsonObject.getId().toString());
                edNomeLinha.setText(jsonObject.getNome());
                edcodigoPonto.setText(jsonObject.getPonto());
            }

            dialog.dismiss();
        }

        @Override
        protected Linha doInBackground(String... params) {

            LinhaService linhaService = new LinhaService(url, id, method, params1);
            Linha linha = linhaService.execute();

            return linha;

        }
    }
}
