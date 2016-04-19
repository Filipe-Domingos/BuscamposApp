package br.edu.iff.pooa20152.buscampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CadastroUsuarioTela extends AppCompatActivity {

    protected static final String CICLO = "ciclo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario_tela);
        Log.i(CICLO, "Testando Log");
    }
}
