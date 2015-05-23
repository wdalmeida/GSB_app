package com.example.warren.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.warren.gsb.bdd.AdaptaterBDD;


public class Voir_dosage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_dosage);
        Intent intent = getIntent();
        String idMedoc = intent.getStringExtra("Medoc");
        String idIndiv = intent.getStringExtra("Indiv");

        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        Cursor c = bdd.getUnDosage(idMedoc, idIndiv);
        c.moveToFirst();
        TextView textView = (TextView) findViewById(R.id.text_dos);
        textView.setText("Le dosage de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_NOM))
                + " pour un " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_LIBELLE)).toLowerCase()
                + " est de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_QTE))
                + " comprimés de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_UNITE)) + " grammes"
                + " par jour pendant " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_DUREE)) + " jours");
        textView.setGravity(Gravity.CENTER);
        bdd.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voir_dosage, menu);
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
}
