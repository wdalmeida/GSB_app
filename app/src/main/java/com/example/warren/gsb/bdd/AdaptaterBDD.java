package com.example.warren.gsb.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by warren on 23/04/15, GSB
 */
public class AdaptaterBDD {

    static final int VERSION_BDD = 1;
    static final String TABLE_MEDICAMENT = "MEDICAMENT";
    static final String COL_ID = "MED_DEPOTLEGAL";
    static final int NUM_COL_ID = 0;
    static final String COL_NOM = "MED_NOMCOMMERCIAL";
    static final int NUM_COL_NOM = 1;
    static final String COL_COMP = "MED_COMPOSITION";
    static final int NUM_COL_COMP = 2;
    static final String COL_EFFET = "MED_EFFETS";
    static final int NUM_COL_EFFET = 3;
    static final String COL_CONTRE = "MED_CONTREINDIC";
    static final int NUM_COL_CONTRE = 4;
    static final String COL_PRIX = "MED_PRIXECHANTILLON";
    static final int NUM_COL_PRIX = 5;
    private static final String NOM_BDD = "MEDICAMENT.db";
    private CreateBDD bdMedoc;
    private Context context;
    private SQLiteDatabase db;

    public AdaptaterBDD(Context context) {
        this.context = context;
        bdMedoc = new CreateBDD(context, NOM_BDD, null, VERSION_BDD);
    }

    //si la base n’existe pas, l’objet SQLiteOpenHelper exécute la méthode onCreate
    // si la version de la base a changé, la méthode onUpgrade sera lancée
    // dans les 2 cas l’appel à getWritableDatabase ou getReadableDatabase renverra  la base
    // de données en cache, nouvellement ouverte, nouvellement créée ou mise à jour
    public AdaptaterBDD open() {
        db = bdMedoc.getWritableDatabase();
        return this;
    }

    public AdaptaterBDD close() {
        db.close();
        return null;
    }

    public long insererMedicament(Medicament unMedoc) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_ID, unMedoc.getId());
        values.put(COL_NOM, unMedoc.getNom());
        values.put(COL_COMP, unMedoc.getComposition());
        values.put(COL_EFFET, unMedoc.getEffet());
        values.put(COL_CONTRE, unMedoc.getContreIndic());
        values.put(COL_PRIX, unMedoc.getPrix());
        System.out.println(unMedoc.nom);
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_MEDICAMENT, null, values);
    }

    private Medicament cursorToArticle(Cursor c) { //Cette méthode permet de convertir un cursor en un article
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst();    //on se place sur le premier élément
        Medicament unMedoc = new Medicament();    //On crée un article
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unMedoc.setId(c.getString(NUM_COL_ID));
        unMedoc.setNom(c.getString(NUM_COL_NOM));
        unMedoc.setComposition(c.getString(NUM_COL_COMP));
        unMedoc.setEffet(c.getString(NUM_COL_EFFET));
        unMedoc.setContreIndic(c.getString(NUM_COL_CONTRE));
        unMedoc.setPrix(c.getString(NUM_COL_PRIX));
        c.close();    //On ferme le cursor
        return unMedoc;    //On retourne l'article
    }

    public Medicament getMedicamentByName(String nom) {
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation)
        Cursor c = db.query(TABLE_MEDICAMENT, new String[]{COL_ID, COL_NOM, COL_COMP, COL_EFFET, COL_CONTRE, COL_PRIX}, COL_NOM + " LIKE \"" + nom + "\"", null, null, null, null);
        return cursorToArticle(c);
    }

    public int updateMedicament(String id, Medicament unMedicament) {
        //La mise à jour d'un article dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel article on doit mettre à jour grâce à sa référence
        ContentValues values = new ContentValues();
        values.put(COL_NOM, unMedicament.getNom());
        values.put(COL_PRIX, unMedicament.getPrix());
        values.put(COL_EFFET, unMedicament.getEffet());
        return db.update(TABLE_MEDICAMENT, values, COL_ID + " = \"" + id + "\"", null);
    }

    public int removeMedicamentById(String id) {
        //Suppression d'un article de la BDD grâce à sa référence
        return db.delete(TABLE_MEDICAMENT, COL_ID + " = \"" + id + "\"", null);
    }

    public Cursor getData() {
        return db.rawQuery("SELECT rowid _id, * FROM MEDICAMENT", null);
    }
}
