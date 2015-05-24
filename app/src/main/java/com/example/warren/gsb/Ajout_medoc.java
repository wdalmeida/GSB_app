package com.example.warren.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warren.gsb.bdd.AdaptaterBDD;
import com.example.warren.gsb.bdd.Medicament;


public class Ajout_medoc extends ActionBarActivity {

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

    public void add(View view) {
        if (!verifChamp()) {
            EditText id = (EditText) findViewById(R.id.edit_medoc_id);
            EditText nom = (EditText) findViewById(R.id.edit_medoc_nom);
            EditText compo = (EditText) findViewById(R.id.edit_medoc_compo);
            EditText contre = (EditText) findViewById(R.id.edit_medoc_contre);
            EditText effet = (EditText) findViewById(R.id.edit_medoc_effet);
            EditText prix = (EditText) findViewById(R.id.edit_medoc_prix);
            AdaptaterBDD bdd = new AdaptaterBDD(this);
            bdd.open();
            Medicament leMedoc = new Medicament(id.getText().toString(), nom.getText().toString(), compo.getText().toString(),
                    contre.getText().toString(), effet.getText().toString(), prix.getText().toString());
            bdd.insererMedicament(leMedoc);
            Toast.makeText(this, "Médicament :" + leMedoc.getNom() + " ajouté", Toast.LENGTH_LONG).show();
            bdd.close();
        }
    }

    public void next(View view) {
        if (!verifChamp()) {
            EditText id = (EditText) findViewById(R.id.edit_medoc_id);
            EditText nom = (EditText) findViewById(R.id.edit_medoc_nom);
            add(view);
            Intent intent = new Intent(this, Ajout_medoc2.class);
            intent.putExtra("Medicament", id.getText() + "/" + nom.getText());
            startActivity(intent);
        }
    }

    public boolean verifChamp() {
        boolean verif = false;
        EditText id = (EditText) findViewById(R.id.edit_medoc_id);
        EditText nom = (EditText) findViewById(R.id.edit_medoc_nom);
        EditText compo = (EditText) findViewById(R.id.edit_medoc_compo);
        EditText contre = (EditText) findViewById(R.id.edit_medoc_contre);
        EditText effet = (EditText) findViewById(R.id.edit_medoc_effet);
        EditText prix = (EditText) findViewById(R.id.edit_medoc_prix);
        //  List<EditText> editTextList = null;
        if (TextUtils.isEmpty(id.getText().toString())) {
            id.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(nom.getText().toString())) {
            nom.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(compo.getText().toString())) {
            compo.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(contre.getText().toString())) {
            contre.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(effet.getText().toString())) {
            effet.setError("Veuillez remplir ce champ");
            verif = true;
        }
        if (TextUtils.isEmpty(prix.getText().toString())) {
            prix.setError("Veuillez remplir ce champ");
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
