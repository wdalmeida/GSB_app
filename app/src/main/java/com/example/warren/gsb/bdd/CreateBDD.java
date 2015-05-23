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

    private static final String CREATE_TABLE_MEDOC =
            "CREATE TABLE " + TABLE_MEDICAMENTS + " (" + COL_ID + " TEXT PRIMARY KEY, "
                    + COL_NOM + " TEXT NOT NULL, " + COL_COMP + " TEXT NOT NULL, "
                    + COL_EFFET + " TEXT NOT NULL, " + COL_CONTRE + " TEXT NOT NULL, " + COL_PRIX + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_INDIV =
            "CREATE TABLE " + TABLE_TYPE + " (" + COL_CODE + " TEXT PRIMARY KEY, "
                    + COL_LIBELLE + " TEXT NOT NULL, " + COL_MIN + " TEXT NOT NULL, " + COL_MAX + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_DOSA =
            "CREATE TABLE " + TABLE_DOSAGE + " (" + COL_ID + " TEXT NOT NULL, " + COL_CODE + " TEXT NOT NULL, "
                    + COL_QTE + " TEXT NOT NULL, " + COL_UNITE + " TEXT NOT NULL, " + COL_DUREE + " TEXT NOT NULL, PRIMARY KEY(" + COL_ID + "," + COL_CODE + "));";

    private static final String INFO_TYPE =
            "INSERT INTO " + TABLE_TYPE + " (" + COL_CODE + "," + COL_LIBELLE + "," + COL_MIN + "," + COL_MAX + ")" + " VALUES"
                    + "('JE','Enfant','0','17'),('AD','Adultes','18','59'),('SE','Senior','60','99');";

    private static final String INFO_MEDOC =
            "INSERT INTO " + TABLE_MEDICAMENTS + " (" + COL_ID + ", " + COL_NOM + ", " + COL_COMP + ", " + COL_EFFET + ", " + COL_CONTRE + ", " + COL_PRIX + ") VALUES"
                    + "('3MYC7', 'TRIMYCINE', 'Triamcinolone (acétonide) + Néomycine + Nystatine', 'Ce médicament est un corticoïde à  activité forte ou très forte associé à  un antibiotique et un antifongique, utilisé en application locale dans certaines atteintes cutanées surinfectées.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, d''infections de la peau ou de parasitisme non traités, d''acné. Ne pas appliquer sur une plaie, ni sous un pansement occlusif.', 18),"
                    + "('ADIMOL9', 'ADIMOL', 'Amoxicilline + Acide clavulanique', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines ou aux céphalosporines.', 44),"
                    + "('AMOPIL7', 'AMOPIL', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines. Il doit être administré avec prudence en cas d''allergie aux céphalosporines.', 2),"
                    + "('AMOX45', 'AMOXAR', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'La prise de ce médicament peut rendre positifs les tests de dépistage du dopage.', 93),"
                    + "('AMOXIG12', 'AMOXI Gé', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines. Il doit être administré avec prudence en cas d''allergie aux céphalosporines.', 36),"
                    + "('APATOUX22', 'APATOUX Vitamine C', 'Tyrothricine + Tétracaïne + Acide ascorbique (Vitamine C)', 'Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, en cas de phénylcétonurie et chez l''enfant de moins de 6 ans.', 88),"
                    + "('BACTIG10', 'BACTIGEL', 'Erythromycine', 'Ce médicament est utilisé en application locale pour traiter l''acné et les infections cutanées bactériennes associées.', 'Ce médicament est contre-indiqué en cas d''allergie aux antibiotiques de la famille des macrolides ou des lincosanides.', 14),"
                    + "('BACTIV13', 'BACTIVIL', 'Erythromycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', 11),"
                    + "('BITALV', 'BIVALIC', 'Dextropropoxyphène + Paracétamol', 'Ce médicament est utilisé pour traiter les douleurs d''intensité modérée ou intense.', 'Ce médicament est contre-indiqué en cas d''allergie aux médicaments de cette famille, d''insuffisance hépatique ou d''insuffisance rénale.', 26),"
                    + "('CARTION6', 'CARTION', 'Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol', 'Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre.', 'Ce médicament est contre-indiqué en cas de troubles de la coagulation (tendances aux hémorragies), d''ulcère gastroduodénal, maladies graves du foie.', 33),"
                    + "('CLAZER6', 'CLAZER', 'Clarithromycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques. Il est également utilisé dans le traitement de l''ulcère gastro-duodénal, en association avec d''autres médicaments.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', 4),"
                    + "('DEPRIL9', 'DEPRAMIL', 'Clomipramine', 'Ce médicament est utilisé pour traiter les épisodes dépressifs sévères, certaines douleurs rebelles, les troubles obsessionnels compulsifs et certaines énurésies chez l''enfant.', 'Ce médicament est contre-indiqué en cas de glaucome ou d''adénome de la prostate, d''infarctus récent, ou si vous avez reà§u un traitement par IMAO durant les 2 semaines précédentes ou en cas d''allergie aux antidépresseurs imipraminiques.', 99),"
                    + "('DIMIRTAM6', 'DIMIRTAM', 'Mirtazapine', 'Ce médicament est utilisé pour traiter les épisodes dépressifs sévères.', 'La prise de ce produit est contre-indiquée en cas de d''allergie à  l''un des constituants.', 25),"
                    + "('DOLRIL7', 'DOLORIL', 'Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol', 'Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre.', 'Ce médicament est contre-indiqué en cas d''allergie au paracétamol ou aux salicylates.', 20),"
                    + "('DORNOM8', 'NORMADOR', 'Doxylamine', 'Ce médicament est utilisé pour traiter l''insomnie chez l''adulte.', 'Ce médicament est contre-indiqué en cas de glaucome, de certains troubles urinaires (rétention urinaire) et chez l''enfant de moins de 15 ans.', 81),"
                    + "('EQUILARX6', 'EQUILAR', 'Méclozine', 'Ce médicament est utilisé pour traiter les vertiges et pour prévenir le mal des transports.', 'Ce médicament ne doit pas être utilisé en cas d''allergie au produit, en cas de glaucome ou de rétention urinaire.', 71),"
                    + "('EVILR7', 'EVEILLOR', 'Adrafinil', 'Ce médicament est utilisé pour traiter les troubles de la vigilance et certains symptomes neurologiques chez le sujet agé.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants.', 46),"
                    + "('INSXT5', 'INSECTIL', 'Diphénydramine', 'Ce médicament est utilisé en application locale sur les piqûres d''insecte et l''urticaire.', 'Ce médicament est contre-indiqué en cas d''allergie aux antihistaminiques.', 86),"
                    + "('JOVAI8', 'JOVENIL', 'Josamycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', 7),"
                    + "('LIDOXY23', 'LIDOXYTRACINE', 'Oxytétracycline +Lidocaïne', 'Ce médicament est utilisé en injection intramusculaire pour traiter certaines infections spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants. Il ne doit pas être associé aux rétinoïdes.', 15),"
                    + "('LITHOR12', 'LITHORINE', 'Lithium', 'Ce médicament est indiqué dans la prévention des psychoses maniaco-dépressives ou pour traiter les états maniaques.', 'Ce médicament ne doit pas être utilisé si vous êtes allergique au lithium. Avant de prendre ce traitement, signalez à  votre médecin traitant si vous souffrez d''insuffisance rénale, ou si vous avez un régime sans sel.', 9),"
                    + "('PARMOL16', 'PARMOCODEINE', 'Codéine + Paracétamol', 'Ce médicament est utilisé pour le traitement des douleurs lorsque des antalgiques simples ne sont pas assez efficaces.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, chez l''enfant de moins de 15 Kg, en cas d''insuffisance hépatique ou respiratoire, d''asthme, de phénylcétonurie et chez la femme qui allaite.', 85),"
                    + "('PHYSOI8', 'PHYSICOR', 'Sulbutiamine', 'Ce médicament est utilisé pour traiter les baisses d''activité physique ou psychique, souvent dans un contexte de dépression.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants.', 8),"
                    + "('PIRIZ8', 'PIRIZAN', 'Pyrazinamide', 'Ce médicament est utilisé, en association à  d''autres antibiotiques, pour traiter la tuberculose.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, d''insuffisance rénale ou hépatique, d''hyperuricémie ou de porphyrie.', 34),"
                    + "('POMDI20', 'POMADINE', 'Bacitracine', 'Ce médicament est utilisé pour traiter les infections oculaires de la surface de l''oeil.', 'Ce médicament est contre-indiqué en cas d''allergie aux antibiotiques appliqués localement.', 3),"
                    + "('TROXT21', 'TROXADET', 'Paroxétine', 'Ce médicament est utilisé pour traiter la dépression et les troubles obsessionnels compulsifs. Il peut également être utilisé en prévention des crises de panique avec ou sans agoraphobie.', 'Ce médicament est contre-indiqué en cas d''allergie au produit.', 51),"
                    + "('TXISOL22', 'TOUXISOL Vitamine C', 'Tyrothricine + Acide ascorbique (Vitamine C)', 'Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants et chez l''enfant de moins de 6 ans.', 92),"
                    + "('URIEG6', 'URIREGUL', 'Fosfomycine trométamol', 'Ce médicament est utilisé pour traiter les infections urinaires simples chez la femme de moins de 65 ans.', 'La prise de ce médicament est contre-indiquée en cas d''allergie à  l''un des constituants et d''insuffisance rénale.', 50);";


    private static final String INFO_DOSAGE =
            "INSERT INTO " + TABLE_DOSAGE + " (" + COL_ID + "," + COL_CODE + "," + COL_QTE + "," + COL_UNITE + "," + COL_DUREE + ")" + " VALUES"
                    + "('3MYC7','JE',1,200,30),"
                    + "('ADIMOL9','JE',2,100,45),"
                    + "('AMOPIL7','JE',3,1000,12),"
                    + "('AMOX45','JE',4,500,6),"
                    + "('AMOXIG12','JE',5,500,14),"
                    + "('APATOUX22','JE',6,500,23),"
                    + "('BACTIG10','JE',7,500,4),"
                    + "('BACTIV13','JE',8,200,5),"
                    + "('BITALV','JE',9,200,2),"
                    + "('CARTION6','JE',10,500,45),"
                    + "('CLAZER6','JE',1,100,90),"
                    + "('DEPRIL9','JE',2,100,3),"
                    + "('DIMIRTAM6','JE',3,500,9),"
                    + "('DOLRIL7','JE',4,1000,10),"
                    + "('DORNOM8','JE',5,1000,10),"
                    + "('EQUILARX6','JE',6,1000,15),"
                    + "('EVILR7','JE',7,500,15),"
                    + "('INSXT5','JE',8,50,7),"
                    + "('JOVAI8','JE',9,50,7),"
                    + "('LIDOXY23','JE',10,50,180),"
                    + "('LITHOR12','JE',11,500,20),"
                    + "('PARMOL16','JE',12,250,5),"
                    + "('PHYSOI8','JE',13,250,4),"
                    + "('PIRIZ8','JE',14,250,2),"
                    + "('POMDI20','JE',15,500,3),"
                    + "('TROXT21','JE',16,750,4),"
                    + "('TXISOL22','JE',17,750,8),"
                    + "('URIEG6','JE',18,750,7),"
                    + "('3MYC7','AD',1,400,30),"
                    + "('ADIMOL9','AD',2,200,45),"
                    + "('AMOPIL7','AD',3,2000,12),"
                    + "('AMOX45','AD',4,1000,6),"
                    + "('AMOXIG12','AD',5,1000,14),"
                    + "('APATOUX22','AD',6,1000,23),"
                    + "('BACTIG10','AD',7,1000,4),"
                    + "('BACTIV13','AD',8,400,5),"
                    + "('BITALV','AD',9,400,2),"
                    + "('CARTION6','AD',10,1000,45),"
                    + "('CLAZER6','AD',1,200,90),"
                    + "('DEPRIL9','AD',2,200,3),"
                    + "('DIMIRTAM6','AD',3,1000,9),"
                    + "('DOLRIL7','AD',4,2000,10),"
                    + "('DORNOM8','AD',5,2000,10),"
                    + "('EQUILARX6','AD',6,2000,15),"
                    + "('EVILR7','AD',7,1000,15),"
                    + "('INSXT5','AD',8,100,7),"
                    + "('JOVAI8','AD',9,100,7),"
                    + "('LIDOXY23','AD',10,100,180),"
                    + "('LITHOR12','AD',11,1000,20),"
                    + "('PARMOL16','AD',12,500,5),"
                    + "('PHYSOI8','AD',13,500,4),"
                    + "('PIRIZ8','AD',14,500,2),"
                    + "('POMDI20','AD',15,500,3),"
                    + "('TROXT21','AD',16,1500,4),"
                    + "('TXISOL22','AD',17,1500,8),"
                    + "('URIEG6','AD',18,1500,7),"
                    + "('3MYC7','SE',2,200,30),"
                    + "('ADIMOL9','SE',4,100,45),"
                    + "('AMOPIL7','SE',6,1000,12),"
                    + "('AMOX45','SE',8,500,6),"
                    + "('AMOXIG12','SE',10,500,14),"
                    + "('APATOUX22','SE',12,500,23),"
                    + "('BACTIG10','SE',14,500,4),"
                    + "('BACTIV13','SE',16,200,5),"
                    + "('BITALV','SE',18,200,2),"
                    + "('CARTION6','SE',20,500,45),"
                    + "('CLAZER6','SE',2,100,90),"
                    + "('DEPRIL9','SE',4,100,3),"
                    + "('DIMIRTAM6','SE',6,500,9),"
                    + "('DOLRIL7','SE',8,1000,10),"
                    + "('DORNOM8','SE',10,1000,10),"
                    + "('EQUILARX6','SE',12,1000,15),"
                    + "('EVILR7','SE',14,500,15),"
                    + "('INSXT5','SE',16,50,7),"
                    + "('JOVAI8','SE',18,50,7),"
                    + "('LIDOXY23','SE',20,50,180),"
                    + "('LITHOR12','SE',22,500,20),"
                    + "('PARMOL16','SE',24,250,5),"
                    + "('PHYSOI8','SE',26,250,4),"
                    + "('PIRIZ8','SE',28,250,2),"
                    + "('POMDI20','SE',30,500,3),"
                    + "('TROXT21','SE',32,750,4),"
                    + "('TXISOL22','SE',34,750,8),"
                    + "('URIEG6','SE',36,750,7);";

    public CreateBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE_MEDOC);
        db.execSQL(CREATE_TABLE_INDIV);
        db.execSQL(CREATE_TABLE_DOSA);

        db.execSQL(INFO_MEDOC);
        db.execSQL(INFO_TYPE);
        db.execSQL(INFO_DOSAGE);
        System.out.println("ON CREATE BD ARTICLE");

    }

    @Override // appelée si la version de la base a changé
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut  supprimer la table et de la recréer
        db.execSQL("DROP TABLE " + TABLE_MEDICAMENTS + ";");
        onCreate(db);
    }
}
