package com.example.warren.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warren.gsb.bdd.AdaptaterBDD;


public class Liste_medoc extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_medoc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        ListView listViewMedicament = (ListView) findViewById(R.id.listMedoc);

        AdaptaterBDD bdd = new AdaptaterBDD(this);
        //On ouvre la base de données
        bdd.open();
        Cursor c = bdd.getAllMedicament();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " articles dans la BDD", Toast.LENGTH_LONG).show();
        // colonnes à afficher
        String[] columns = new String[]{AdaptaterBDD.COL_NOM, AdaptaterBDD.COL_COMP, AdaptaterBDD.COL_CONTRE, AdaptaterBDD.COL_EFFET, AdaptaterBDD.COL_PRIX};
        // champs dans lesquelles afficher les colonnes
        int[] to = new int[]{R.id.MedocName, R.id.text_MedocCompo, R.id.text_MedocContre, R.id.text_MedocEffet, R.id.text_MedocPrix};
// this : contexte (notre activité)
// R.layout : vue
//c : cursor
//columns : nom des colonnes
//to : contrôles
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.un_medoc, c, columns, to, 1);
        dataAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == cursor.getColumnIndex(AdaptaterBDD.COL_PRIX)) {
                    String prix = cursor.getString(columnIndex);
                    TextView textView = (TextView) view;
                    textView.setText(prix + "€");
                    return true;
                }
                return false;
            }
        });
        // Assign adapter to ListView
        listViewMedicament.setAdapter(dataAdapter);
        bdd.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_liste_medoc, menu);
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
