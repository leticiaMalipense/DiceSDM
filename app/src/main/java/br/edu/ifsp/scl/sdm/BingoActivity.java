package br.edu.ifsp.scl.sdm;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BingoActivity extends AppCompatActivity implements View.OnClickListener {
    private Random geradorRandomico;
    private EditText qtdNumEditText;
    private TextView sorteadoTextView;
    private TextView todosSorteadosTextView;
    private List<Integer> listaSorteados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Jogando bingo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaSorteados = new ArrayList<>();
        geradorRandomico = new Random(System.currentTimeMillis());
        qtdNumEditText = findViewById(R.id.edtNum);
        sorteadoTextView = findViewById(R.id.txtSorteado);

        todosSorteadosTextView = findViewById(R.id.txtTodosSorteados);

   }

    @Override
    public void onClick(View view) {
       if (view.getId() == R.id.sortearNum) {
           sortearNumero();
       }
        if (view.getId() == R.id.limpar) {
            limparCampos();
        }

    }

    private void sortearNumero() {
        if(qtdNumEditText.getText() == null || qtdNumEditText.getText().length() == 0){
            qtdNumEditText.setError("Por favor preencha o campo");
        } else {

            int qtdNumeros = Integer.parseInt(qtdNumEditText.getText().toString());
            if (listaSorteados.size() == qtdNumeros) {
                Toast.makeText(getApplicationContext(), "Todos os numeros já foram sorteados", Toast.LENGTH_LONG).show();
            } else {
                addicionarAosSorteados(gerarNumeroRandomico(qtdNumeros));
            }
        }
    }

    private Integer gerarNumeroRandomico(Integer qtdNumeros){
        boolean procurar = true;

        while(procurar && listaSorteados.size() < qtdNumeros){
            int resultado = geradorRandomico.nextInt(qtdNumeros) + 1;
            if(listaSorteados.contains(resultado)) {
                procurar = true;
            }else{
                return resultado;
            }
        }
        return 0;
    }

    private void addicionarAosSorteados(Integer resultado){
        sorteadoTextView.setText(String.valueOf(resultado));
        listaSorteados.add(resultado);

        if(todosSorteadosTextView.getText().length() == 0){
            todosSorteadosTextView.setText(String.valueOf(resultado));
        }else{
            todosSorteadosTextView.setText(resultado + ", " + todosSorteadosTextView.getText());
        }

    }

    private void limparCampos() {
        todosSorteadosTextView.setText("");
        sorteadoTextView.setText("");
        listaSorteados.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
