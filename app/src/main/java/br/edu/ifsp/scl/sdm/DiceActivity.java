package br.edu.ifsp.scl.sdm;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class DiceActivity extends AppCompatActivity implements View.OnClickListener{

    // Random usado para simular o lançamento do dado
    private Random geradorRandomico;
    // Componentes visuais
    private TextView resultadoTextView;
    private Button jogarDadoButton;
    private Spinner numDadosSpinner;
    private ImageView resultadoImageView;
    private ImageView resultado2ImageView;
    private EditText numFacesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Jogando dados");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Após a criação da tela
        geradorRandomico = new Random(System.currentTimeMillis());
        // Recuperando referência para o resultadoTextView do arquivo de layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
        // Recuperando referência para o jogarDadoButton do arquivo de layout
        jogarDadoButton = findViewById(R.id.jogarDadoButton);
        jogarDadoButton.setOnClickListener(this);

        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);

        // Recuperando referência para o resultadoImageView do arquivo de layout
        resultadoImageView = findViewById(R.id.resultadoImageView);

        // Recuperando referência para o numFacesEditText do arquivo de layout
        numFacesEditText = findViewById(R.id.numFacesEditText);
    }

    /*  private void setImageResource(ImageView iv, int face) {
          switch (face) {
              case 1: iv.setImageResource(R.drawable.dice_1);
                  break;
              case 2: iv.setImageResource(R.drawable.dice_2);
                  break;
              case 3: iv.setImageResource(R.drawable.dice_3);
                  break;
              case 4: iv.setImageResource(R.drawable.dice_4);
                  break;
              case 5: iv.setImageResource(R.drawable.dice_5);
                  break;
              case 6: iv.setImageResource(R.drawable.dice_6);
                  break;
          }
      }
  */
    private void setImageResource(ImageView iv, int face) {
        // Em várias linhas para facilitar o entendimento
        String nomeRes = "dice_" + face;
        int idRes = getResources().getIdentifier(nomeRes, "drawable", getPackageName());
        iv.setImageResource(idRes);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.jogarDadoButton) {
            // Recuperando o número de dados selecionados
            int numDados =
                    Integer.parseInt(numDadosSpinner.getSelectedItem().toString());
            // String que armazena números sorteados
            String resultadoText = "Faces sorteadas: ";
            int numFaces;
            try {
                numFaces = Integer.parseInt(numFacesEditText.getText().toString());
            } catch (NumberFormatException e) {
                // Caso usuário não digite nenhum número de faces
                numFaces = 6;
            }
            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            }
            else {
                resultadoImageView.setVisibility(View.VISIBLE);
                // Visibilidade do resultado2ImageView de acordo com número de dados
                if (numDados == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                }
                else {
                    resultado2ImageView.setVisibility(View.GONE);
                    resultadoText = "Face sorteada: ";
                }
            }
            // Sorteando números de acordo com número de dados
            for (int i = 1; i <= numDados; i++) {
                int resultado = geradorRandomico.nextInt(numFaces) + 1;
                resultadoText += resultado + ", ";
                ImageView iv = (i == 1) ? resultadoImageView : resultado2ImageView;
                setImageResource(iv, resultado);
            }
            resultadoTextView.setText(resultadoText.substring(0,
                    resultadoText.lastIndexOf(',')));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      finish();
      return true;
    }
}
