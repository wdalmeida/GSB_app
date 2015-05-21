package com.example.warren.gsb;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warren.gsb.bdd.AdaptaterBDD;
import com.example.warren.gsb.bdd.Medicament;


public class ajout_medoc extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_medoc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajout_medoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void add(View view) {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        EditText id = (EditText) findViewById(R.id.edit_medoc_id);
        EditText nom = (EditText) findViewById(R.id.edit_medoc_nom);
        EditText compo = (EditText) findViewById(R.id.edit_medoc_compo);
        EditText contre = (EditText) findViewById(R.id.edit_medoc_contre);
        EditText effet = (EditText) findViewById(R.id.edit_medoc_effet);
        EditText prix = (EditText) findViewById(R.id.edit_medoc_prix);
        Medicament leMedoc = new Medicament(id.getText().toString(), nom.getText().toString(), compo.getText().toString(),
                contre.getText().toString(), effet.getText().toString(), prix.getText().toString());
        bdd.insererMedicament(leMedoc);
        Toast.makeText(this, "Médicament :" + leMedoc.getNom() + " ajouté", Toast.LENGTH_LONG).show();
    }
}
