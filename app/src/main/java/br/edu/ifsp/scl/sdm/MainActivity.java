package br.edu.ifsp.scl.sdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
 /*       if (id == R.id.dice_settings) {
            Intent i = new Intent(getApplicationContext(), DiceActivity.class);
            startActivityForResult(i, 1);
            return true;
        }*/
        if (id == R.id.bingo_settings) {
            Intent i = new Intent(getApplicationContext(), BingoActivity.class);
            startActivityForResult(i, 1);
            return true;
        }
        return false;
    }
}
