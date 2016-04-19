package br.edu.iff.pooa20152.buscampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PrincipalTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_tela);
    }

    public void cadastrarOnibusOnClick (View v){
        startActivity(new Intent(this, CadastroOnibusTela.class));
    }

    public void cadastrarEmpresaOnClick (View v){
        startActivity(new Intent(this, CadastroEmpresaTela.class));
    }

    public void cadastrarHorarioOnClick (View v){
        startActivity(new Intent(this, CadastroHorarioTela.class));
    }

    public void cadastrarLinhaOnClick (View v){
        startActivity(new Intent(this, CadastroLinhaTela.class));
    }

    public void cadastrarPontoOnClick (View v){
        startActivity(new Intent(this, CadastroPontoTela.class));
    }

    public void sairAppOnClick (View v){
        finish();
    }
}
