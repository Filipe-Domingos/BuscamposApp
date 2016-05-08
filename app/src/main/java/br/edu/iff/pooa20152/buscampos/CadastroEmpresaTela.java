package br.edu.iff.pooa20152.buscampos;

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

import br.edu.iff.pooa20152.buscampos.helper.RestFullHelper;

public class CadastroEmpresaTela extends AppCompatActivity {

    private EditText edCodigoEmpresa;
    private EditText edNomeEmpresa;
    private EditText edCnpj;
    private EditText edTelefone;
    private EditText edEndereco;

    private final String TAG = "MAIN";

    private Button btSalvar;
    private Button btMostrar;
    private Button btDeletar;
    private Button btLimpar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_empresa_tela);

        edCodigoEmpresa = (EditText) findViewById(R.id.edCodigoEmpresa);
        edNomeEmpresa = (EditText) findViewById(R.id.edNomeEmpresa);
        edCnpj = (EditText) findViewById(R.id.edCnpj);
        edTelefone = (EditText) findViewById(R.id.edTelefone);
        edEndereco = (EditText) findViewById(R.id.edEndereco);

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
                String filtro = edCodigoEmpresa.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edCodigoEmpresa.getText().toString()))
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

    private void limpar(){
        edCodigoEmpresa.setText("");
        edNomeEmpresa.setText("");
        edCnpj.setText("");
        edTelefone.setText("");
        edEndereco.setText("");
    }

    private void deletarInformationtoAPI() {
        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        FabricanteTask bgtDel = new FabricanteTask(
                durl + "/empresas/"
                        + edCodigoEmpresa.getText().toString() + ".json",
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
        limpar();
    }

    private void getInformationtoAPI() {
        JSONObject params = null;

        FabricanteTask bgtGet = new FabricanteTask(
                durl + "/empresas/"
                        + edCodigoEmpresa.getText().toString() + ".json",
                RestFullHelper.GET, params);
        bgtGet.execute();
    }

    private void postInformationtoAPI() {
        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edNomeEmpresa.getText().toString());
            params.put("cnpj", edCnpj.getText().toString());
            params.put("telefone", edTelefone.getText().toString());
            params.put("endereco", edEndereco.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FabricanteTask bgtPost = new FabricanteTask(
                durl + "/empresas.json", RestFullHelper.POST, params);
        bgtPost.execute();

    }

    private void putInformationtoAPI() {
        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edNomeEmpresa.getText().toString());
            params.put("cnpj", edCnpj.getText().toString());
            params.put("telefone", edTelefone.getText().toString());
            params.put("endereco", edEndereco.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FabricanteTask bgtPut = new FabricanteTask(
                durl + "/empresas/"
                        + edCodigoEmpresa.getText().toString() + ".json",
                RestFullHelper.PUT, params);
        bgtPut.execute();
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

    public class FabricanteTask extends AsyncTask<String, String, JSONObject> {

        String url = null;
        String method = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public FabricanteTask(String url, String method, JSONObject params1) {
            this.url = url;
            this.method = method;
            this.params1 = params1;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CadastroEmpresaTela.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject professor) {

            if (professor != null) {
                try {
                    edCodigoEmpresa.setText(professor.getString("id"));
                    edNomeEmpresa.setText(professor.getString("nome"));
                    edCnpj.setText(professor.getString("cnpj"));
                    edTelefone.setText(professor.getString("telefone"));
                    edEndereco.setText(professor.getString("endereco"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();
            return http.getJSON(url, method, params1);
        }
    }
}
