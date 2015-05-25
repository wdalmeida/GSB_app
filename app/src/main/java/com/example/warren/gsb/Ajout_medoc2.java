package com.example.warren.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView nom = (TextView) findViewById(R.id.textView2);
        nom.setText(split[1]);
        chargerSpinner();
        //TODO ajouter une ligne par défaut au spinner ex : selectionner
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajout_medoc2, menu);
        return true;
    }

    public void chargerSpinner() {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_next);
        Intent intent = getIntent();
        String info = intent.getStringExtra("Medicament");
        String[] split = info.split("/");
        Cursor c = bdd.getIndividuNonDosee(split[0]);

        String[] from = new String[]{AdaptaterBDD.COL_LIBELLE};
        int[] to = new int[]{R.id.nom_medoc};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.row_spinner, c, from, to, 1);

        // Assign adapter to spinne
        spinner.setAdapter(dataAdapter);
        bdd.close();
    }

    public void addDosage(View view) {
        if (!verifChamp()) {
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
            Toast.makeText(this, "Dosage enregistré", Toast.LENGTH_LONG).show();
            Cursor c = bdd.getIndividuNonDosee(split[0]);
            if (c.getCount() > 0) {
                chargerSpinner();
                bdd.close();
            } else {
                bdd.close();
                startActivity(new Intent(Ajout_medoc2.this, MainActivity.class));
            }
        }
    }

    public boolean verifChamp() {
        boolean verif = false;
        EditText qte = (EditText) findViewById(R.id.editText);
        EditText unite = (EditText) findViewById(R.id.editText2);
        EditText duree = (EditText) findViewById(R.id.editText3);
        //  List<EditText> editTextList = null;
        if (TextUtils.isEmpty(qte.getText().toString())) {
            qte.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(unite.getText().toString())) {
            unite.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(duree.getText().toString())) {
            duree.setError("Veuillez remplir ce champ");
            verif = true;
        }

        /*editTextList.add(id);
        editTextList.add(nom);
        editTextList.add(compo);
        editTextList.add(contre);
        editTextList.add(effet);
        editTextList.add(prix);
        for( EditText edit : editTextList) {
            if(TextUtils.isEmpty(edit.getText().toString())){
                edit.setError("Veuillez remplir ce champ");*/

        // }
        // }
        return verif;
    }
}
