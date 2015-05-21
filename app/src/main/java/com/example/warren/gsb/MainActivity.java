package com.example.warren.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.warren.gsb.bdd.AdaptaterBDD;
import com.example.warren.gsb.bdd.Medicament;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!testBd())
        remplirBDD();
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
        Intent intent = new Intent(this, liste_medoc.class);
        startActivity(intent);
    }

    public void addMedoc(View view) {
        Intent intent = new Intent(this, ajout_medoc.class);
        startActivity(intent);
    }

    public boolean testBd() {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        bdd.open();
        boolean res = false;
        if (bdd.getAllMedicament().getCount() > 0) {
            res = true;
        }
        bdd.close();
        return res;
    }

    public void remplirBDD() {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        //génération automatique du prix à la création
        int lower = 1;
        int higher = 100;

        String[] tab = new String[20];
        tab[0] = "3MYC7/TRIMYCINE/Triamcinolone (acétonide) + Néomycine + Nystatine/Ce médicament est un corticoïde à  activité forte ou très forte associé à  un antibiotique et un antifongique, utilisé en application locale dans certaines atteintes cutanées surinfectées./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, d'infections de la peau ou de parasitisme non traités, d'acné. Ne pas appliquer sur une plaie, ni sous un pansement occlusif./NULL";
        tab[1] = "ADIMOL9/ADIMOL/Amoxicilline + Acide clavulanique/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines ou aux céphalosporines./NULL";
        tab[2] = "AMOPIL7/AMOPIL/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./NULL";
        tab[3] = "AMOX45/AMOXAR/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./La prise de ce médicament peut rendre positifs les tests de dépistage du dopage./NULL";
        tab[4] = "AMOXIG12/AMOXI Gé/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./NULL";
        tab[5] = "APATOUX22/APATOUX Vitamine C/Tyrothricine + Tétracaïne + Acide ascorbique (Vitamine C)Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, en cas de phénylcétonurie et chez l'enfant de moins de 6 ans./NULL";
        tab[6] = "BACTIG10/BACTIGEL/Erythromycine/Ce médicament est utilisé en application locale pour traiter lacné et les infections cutanées bactériennes associées./Ce médicament est contre-indiqué en cas d'allergie aux antibiotiques de la famille des macrolides ou des lincosanides./NULL";
        tab[7] = "BACTIV13/BACTIVIL/Erythromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL";
        tab[8] = "BITALV/BIVALIC/Dextropropoxyphène + Paracétamol/Ce médicament est utilisé pour traiter les douleurs dintensité modérée ou intense./Ce médicament est contre-indiqué en cas d'allergie aux médicaments de cette famille, d'insuffisance hépatique ou d'insuffisance rénale./NULL";
        tab[9] = "CARTION6/CARTION/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol,Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas de troubles de la coagulation (tendances aux hémorragies), dulcère gastroduodénal, maladies graves du foie./NULL";
        tab[10] = "CLAZER6/CLAZER/Clarithromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques. Il est également utilisé dans le traitement de lulcère gastro-duodénal, en association avec d'autres médicaments./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL";
        tab[11] = "DEPRIL9/DEPRAMIL/Clomipramine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères, certaines douleurs rebelles, les troubles obsessionnels compulsifs et certaines énurésies chez l'enfant./Ce médicament est contre-indiqué en cas de glaucome ou dadénome de la prostate, d'infarctus récent, ou si vous avez reà§u un traitement par IMAO durant les 2 semaines précédentes ou en cas d'allergie aux antidépresseurs imipraminiques./NULL";
        tab[12] = "DIMIRTAM6/DIMIRTAM/Mirtazapine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères./La prise de ce produit est contre-indiquée en cas de d'allergie à  l'un des constituants./NULL";
        tab[13] = "DOLRIL7/DOLORIL/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol/Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas d'allergie au paracétamol ou aux salicylates./NULL";
        tab[14] = "DORNOM8/NORMADOR/Doxylamine/Ce médicament est utilisé pour traiter l'insomnie chez ladulte./Ce médicament est contre-indiqué en cas de glaucome, de certains troubles urinaires (rétention urinaire) et chez l'enfant de moins de 15 ans./NULL";
        tab[15] = "EQUILARX6/EQUILAR/Méclozine/Ce médicament est utilisé pour traiter les vertiges et pour prévenir le mal des transports./Ce médicament ne doit pas être utilisé en cas d'allergie au produit, en cas de glaucome ou de rétention urinaire./NULL";
        tab[16] = "EVILR7/EVEILLOR/Adrafinil/Ce médicament est utilisé pour traiter les troubles de la vigilance et certains symptomes neurologiques chez le sujet agé./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants./NULL";
        tab[17] = "INSXT5/INSECTIL/Diphénydramine/Ce médicament est utilisé en application locale sur les piqûres dinsecte et lurticaire./Ce médicament est contre-indiqué en cas d'allergie aux antihistaminiques./NULL";
        tab[18] = "JOVAI8/JOVENIL/Josamycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL";
        tab[19] = "LIDOXY23/LIDOXYTRACINE/Oxytétracycline +Lidocaïne/Ce médicament est utilisé en injection intramusculaire pour traiter certaines infections spécifiques./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants. Il ne doit pas être associé aux rétinoïdes./NULL";
        //On ouvre la base de données pour écrire dedans
        bdd.open();
        //On crée les médicaments et on les insérent
        for (int y = 0; y <= 19; y++) {
            String[] laLigne = tab[y].split("/");
            int random = (int) (Math.random() * (higher - lower)) + lower;
            Medicament unMedicament = new Medicament(laLigne[0] + "", laLigne[1], laLigne[2], laLigne[3], laLigne[4], Integer.toString(random));
            bdd.insererMedicament(unMedicament);
        }
        bdd.close();
    }
}
