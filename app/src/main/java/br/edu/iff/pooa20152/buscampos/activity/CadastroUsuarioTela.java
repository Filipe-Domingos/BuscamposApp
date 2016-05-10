package br.edu.iff.pooa20152.buscampos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.edu.iff.pooa20152.buscampos.R;

public class CadastroUsuarioTela extends AppCompatActivity {

    protected static final String CICLO = "ciclo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario_tela);
        Log.i(CICLO, "Testando Log");

        //Seleção Sexo
        Spinner spinner = (Spinner) findViewById(R.id.sexo_spinner);
        // Criar um ArrayAdapter usando a matriz de cadeia e um layout padrão girador
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);
        // Especifique o layout a ser usado quando a lista de opções aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplique o adaptador para o spinner
        spinner.setAdapter(adapter);
    }
}