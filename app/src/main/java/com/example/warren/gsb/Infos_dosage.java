package com.example.warren.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.example.warren.gsb.bdd.AdaptaterBDD;


public class Infos_dosage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_dosage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        Cursor c = bdd.getAllIndividu();
        c.moveToFirst();
        TextView textView = (TextView) findViewById(R.id.indiv1);
        textView.setText(c.getString(c.getColumnIndex(AdaptaterBDD.COL_LIBELLE))
                + " de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MIN))
                + " à " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MAX))
                + " ans.");
        c.moveToNext();
        TextView textView2 = (TextView) findViewById(R.id.indiv2);
        textView2.setText(c.getString(c.getColumnIndex(AdaptaterBDD.COL_LIBELLE))
                + " de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MIN))
                + " à " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MAX))
                + " ans.");
        c.moveToNext();
        TextView textView3 = (TextView) findViewById(R.id.indiv3);
        textView3.setText(c.getString(c.getColumnIndex(AdaptaterBDD.COL_LIBELLE))
                + " de " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MIN))
                + " à " + c.getString(c.getColumnIndex(AdaptaterBDD.COL_MAX))
                + " ans.");
        c.close();
        bdd.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_infos_dosage, menu);
        return true;
    }
}
