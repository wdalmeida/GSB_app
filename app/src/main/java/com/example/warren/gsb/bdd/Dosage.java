package com.example.warren.gsb.bdd;

/**
 * Created by warren on 23/04/15, GSB
 */
public class Dosage {
    protected String quantite;
    protected String unité;
    protected String durée;

    public Dosage(String quantite, String unité, String durée) {
        this.quantite = quantite;
        this.unité = unité;
        this.durée = durée;
    }

    public Dosage() {
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getUnité() {
        return unité;
    }

    public void setUnité(String unité) {
        this.unité = unité;
    }

    public String getDurée() {
        return durée;
    }

    public void setDurée(String durée) {
        this.durée = durée;
    }
}
