package com.example.warren.gsb.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by warren on 23/04/15, GSB
 */
public class CreateBDD extends SQLiteOpenHelper {
    static final String COL_ID = "MED_DEPOTLEGAL";
    static final String COL_CODE = "TYPE_CODE";
    private static final String TABLE_MEDICAMENTS = "MEDICAMENT";
    private static final String COL_NOM = "MED_NOMCOMMERCIAL";
    private static final String COL_COMP = "MED_COMPOSITION";
    private static final String COL_EFFET = "MED_EFFETS";
    private static final String COL_CONTRE = "MED_CONTREINDIC";
    private static final String COL_PRIX = "MED_PRIXECHANTILLON";

    private static final String TABLE_TYPE = "TYPE_INDIVIDU";
    private static final String COL_LIBELLE = "TYPE_LIBELLE";
    private static final String COL_MIN = "TYPE_AGEMIN";
    private static final String COL_MAX = "TYPE_AGEMAX";

    private static final String TABLE_DOSAGE = "DOSAGE";
    private static final String COL_QTE = "D_QUANTIE";
    private static final String COL_UNITE = "D_UNITE";
    private static final String COL_DUREE = "D_DUREE";

    private static final String CREATE_BDD =
            "CREATE TABLE " + TABLE_MEDICAMENTS + " (" + COL_ID + " TEXT(256) PRIMARY KEY,"
                    + COL_NOM + " TEXT(256) NOT NULL, " + COL_COMP + " TEXT(256) NOT NULL, "
                    + COL_EFFET + " TEXT(256) NOT NULL," + COL_CONTRE + " TEXT(256) NOT NULL," + COL_PRIX + " TEXT(256) NOT NULL);"

                    + "CREATE TABLE " + TABLE_TYPE + " (" + COL_CODE + " TEXT(256) PRIMARY KEY,"
                    + COL_LIBELLE + " TEXT(256) NOT NULL," + COL_MIN + "TEXT(256) NOT NULL," + COL_MAX + "TEXT(256) NOT NULL);"

                    + "CREATE TABLE " + TABLE_DOSAGE + "(" + COL_QTE + "TEXT(256) NOT NULL," + COL_UNITE + "TEXT(256) NOT NULL,"
                    + COL_DUREE + "TEXT(256) NOT NULL);";

    public CreateBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
        System.out.println("ON CREATE BD ARTICLE");

    }

    @Override // appelée si la version de la base a changé
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut  supprimer la table et de la recréer
        db.execSQL("DROP TABLE " + TABLE_MEDICAMENTS + ";");
        onCreate(db);
    }
}
