package com.example.warren.gsb.bdd;

/**
 * Created by warren on 23/04/15, GSB
 */
public class Medicament {
    protected String id;
    protected String nom;
    protected String composition;
    protected String effet;
    protected String contreIndic;
    protected String prix;

    public Medicament(String id, String nom, String composition, String effet, String contreIndic, String prix) {
        this.id = id;
        this.nom = nom;
        this.composition = composition;
        this.effet = effet;
        this.contreIndic = contreIndic;
        this.prix = prix;
    }

    public Medicament() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getContreIndic() {
        return contreIndic;
    }

    public void setContreIndic(String contreIndic) {
        this.contreIndic = contreIndic;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
