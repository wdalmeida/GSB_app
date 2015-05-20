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

    public void remplirBDD() {
        AdaptaterBDD bdd = new AdaptaterBDD(this);
        /*String s1= "3MYC7/TRIMYCINE/Triamcinolone (acétonide) + Néomycine + Nystatine/Ce médicament est un corticoïde à  activité forte ou très forte associé à  un antibiotique et un antifongique, utilisé en application locale dans certaines atteintes cutanées surinfectées./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, d'infections de la peau ou de parasitisme non traités, d'acné. Ne pas appliquer sur une plaie, ni sous un pansement occlusif./NULL" +
                "ADIMOL9/ADIMOL/Amoxicilline + Acide clavulanique/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines ou aux céphalosporines./NULL"+
                "AMOPIL7/AMOPIL/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./NULL"+
                "AMOX45/AMOXAR/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./La prise de ce médicament peut rendre positifs les tests de dépistage du dopage./NULL"+
                "AMOXIG12/AMOXI Gé/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./NULL"+
                "APATOUX22/APATOUX Vitamine C/Tyrothricine + Tétracaïne + Acide ascorbique (Vitamine C)Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, en cas de phénylcétonurie et chez l'enfant de moins de 6 ans./NULL"+
                "BACTIG10/BACTIGEL/Erythromycine/Ce médicament est utilisé en application locale pour traiter lacné et les infections cutanées bactériennes associées./Ce médicament est contre-indiqué en cas d'allergie aux antibiotiques de la famille des macrolides ou des lincosanides./NULL"+
                "BACTIV13/BACTIVIL/Erythromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL"+
                "BITALV/BIVALIC/Dextropropoxyphène + Paracétamol/Ce médicament est utilisé pour traiter les douleurs dintensité modérée ou intense./Ce médicament est contre-indiqué en cas d'allergie aux médicaments de cette famille, d'insuffisance hépatique ou d'insuffisance rénale./NULL"+
                "CARTION6/CARTION/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol,Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas de troubles de la coagulation (tendances aux hémorragies), dulcère gastroduodénal, maladies graves du foie./NULL"+
                "CLAZER6/CLAZER/Clarithromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques. Il est également utilisé dans le traitement de lulcère gastro-duodénal, en association avec d'autres médicaments./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL"+
                "DEPRIL9/DEPRAMIL/Clomipramine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères, certaines douleurs rebelles, les troubles obsessionnels compulsifs et certaines énurésies chez l'enfant./Ce médicament est contre-indiqué en cas de glaucome ou dadénome de la prostate, d'infarctus récent, ou si vous avez reà§u un traitement par IMAO durant les 2 semaines précédentes ou en cas d'allergie aux antidépresseurs imipraminiques./NULL"+
                "DIMIRTAM6/DIMIRTAM/Mirtazapine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères./La prise de ce produit est contre-indiquée en cas de d'allergie à  l'un des constituants./NULL"+
                "DOLRIL7/DOLORIL/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol/Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas d'allergie au paracétamol ou aux salicylates./NULL"+
                "DORNOM8/NORMADOR/Doxylamine/Ce médicament est utilisé pour traiter l'insomnie chez ladulte./Ce médicament est contre-indiqué en cas de glaucome, de certains troubles urinaires (rétention urinaire) et chez l'enfant de moins de 15 ans./NULL"+
                "EQUILARX6/EQUILAR/Méclozine/Ce médicament est utilisé pour traiter les vertiges et pour prévenir le mal des transports./Ce médicament ne doit pas être utilisé en cas d'allergie au produit, en cas de glaucome ou de rétention urinaire./NULL"+
                "EVILR7/EVEILLOR/Adrafinil/Ce médicament est utilisé pour traiter les troubles de la vigilance et certains symptomes neurologiques chez le sujet agé./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants./NULL"+
                "INSXT5/INSECTIL/Diphénydramine/Ce médicament est utilisé en application locale sur les piqûres dinsecte et lurticaire./Ce médicament est contre-indiqué en cas d'allergie aux antihistaminiques./ NULL"+
                "JOVAI8/JOVENIL/Josamycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./NULL"+
                "LIDOXY23/LIDOXYTRACINE/Oxytétracycline +Lidocaïne/Ce médicament est utilisé en injection intramusculaire pour traiter certaines infections spécifiques./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants. Il ne doit pas être associé aux rétinoïdes./NULL"+
                "LITHOR12/LITHORINE/Lithium/Ce médicament est indiqué dans la prévention des psychoses maniaco-dépressives ou pour traiter les états maniaques./Ce médicament ne doit pas être utilisé si vous êtes allergique au lithium. Avant de prendre ce traitement, signalez à  votre médecin traitant si vous souffrez d'insuffisance rénale, ou si vous avez un régime sans sel./NULL"+
                "PARMOL16/PARMOCODEINE/Codéine + Paracétamol/Ce médicament est utilisé pour le traitement des douleurs lorsque des antalgiques simples ne sont pas assez efficaces./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, chez l'enfant de moins de 15 Kg, en cas dinsuffisance hépatique ou respiratoire, dasthme, de phénylcétonurie et chez la femme qui allaite./NULL"+
                "PHYSOI8/PHYSICOR/Sulbutiamine/Ce médicament est utilisé pour traiter les baisses d'activité physique ou psychique, souvent dans un contexte de dépression./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants./NULL"+
                "PIRIZ8/PIRIZAN/Pyrazinamide/Ce médicament est utilisé en association à  d'autres antibiotiques, pour traiter la tuberculose./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, d'insuffisance rénale ou hépatique, dhyperuricémie ou de porphyrie./NULL"+
                "POMDI20/POMADINE/Bacitracine/Ce médicament est utilisé pour traiter les infections oculaires de la surface de l'oeil./Ce médicament est contre-indiqué en cas d'allergie aux antibiotiques appliqués localement./NULL"+
                "TROXT21/TROXADET/Paroxétine/Ce médicament est utilisé pour traiter la dépression et les troubles obsessionnels compulsifs./Il peut également être utilisé en prévention des crises de panique avec ou sans agoraphobie./ Ce médicament est contre-indiqué en cas d'allergie au produit./NULL"+
                "TXISOL22/TOUXISOL Vitamine C/Tyrothricine + Acide ascorbique (Vitamine C)Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants et chez lenfant de moins de 6 ans./NULL"+
                "URIEG6/URIREGUL/Fosfomycine trométamol/Ce médicament est utilisé pour traiter les infections urinaires simples chez la femme de moins de 65 ans./La prise de ce médicament est contre-indiquée en cas d'allergie à l'un des constituants et dinsuffisance rénale./NULL";
        */
        int lower = 1;
        int higher = 100;

        int random = (int) (Math.random() * (higher - lower)) + lower;
        String[] tab = new String[20];
        tab[0] = "3MYC7/TRIMYCINE/Triamcinolone (acétonide) + Néomycine + Nystatine/Ce médicament est un corticoïde à  activité forte ou très forte associé à  un antibiotique et un antifongique, utilisé en application locale dans certaines atteintes cutanées surinfectées./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, d'infections de la peau ou de parasitisme non traités, d'acné. Ne pas appliquer sur une plaie, ni sous un pansement occlusif./" + random;
        tab[1] = "ADIMOL9/ADIMOL/Amoxicilline + Acide clavulanique/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines ou aux céphalosporines./" + random;
        tab[2] = "AMOPIL7/AMOPIL/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./" + random;
        tab[3] = "AMOX45/AMOXAR/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./La prise de ce médicament peut rendre positifs les tests de dépistage du dopage./" + random;
        tab[4] = "AMOXIG12/AMOXI Gé/Amoxicilline/Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux pénicillines. Il doit être administré avec prudence en cas d'allergie aux céphalosporines./" + random;
        tab[5] = "APATOUX22/APATOUX Vitamine C/Tyrothricine + Tétracaïne + Acide ascorbique (Vitamine C)Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants, en cas de phénylcétonurie et chez l'enfant de moins de 6 ans./" + random;
        tab[6] = "BACTIG10/BACTIGEL/Erythromycine/Ce médicament est utilisé en application locale pour traiter lacné et les infections cutanées bactériennes associées./Ce médicament est contre-indiqué en cas d'allergie aux antibiotiques de la famille des macrolides ou des lincosanides./" + random;
        tab[7] = "BACTIV13/BACTIVIL/Erythromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./" + random;
        tab[8] = "BITALV/BIVALIC/Dextropropoxyphène + Paracétamol/Ce médicament est utilisé pour traiter les douleurs dintensité modérée ou intense./Ce médicament est contre-indiqué en cas d'allergie aux médicaments de cette famille, d'insuffisance hépatique ou d'insuffisance rénale./" + random;
        tab[9] = "CARTION6/CARTION/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol,Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas de troubles de la coagulation (tendances aux hémorragies), dulcère gastroduodénal, maladies graves du foie./" + random;
        tab[10] = "CLAZER6/CLAZER/Clarithromycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques. Il est également utilisé dans le traitement de lulcère gastro-duodénal, en association avec d'autres médicaments./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./" + random;
        tab[11] = "DEPRIL9/DEPRAMIL/Clomipramine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères, certaines douleurs rebelles, les troubles obsessionnels compulsifs et certaines énurésies chez l'enfant./Ce médicament est contre-indiqué en cas de glaucome ou dadénome de la prostate, d'infarctus récent, ou si vous avez reà§u un traitement par IMAO durant les 2 semaines précédentes ou en cas d'allergie aux antidépresseurs imipraminiques./" + random;
        tab[12] = "DIMIRTAM6/DIMIRTAM/Mirtazapine/Ce médicament est utilisé pour traiter les épisodes dépressifs sévères./La prise de ce produit est contre-indiquée en cas de d'allergie à  l'un des constituants./" + random;
        tab[13] = "DOLRIL7/DOLORIL/Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol/Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre./Ce médicament est contre-indiqué en cas d'allergie au paracétamol ou aux salicylates./" + random;
        tab[14] = "DORNOM8/NORMADOR/Doxylamine/Ce médicament est utilisé pour traiter l'insomnie chez ladulte./Ce médicament est contre-indiqué en cas de glaucome, de certains troubles urinaires (rétention urinaire) et chez l'enfant de moins de 15 ans./" + random;
        tab[15] = "EQUILARX6/EQUILAR/Méclozine/Ce médicament est utilisé pour traiter les vertiges et pour prévenir le mal des transports./Ce médicament ne doit pas être utilisé en cas d'allergie au produit, en cas de glaucome ou de rétention urinaire./" + random;
        tab[16] = "EVILR7/EVEILLOR/Adrafinil/Ce médicament est utilisé pour traiter les troubles de la vigilance et certains symptomes neurologiques chez le sujet agé./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants./" + random;
        tab[17] = "INSXT5/INSECTIL/Diphénydramine/Ce médicament est utilisé en application locale sur les piqûres dinsecte et lurticaire./Ce médicament est contre-indiqué en cas d'allergie aux antihistaminiques./ " + random;
        tab[18] = "JOVAI8/JOVENIL/Josamycine/Ce médicament est utilisé pour traiter des infections bactériennes spécifiques./Ce médicament est contre-indiqué en cas d'allergie aux macrolides (dont le chef de file est lérythromycine)./" + random;
        tab[19] = "LIDOXY23/LIDOXYTRACINE/Oxytétracycline +Lidocaïne/Ce médicament est utilisé en injection intramusculaire pour traiter certaines infections spécifiques./Ce médicament est contre-indiqué en cas d'allergie à l'un des constituants. Il ne doit pas être associé aux rétinoïdes./" + random;

        //Création d'un Article
        System.out.println("JUSTE UN TEST");
        //On ouvre la base de données pour écrire dedans
        bdd.open();
        //On insère l'article que l'on vient de créer
        for (int y = 0; y <= 19; y++) {
            String[] laLigne = tab[y].split("/");
            System.out.println(laLigne.length);
            System.out.println("**0**" + laLigne[0].length());
            System.out.println(laLigne[0]);
            System.out.println("**1**" + laLigne[1].length());
            System.out.println(laLigne[1]);
            System.out.println("**2**" + laLigne[2].length());
            System.out.println(laLigne[2]);
            System.out.println("**3**" + laLigne[3].length());
            System.out.println(laLigne[3]);
            System.out.println("**4**" + laLigne[4].length());
            System.out.println(laLigne[4]);
            Medicament unMedicament = new Medicament(laLigne[0] + "", laLigne[1], laLigne[2], laLigne[3], laLigne[4], "10");
            bdd.insererMedicament(unMedicament);
        }
        Medicament unMedicament = new Medicament("0", "TEST", "compo", "effet", "cotre", "30");
        bdd.insererMedicament(unMedicament);
        Cursor c = bdd.getData();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " articles dans la BD", Toast.LENGTH_LONG).show();
        bdd.close();
    }
}
