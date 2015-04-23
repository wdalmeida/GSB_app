package com.example.warren.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.warren.gsb.bdd.AdaptaterBDD;
import com.example.warren.gsb.bdd.Medicament;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBd();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void showMedoc(View view) {
        Intent intent = new Intent(this, activity_liste_medoc.class);
        startActivity(intent);
    }

    public void testBd() {
        //Création d'une instance de la classe unArticlesBDD
        AdaptaterBDD bdd = new AdaptaterBDD(this);

        //Création d'un Article
        Medicament unMedicament = new Medicament("0", "TEST", "compo", "effet", "cotre", "30");

        //On ouvre la base de données pour écrire dedans
        bdd.open();
        //On insère l'article que l'on vient de créer
        bdd.insererMedicament(unMedicament);
        System.out.println("insertion article");
        System.out.println();
        //Pour vérifier que l'on a bien créé un Article dans la BDD
        //on extrait l’article de la BDD grâce à la désignation de l'article que l'on a créé précédemment
        Medicament unMedicamentBdd = bdd.getMedicamentByName("TEST");
        //Si un unArticle est retourné (donc si le unArticle à bien été ajouté à la BDD)
        if (unMedicamentBdd != null) {
            //On affiche les infos de l’Article dans un Toast
            Toast.makeText(this, unMedicamentBdd.getNom(), Toast.LENGTH_LONG).show();
            //On modifie le titre de l’Article
            unMedicamentBdd.setNom("TEST2");
            //Puis on met à jour la BDD
            bdd.updateMedicament(unMedicamentBdd.getComposition(), unMedicamentBdd);
        } else {
            Toast.makeText(this, "Article non trouvé", Toast.LENGTH_LONG).show();
        }

        //On extrait l’Article de la BDD grâce à sa nouvelle désignation
        unMedicamentBdd = bdd.getMedicamentByName("TEST");
        //S'il existe un Article possédant cette désignation dans la BDD
        if (unMedicamentBdd != null) {
            //On affiche les nouvelles info de l’Article pour vérifié que la désignation de l’Article a bien été maj
            Toast.makeText(this, unMedicamentBdd.getPrix(), Toast.LENGTH_LONG).show();
            //on supprime le unArticle de la BDD grâce à son ID
            //articleBdd.removeArticleWithRef(unArticleFromBdd.getReference());
        }


        //On essaie d'extraire de nouveau l’Article de la BDD toujours grâce à sa nouvelle désignation
        unMedicamentBdd = bdd.getMedicamentByName("TEST2");
        //Si aucun unArticle n'est retourné
        if (unMedicamentBdd == null) {
            //On affiche un message indiquant que l’Article n'existe pas dans la BDD
            Toast.makeText(this, "Cet article n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        } else {    //Si l'Article existe (mais normalement il ne devrait pas)
            //on affiche un message indiquant que l’Article existe dans la BDD
            Toast.makeText(this, "Cet article existe dans la BDD", Toast.LENGTH_LONG).show();
        }
        Cursor c = bdd.getData();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " articles dans la BD", Toast.LENGTH_LONG).show();
        bdd.close();
    }
}
