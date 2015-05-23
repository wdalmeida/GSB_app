package com.example.warren.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.warren.gsb.bdd.AdaptaterBDD;
import com.example.warren.gsb.bdd.Dosage;


public class Ajout_medoc2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_medoc2);
        Intent intent = getIntent();
        String info = intent.getStringExtra("Medicament");
        String[] split = info.split("/");
        TextView nom = (TextView) findViewById(R.id.TextNom);
        nom.setText(split[1]);
        chargerSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajout_medoc2, menu);
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

    public void chargerSpinner() {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_next);
        Cursor c = bdd.getAllIndividu();

        String[] from = new String[]{AdaptaterBDD.COL_LIBELLE};
        int[] to = new int[]{R.id.nom_medoc};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.row_spinner_medoc, c, from, to, 1);

        // Assign adapter to spinne
        spinner.setAdapter(dataAdapter);
        bdd.close();
    }

    public void addDosage(View view) {
        Intent intent = getIntent();
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        EditText qte = (EditText) findViewById(R.id.editText);
        EditText unite = (EditText) findViewById(R.id.editText2);
        EditText duree = (EditText) findViewById(R.id.editText3);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_next);
        Cursor cursor = (Cursor) spinner.getSelectedItem();
        String id_indiv = cursor.getString(cursor.getColumnIndex(AdaptaterBDD.COL_CODE));

        String info = intent.getStringExtra("Medicament");
        String[] split = info.split("/");
        String id_medoc = split[0];
        Dosage leDosage = new Dosage(id_medoc, id_indiv, qte.getText().toString(), unite.getText().toString(), duree.getText().toString());
        bdd.insererDosage(leDosage);
        bdd.close();
    }
}
