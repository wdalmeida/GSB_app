package com.example.warren.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.example.warren.gsb.bdd.AdaptaterBDD;


public class Form_dosage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_dosage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        spinnerMedoc(bdd);
        spinnerIndiv(bdd);
        bdd.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dosage, menu);
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

    public void spinnerMedoc(AdaptaterBDD bdd) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_medoc);
        Cursor c = bdd.getAllMedicament();

        String[] from = new String[]{AdaptaterBDD.COL_NOM};
        int[] to = new int[]{R.id.nom_medoc};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.row_spinner_medoc, c, from, to, 1);
        // Assign adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void spinnerIndiv(AdaptaterBDD bdd) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_indiv);
        Cursor c = bdd.getAllIndividu();

        String[] from = new String[]{AdaptaterBDD.COL_LIBELLE};
        int[] to = new int[]{R.id.nom_medoc};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.row_spinner_medoc, c, from, to, 1);
        // Assign adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void voirDosage() {
        Intent intent = new Intent(this, Voir_dosage.class);
        startActivity(intent);

    }
}
