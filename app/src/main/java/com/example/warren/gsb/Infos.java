package com.example.warren.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.example.warren.gsb.bdd.AdaptaterBDD;


public class Infos extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();

        TextView prix = (TextView) findViewById(R.id.prixM);
        prix.setText("Prix moyen des médicaments : " + bdd.getPrixMoyen() + "€");
        Cursor cursor = bdd.getAllMedicament();
        TextView nb = (TextView) findViewById(R.id.nbMedoc);
        nb.setText("Nombres de médicament : " + cursor.getCount());
        bdd.close();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_infos, menu);
        return true;
    }
}
