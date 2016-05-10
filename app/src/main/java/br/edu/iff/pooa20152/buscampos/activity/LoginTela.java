package br.edu.iff.pooa20152.buscampos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20152.buscampos.R;

public class LoginTela extends AppCompatActivity {

    private static final String MOSTRAR_SENHA = "mostrar_senha";
    private EditText edEmail;
    private EditText edSenha;
    private CheckBox cbMostrar;
    private Button btLimpar;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tela);

        edEmail = (EditText) findViewById(R.id.edEmail);
        edSenha = (EditText) findViewById(R.id.edSenha);
        cbMostrar = (CheckBox) findViewById(R.id.cbManter);

        //Mostrar a Senha oculta.
        cbMostrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                    edSenha.setTransformationMethod(new PasswordTransformationMethod());
                else
                    edSenha.setTransformationMethod(null);

            }
        });
    }

    public void entrarOnClick(View v) {

        String nomeInformado = edEmail.getText().toString();
        String senhaInformada = edSenha.getText().toString();

        if ("admin".equals(nomeInformado) && "123".equals(senhaInformada)) {
            SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.commit();
            startActivity(new Intent(this,PrincipalTela.class));

            String mensagemConectado = getString(R.string.conectadoLogin);
            Toast toast = Toast.makeText(this, mensagemConectado, Toast.LENGTH_SHORT);
            toast.show();
            finish();

        } else {
            String mensagemErro = getString(R.string.erroLogin);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void cadastrarUsuarioOnClick (View v){
        startActivity(new Intent(this, CadastroUsuarioTela.class));
    }

    public void limparOnClick (View v){

        edEmail.setText("");
        edSenha.setText("");
    }
}
