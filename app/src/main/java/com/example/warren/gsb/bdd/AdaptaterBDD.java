package com.example.warren.gsb.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by warren on 23/04/15, GSB
 */
public class AdaptaterBDD {

    public static final String COL_ID = "MED_DEPOTLEGAL";
    public static final String COL_NOM = "MED_NOMCOMMERCIAL";
    public static final String COL_COMP = "MED_COMPOSITION";
    public static final String COL_EFFET = "MED_EFFETS";
    public static final String COL_CONTRE = "MED_CONTREINDIC";
    public static final String COL_PRIX = "MED_PRIXECHANTILLON";

    public static final String COL_CODE = "TYPE_CODE";
    public static final String COL_LIBELLE = "TYPE_LIBELLE";
    public static final String COL_MIN = "TYPE_AGEMIN";
    public static final String COL_MAX = "TYPE_AGEMAX";

    public static final String COL_QTE = "D_QUANTIE";
    public static final String COL_UNITE = "D_UNITE";
    public static final String COL_DUREE = "D_DUREE";
    public static final String TABLE_DOSAGE = "DOSAGE";
    static final String TABLE_MEDICAMENT = "MEDICAMENT";
    static final int NUM_COL_ID = 0;
    static final int NUM_COL_NOM = 1;
    static final int NUM_COL_COMP = 2;
    static final int NUM_COL_EFFET = 3;
    static final int NUM_COL_CONTRE = 4;
    static final int NUM_COL_PRIX = 5;
    static final String TABLE_INDIVIDU = "TYPE_INDIVIDU";
    static final int NUM_COL_CODE = 0;
    static final int NUM_COL_LIBELLE = 1;
    static final int NUM_COL_MIN = 2;
    static final int NUM_COL_MAX = 3;
    static final int NUM_COL_QTE = 2;
    static final int NUM_COL_UNITE = 3;
    static final int NUM_COL_DUREE = 4;

    static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "GSB.db";
    protected Context context;
    private CreateBDD bdMedoc;
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

    public long insererDosage(Dosage unDosage) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_ID, unDosage.getId_medoc());
        values.put(COL_CODE, unDosage.getCode_indiv());
        values.put(COL_QTE, unDosage.getQuantite());
        values.put(COL_UNITE, unDosage.getUnité());
        values.put(COL_DUREE, unDosage.getDurée());

        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_DOSAGE, null, values);
    }

    private Medicament cursorToMedicament(Cursor c) {
        //Cette méthode permet de convertir un cursor en un article
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

    public Cursor getAllMedicament() {
        return db.rawQuery("SELECT rowid _id, * FROM " + TABLE_MEDICAMENT, null);
    }

    public Cursor getAllIndividu() {
        return db.rawQuery("SELECT rowid _id, * FROM " + TABLE_INDIVIDU, null);
    }

    public Cursor getIndividuNonDosee(String medoc) {
        return db.rawQuery("SELECT rowid _id, " + COL_LIBELLE + ", " + COL_CODE
                + " FROM " + TABLE_INDIVIDU
                + " WHERE " + COL_CODE + " NOT IN ( SELECT " + COL_CODE + " FROM " + TABLE_DOSAGE
                + " WHERE " + COL_ID + "='" + medoc + "');", null);
    }

    public Cursor getUnDosage(String idMedoc, String idIndiv) {
        return db.rawQuery("SELECT m." + COL_NOM + ", i." + COL_LIBELLE
                + ", " + COL_QTE + ", " + COL_UNITE + ", " + COL_DUREE
                + " FROM " + TABLE_DOSAGE + " d"
                + " JOIN " + TABLE_MEDICAMENT + " m ON m." + COL_ID + "=d." + COL_ID
                + " JOIN " + TABLE_INDIVIDU + " i ON i." + COL_CODE + "=d." + COL_CODE
                + " WHERE m." + COL_ID + "='" + idMedoc + "' AND d." + COL_CODE + "='" + idIndiv + "'", null);
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

}
